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



import java.rmi.RemoteException;
import java.util.*;

public class ServerHandler {
    private ServerRMI rmiServer;
    private ServerTCP tcpServer;
    private ServerConfigNetwork configBase;
    public static String HOSTNAME = "Server";
    public Map<String, ClientConnection> connectedClients;
    private MainController mainController;
    private List<String> waitingLobby;
    private boolean creatingLobby;
    private final Object lobbyLock = new Object();
    private final Object controllerLock = new Object();

    public ServerHandler(ServerConfigNetwork data) {
        configBase = data;
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
        // Check if the ip is valid, maybe we can assume that anyway..
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
        }
    }

    public void sendMessageToPlayer(String username, Message msg){
        if(connectedClients.containsKey(username)){
            connectedClients.get(username).sendMessage(msg);
        }
    }

    public LoginResult manageLoginRequest(LoginRequest request, ClientConnection connection){
        boolean logged = false;
        boolean reconnected = false;
        String username = request.getUsername();

        if(!connectedClients.containsKey(username)){
            // New user
            connectedClients.put(username, connection);
            System.out.println("New client connected with the username: " + username);
            logged = true;
        } else {
            if(!connectedClients.get(username).isConnected()){
                connectedClients.get(username).setConnected(true);
                System.out.println("Reconnection of the player with the username: " + username);
                logged = true;
                reconnected = true;
            }
            else {
                System.out.println("The username " + username + "is already taken, try to choose another one.");
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
        return connectedClients;
    }




}
