package it.polimi.ingsw.server;

import it.polimi.ingsw.network.ServerNetwork;
import java.util.Scanner;

/**
 * Main class of the server, it starts both the RMI server and the socket server.
 */
public class ServerMain {
    public static void main(String[] args) {
        String serverIP;
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the server address IP (press only ENTER for localhost) : ");
        serverIP = scan.nextLine().trim();
        ServerNetwork data = new ServerNetwork();

        if(!serverIP.isEmpty()) {

            data.setServerIP(serverIP);
            System.setProperty("java.rmi.server.hostname", serverIP);

        }

        System.out.println("---Summary---");
        System.out.println("-Server IP address: " + data.getServerIP());
        System.out.println("-------------");

        ServerHandler handler = new ServerHandler(data);
        handler.init();

    }
}
