package it.polimi.ingsw.server;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.SelectingNumPlayers;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerHandler {
    private Game game;
    private ServerConfigNetwork data;
    public static String HOSTNAME = "Server";
    private List<String> connectedClients;

    public ServerHandler(ServerConfigNetwork configurationBase){
        this.data = configurationBase;
        connectedClients = new ArrayList<>();
    }

    public void init() {
        String serverIP;
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the server address IP if you want, instead press only ENTER: ");
        serverIP = scan.nextLine().trim();

        if(!serverIP.isEmpty()) {
            // if(isValid(serverIP)){
                data.setServerIP(serverIP);
            // }
        }

        System.out.println("---Summary---");
        System.out.println("-Server IP address: " + data.getServerIP());
        System.out.println("-------------");
    }

    public void isValid(String check){
        // Check if the ip is valid, maybe we can assume that anyway..
    }

    public void manageMessage(Message msg) {
        switch(msg.getType()) {
            case messEnum.LOGIN_REQUEST:
                //for the reconnection I think
                break;
            case messEnum.SELECTING_NUM_PLAYERS:
                SelectingNumPlayers selection = (SelectingNumPlayers) msg;
                // check if it's a valid number
                if(selection.getNumOfPlayers() > 1 && selection.getNumOfPlayers() < 5){
                    game = new Game();
                    game.setNumOfPlayers(selection.getNumOfPlayers());
                }
        }
    }

    public List<String> getConnectedClients() {
        return connectedClients;
    }

    public Game getGame() {
        return game;
    }
}
