package it.polimi.ingsw.server;

import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.network.message.Message;

import java.util.Scanner;

public class ServerHandler {
    private ServerConfigNetwork data;
    public static String HOSTNAME = "Server";

    public ServerHandler(ServerConfigNetwork configurationBase){
        this.data = configurationBase;
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

    }
}
