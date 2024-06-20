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
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

import java.rmi.RemoteException;
import java.util.*;
import java.util.List;

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
    private GameStopper gameStopper;
    // Timeout for the game when only one player remain connected.
    public static int TIMEOUT = 30;
    private boolean stop;

    /**
     *  Constructor
     * @param data the configuration of the server
     */
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

    /**
     * Start the server
     */
    public void init() {
        rmiServer.start();
        tcpServer.start();
        pinger.start();
    }


    /**
     * Manages incoming messages based on their type and performs appropriate actions.
     *
     * @param msg the message to be managed, which contains information and commands
     *
     * <p>Handles the following message types:</p>
     * <ul>
     *   <li><b>SELECTION_NUM_PLAYERS</b>: Initializes the game if it hasn't started yet.
     *       Creates a main controller, assigns players, and manages waiting and connected clients.</li>
     *   <li><b>SELECTION_TOKEN</b>: Sets the token selected by a player and updates the game's available tokens.</li>
     *   <li><b>SELECTION_FIRSTCARD</b>: Handles the initial card side selection by a player and prompts for the next action.</li>
     *   <li><b>SELECTION_OBJECTIVE</b>: Sets the objective card selected by a player and manages the turn order.</li>
     *   <li><b>SELECTION_CARD</b>: Processes the selection of a card during the game and progresses the game state.</li>
     *   <li><b>DRAW_CARD_RESPONSE</b>: Manages the response after a player draws a card and ends the turn.</li>
     *   <li><b>PING</b>: Processes a ping message to keep the connection alive.</li>
     *   <li><b>CHATMSG</b>: Handles chat messages, updating the chat between players.</li>
     * </ul>
     */

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
                            new SelectFirstSide(messEnum.SELECT_FIRST_SIDE, selToken.getUsername()));
                }
                break;

            case SELECTION_FIRSTCARD:
                synchronized (controllerLock) {
                    SelectionFirstCardSide selFirstSide = (SelectionFirstCardSide) msg;
                    mainController.initialCardSideSelection(fromStringToBool(selFirstSide.getSelection()));
                    sendMessageToPlayer(selFirstSide.getUsername(), new SelectObjCard(HOSTNAME));
                }
                break;

            case SELECTION_OBJECTIVE:
                synchronized (controllerLock) {
                    SelectionObjCard selObj = (SelectionObjCard) msg;
                    String username = selObj.getUsername();
                    List<ObjectiveCard> objectiveCards = mainController.getPlayerByUsername(username).getSelObjectiveCard();
                    mainController.getPlayerByUsername(username).setObjectiveCard(objectiveCards.get(selObj.getSelection() - 1));

                    mainController.endFirstTurn();
                }
                break;

            case messEnum.SELECTION_CARD:
                System.out.println("ricevuto");
                synchronized (controllerLock) {
                    System.out.println("preso lock");
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


    /**
     * <p>Sends a message to a specified player identified by their username, if they are currently connected.</p>
     * <p>This method ensures thread safety by making a copy of the connected clients map
     * and synchronizing on individual client connections before sending the message.</p>
     *
     * @param username the username of the player to whom the message is sent
     * @param msg the message object containing the information to be sent
     *

     */

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


    /**
     * <p>Sends a message to all connected players except the one specified by username.</p>
     * This method ensures thread safety by making a copy of the connected clients map
     * and synchronizing on individual client connections before sending each message.
     *
     * @param username the username of the player who should not receive the message
     * @param msg the message object containing the information to be sent
     */

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

    /**
     * Manages a login request from a client, either connecting a new user or handling reconnection.
     *
     * @param request the login request containing the username
     * @param connection the client connection associated with the request
     * @return a LoginResult object indicating if the login was successful and if it was a reconnection
     */

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
                        // We interrupt the thread and we create a new one
                        gameStopper.interrupt();
                        gameStopper = new GameStopper(this);
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
                        sendMessageToPlayer(username,
                                new WaitTurnMsg(ServerHandler.HOSTNAME,
                                        mainController.getGame().getCommonObjectives(),
                                        mainController.getGame().getUserList(),
                                        p.getPlayerObjectiveCard(),
                                        p.getInitialCard()));
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


    /**
     * Processes a new login request from a client, handling joining a game or waiting in the lobby.
     *
     * @param request the login request containing the username
     */

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

    /**
     * Returns a copy of the map containing currently connected clients.

     * @return a copy of the map containing connected clients, where keys are usernames and values are ClientConnection objects
     */
    public Map<String, ClientConnection> getConnectedClients() {
        Map<String, ClientConnection> copy;
        synchronized (connectedClients) {
            copy = new HashMap<>(connectedClients);
        }
        return copy;
    }

    /**
     * Handles disconnection of a player, updating the game state and managing server shutdown if necessary.
     * @param client the ClientConnection object representing the disconnected player
     */

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
            if (mainController == null) {
                // the game has not been created yet.
                sendMessageToAll(new GameAborted(HOSTNAME));
                System.exit(1);
            } else {
                mainController.playerDisconnect(name);

                int count = 0;
                synchronized (connectedClients) {
                    for (String user : connectedClients.keySet()) {
                        if (connectedClients.get(user).isConnected()) {
                            count = count + 1;
                        }
                    }
                }
                if(count == 0) {
                    System.out.println(Colors.redColor + "The server is closing because no one is connected anymore." + Colors.resetColor);
                    System.exit(0);
                }
                else if(count == 1) {
                    stop = true;
                    gameStopper.start();
                } else {
                    mainController.checkNextTurnForDisconnection(name);
                }
            }
        }
    }



    /**
     * Ends the game, sending a message to all clients about the winner and then shutting down the server.
     *
     * <p>This method sends a ShowWinnerMessage to all connected clients indicating the end of the game.</p>
     * <p>The server then exits with code 2 to signify the game's end.</p>
     */

    public void endGame(){
        // the last player win the game
        sendMessageToAll(new ShowWinnerMessage(HOSTNAME, true, "suca"));

        System.exit(2);
    }


    /**
     * Converts a string representation to a boolean value.
     *
     * @param a the string to convert
     * @return {@code true} if the string equals "front", {@code false} otherwise
     */

    public boolean fromStringToBool(String a){
        return Objects.equals(a, "front");
    }
}
