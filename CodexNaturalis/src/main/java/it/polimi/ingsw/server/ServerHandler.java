package it.polimi.ingsw.server;

import it.polimi.ingsw.network.ClientConnection;
import it.polimi.ingsw.network.LoginResult;
import it.polimi.ingsw.network.RMI.ServerRMI;
import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.network.TCP.ServerTCP;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.controller.MainController;
import it.polimi.ingsw.server.model.*;


import java.rmi.RemoteException;
import java.util.*;

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

    public ServerHandler(ServerConfigNetwork data) {
        this.configBase = data;
        connectedClients = new HashMap<>();
        waitingLobby = new ArrayList<>();
        creatingLobby = false;
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
    }

    public void isValid(String check){
        // Check if the ip is valid, maybe we can assume that anyway...
    }

    public void manageMessage(Message msg) {
        switch(msg.getType()) {
            case messEnum.SELECTION_NUM_PLAYERS:
                synchronized (controllerLock){

                    if(mainController == null){

                        SelectionNumPlayers sel = (SelectionNumPlayers) msg;
                        this.mainController = new MainController(this);
                        mainController.gameCreation(sel.getUsername(), sel.getNumOfPlayers());
                        if(!waitingLobby.isEmpty()){
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
                            for(int i = 0; i < acceptedClients; i++){
                                mainController.joinPlayer(waitingLobby.get(i));
                            }
                        }

                    } else {

                        sendMessageToPlayer(msg.getUsername(),
                                new AlreadyStarted(ServerHandler.HOSTNAME));

                    }
                }
                break;
            case messEnum.DRAW_CARD_REQUEST:
                synchronized (controllerLock){
                    DrawCardRequest draw = (DrawCardRequest) msg;
                    Card card = mainController.drawCard(draw.getUsername(), draw.getWhereToDraw(), draw.getCardIndex());
                    if (card != null) {
                        sendMessageToPlayer(draw.getUsername(),
                                new DrawCardResponse(messEnum.DRAW_CARD_RESPONSE, draw.getUsername(), card));
                    }
                }
            case SELECTION_TOKEN:
                synchronized (controllerLock) {
                    SelectionToken selToken = (SelectionToken) msg;
                    mainController.getPlayerByUsername(selToken.getUsername()).setPlayerTokenS(selToken.getDescription());
                    mainController.getGame().removeAvailableTokens(selToken.getDescription());
                    sendMessageToPlayer(selToken.getUsername(),
                            new SelectObjCard(ServerHandler.HOSTNAME));
                }
                break;
            case SELECTION_OBJECTIVE:
                synchronized (controllerLock) {
                    SelectionObjCard selObj = (SelectionObjCard) msg;
                    String username = selObj.getUsername();
                    List<ObjectiveCard> objectiveCards = mainController.getPlayerByUsername(username).getSelObjectiveCard();
                    mainController.getPlayerByUsername(username).setObjectiveCard(objectiveCards.get(selObj.getSelection() - 1));

                    if(mainController.isFirstTurn()){
                        mainController.beginFirstTurn();
                    } else {
                       mainController.beginTurn();
                    }
                }
                break;
            case messEnum.SELECTION_CARD:
                synchronized (controllerLock){
                    SelectionCard selCard = (SelectionCard) msg;
                    Player player = mainController.getPlayerByUsername(selCard.getUsername());
                    mainController.playCard(player, selCard.getCard(), selCard.getX(), selCard.getY(), selCard.getSide());
                    //manda nuovo mess
                }
                break;
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
                System.out.println("New client connected with the username: " + username);
                logged = true;
            } else {
                // Reconnection of a known player
                if (!connectedClients.get(username).isConnected()) {
                    connectedClients.get(username).setConnected(true);
                    System.out.println("Reconnection of the player with the username: " + username);
                    logged = true;
                    reconnected = true;
                } else {
                    System.out.println("The username " + username + "is already taken, try to choose another one.");
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
}
