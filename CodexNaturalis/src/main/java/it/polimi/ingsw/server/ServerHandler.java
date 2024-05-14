package it.polimi.ingsw.server;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static sun.util.locale.LocaleUtils.isEmpty;

public class ServerHandler {
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
            case messEnum.LOGIN_REQUEST :

        }
    }

    public List<String> getConnectedClients() {
        return connectedClients;
    }

}
