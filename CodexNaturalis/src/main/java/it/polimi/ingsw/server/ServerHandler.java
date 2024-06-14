package it.polimi.ingsw.server;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.ClientConnection;
import it.polimi.ingsw.network.LoginResult;
import it.polimi.ingsw.network.Pinger;
import it.polimi.ingsw.network.RMI.ServerRMI;
import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.network.TCP.ServerTCP;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.controller.GameStopper;
import it.polimi.ingsw.server.controller.MainController;
import it.polimi.ingsw.server.model.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServerHandler {
    private ServerRMI rmiServer;
    private ServerTCP tcpServer;
    private final ServerConfigNetwork configBase;
    public static String HOSTNAME = "Server";
    public final Map<String, ClientConnection> connectedClients;
    private MainController mainController;
    private final List<String> waitingLobby;
    private boolean creatingLobby;
    private final Object lobbyLock = new Object();
    private final Object controllerLock = new Object();
    private final Pinger pinger;
    private final GameStopper gameStopper;
    // Timeout for the game when only one player remain connected.
    public static int TIMEOUT = 30;
    private boolean stop;

    public ServerHandler(ServerConfigNetwork data) {
        this.configBase = data;
        connectedClients = new HashMap<>();
        waitingLobby = new ArrayList<>();
        creatingLobby = false;
        pinger = new Pinger(this);
        gameStopper = new GameStopper(this);
        stop = false;

        try {
            rmiServer = new ServerRMI(configBase, this);
            tcpServer = new ServerTCP(configBase, this);
        } catch (RemoteException e) {
            System.err.println("Unable to create a new server, maybe one is already running.");
        }

    }

    public void init() {
        rmiServer.start();
        tcpServer.start();
        pinger.start();
    }

    public void isValid(String check){
        // Check if the ip is valid, maybe we can assume that anyway...
    }

    public void manageMessage(Message msg) {
        switch(msg.getType()) {
            case messEnum.SELECTION_NUM_PLAYERS:
                synchronized (controllerLock) {

                    if (mainController == null) {

                        SelectionNumPlayers sel = (SelectionNumPlayers) msg;
                        this.mainController = new MainController(this);
                        mainController.gameCreation(sel.getUsername(), sel.getNumOfPlayers());
                        if (!waitingLobby.isEmpty()) {
                            int waitingSize = waitingLobby.size();
                            int acceptedClients = sel.getNumOfPlayers() - 1;
                            int rejectedClients = waitingSize - acceptedClients;
                            synchronized (connectedClients) {
                                for (int i = 0; i < rejectedClients; i++) {
                                    sendMessageToPlayer(waitingLobby.get(waitingSize - 1 - i),
                                            new RejectedMessage(ServerHandler.HOSTNAME));
                                    connectedClients.get(waitingLobby.get(waitingSize - 1 - i)).setConnected(false);
                                }
                            }
                            for (int i = 0; i < acceptedClients; i++) {
                                mainController.joinPlayer(waitingLobby.get(i));
                            }
                        }

                    } else {

                        sendMessageToPlayer(msg.getUsername(),
                                new AlreadyStarted(ServerHandler.HOSTNAME));

                    }
                }
                break;
            case SELECTION_TOKEN:
                synchronized (controllerLock) {
                    SelectionToken selToken = (SelectionToken) msg;
                    mainController.getPlayerByUsername(selToken.getUsername()).setPlayerTokenS(selToken.getDescription());
                    mainController.getGame().removeAvailableTokens(selToken.getDescription());
                    sendMessageToPlayer(selToken.getUsername(),
                            new SelectFirstSide(messEnum.SELECT_FIRST_SIDE,selToken.getUsername()));
                }
                break;

                case messEnum.SELECTION_FIRSTCARD:
                synchronized (controllerLock) {
                    SelectionFirstCardSide selFirstSide = (SelectionFirstCardSide) msg;
                    mainController.getPlayerByUsername(selFirstSide.getUsername()).getInitialCard().setFrontSide(Trasfomatore(selFirstSide.getSelection()));
                    sendMessageToPlayer(selFirstSide.getUsername(), new SelectObjCard(HOSTNAME));
                }
                break;

            case SELECTION_OBJECTIVE:
                synchronized (controllerLock) {
                    SelectionObjCard selObj = (SelectionObjCard) msg;
                    String username = selObj.getUsername();
                    List<ObjectiveCard> objectiveCards = mainController.getPlayerByUsername(username).getSelObjectiveCard();
                    mainController.getPlayerByUsername(username).setObjectiveCard(objectiveCards.get(selObj.getSelection() - 1));

                    if (!mainController.isLastPlayer(username)) {
                        mainController.beginFirstTurn();
                    } else {
                        mainController.setFirstTurn(false);
                        mainController.beginTurn();
                    }
                }
                break;

            case messEnum.SELECTION_CARD:
                synchronized (controllerLock) {
                    SelectionCard selCard = (SelectionCard) msg;
                    mainController.selectionCard(selCard.getCard(), selCard.getX(), selCard.getY(), selCard.getSide());
                    mainController.middleTurn();
                }
                break;
            case messEnum.DRAW_CARD_RESPONSE:
                synchronized (controllerLock) {
                    DrawCardResponse draw = (DrawCardResponse) msg;
                    int choice = draw.getChoice();
                    mainController.drawCard(choice);
                    mainController.endTurn();
                }
                break;
            case PING:
                pinger.loadMessage(msg);
                break;
            case messEnum.CHATMSG:
                synchronized (controllerLock) {
                    ChatMessage chatMsg = (ChatMessage) msg;
                    String destination = chatMsg.getDestination();
                    String chat = chatMsg.getChat();
                    mainController.chatUpdate(chatMsg.getUsername(), destination, chat);
                    break;
                }




        }
    }

    public void sendMessageToPlayer(String username, Message msg){

        Map<String, ClientConnection> connCliCopy;
        synchronized (connectedClients) {
            connCliCopy = new HashMap<>(connectedClients);
        }

        if(connCliCopy.containsKey(username) &&
        connCliCopy.get(username).isConnected()) {
            synchronized (connCliCopy.get(username)) {
                connCliCopy.get(username).sendMessage(msg);
            }
        }
    }

    public void sendMessageToAll(Message msg){

        Map<String, ClientConnection> connCliCopy;
        synchronized (connectedClients) {
            connCliCopy = new HashMap<>(connectedClients);
        }

        for (String user : connCliCopy.keySet()) {
            synchronized (connCliCopy.get(user)) {
                if (connCliCopy.get(user).isConnected()) {
                    connCliCopy.get(user).sendMessage(msg);
                }
            }
        }
    }


    public void sendMessageToAllExcept(String username, Message msg){

        Map<String, ClientConnection> connCliCopy;
        synchronized (connectedClients) {
            connCliCopy = new HashMap<>(connectedClients);
        }

        for (String user : connCliCopy.keySet()) {
            synchronized (connCliCopy.get(user)) {
                if (connCliCopy.get(user).isConnected() &&
                        !connCliCopy.get(user).getUsername().equals(username)) {
                    connCliCopy.get(user).sendMessage(msg);
                }
            }
        }
    }

    public LoginResult manageLoginRequest(LoginRequest request, ClientConnection connection){
        boolean logged = false;
        boolean reconnected = false;
        String username = request.getUsername();

        synchronized (connectedClients) {
            if (!connectedClients.containsKey(username)) {
                // New user
                connectedClients.put(username, connection);
                connection.setUsername(username);
                System.out.println(Colors.yellowColor + "New client connected with the username: " + username + Colors.resetColor);
                logged = true;
            } else {
                // Reconnection of a known player
                if (!connectedClients.get(username).isConnected()) {
                    // Check if the game is in STOP phase
                    if(stop){
                        gameStopper.interrupt();
                        stop = false;
                        synchronized (controllerLock) {
                            mainController.beginTurn();
                        }
                    }

                    // Update with the new connection
                    connectedClients.replace(username, connectedClients.get(username), connection);
                    connection.setUsername(username);
                    System.out.println(Colors.yellowColor + "Reconnection of the player with the username: " + username + Colors.resetColor);

                    // Update the model
                    synchronized (controllerLock) {
                        Player p = mainController.getPlayerByUsername(username);
                        p.setConnected(true);
                    }
                    logged = true;
                    reconnected = true;
                } else {
                    System.out.println(Colors.yellowColor + "The username " + username + " is already taken, try to choose another one." + Colors.resetColor);
                }
            }
        }

        return new LoginResult(logged, reconnected);
    }

    public void newLoginRequest(LoginRequest request){
        String username = request.getUsername();
        synchronized (controllerLock) {
            if (mainController != null){
                mainController.joinPlayer(username);
            } else {
                synchronized (lobbyLock) {
                    if (creatingLobby) {
                        waitingLobby.add(username);
                        sendMessageToPlayer(username, new WaitingForLobby(ServerHandler.HOSTNAME));
                    } else {
                        creatingLobby = true;
                        sendMessageToPlayer(username, new SelectNumPlayers(ServerHandler.HOSTNAME));
                    }
                }
            }
        }
    }

    public Map<String, ClientConnection> getConnectedClients() {
        Map<String, ClientConnection> copy;
        synchronized (connectedClients) {
            copy = new HashMap<>(connectedClients);
        }
        return copy;
    }

    public void playerDisconnection(ClientConnection client){
        System.out.println(Colors.redColor+ "Disconnection of a client..." + Colors.resetColor);
        String name = null;

        synchronized (connectedClients) {
            // check if the client is actually connected
            if (connectedClients.containsValue(client)) {
                //search the nickname of the client
                for (String user : connectedClients.keySet()) {
                    if (connectedClients.get(user).equals(client)) {
                        name = user;
                        connectedClients.get(user).setConnected(false);
                        System.out.println(Colors.redColor + user + " is now disconnected!" + Colors.resetColor);
                        break;
                    }
                }
            } else {
                System.out.println(Colors.redColor + "The client was already disconnected." + Colors.resetColor);
                return;
            }
        }

        synchronized (controllerLock) {
            if (mainController == null || mainController.isFirstTurn()) {
                // the game has not been created yet.
                sendMessageToAll(new GameAborted(HOSTNAME));
                System.exit(1);
            } else {
                mainController.playerDisconnect(name);

                int count = 0;
                synchronized (connectedClients) {
                    for (String user : connectedClients.keySet()) {
                        if (connectedClients.get(user).isConnected()) {
                            count++;
                        }
                    }
                }
                if(count == 1) {
                    stop = true;
                    gameStopper.start();
                } else {
                    mainController.checkNextTurnForDisconnection(name);
                }
            }
        }
    }

    public void endGame(){
        // the last player win the game
        sendMessageToAll(new ShowWinnerMessage(HOSTNAME, true));

        System.exit(2);
    }

    public boolean Trasfomatore(String a){
        if(Objects.equals(a, "front")) return true;
        else return false;
    }
}
