package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.Client;

import java.rmi.RemoteException;

/**
 * Main class of the server, it starts both the RMI server and the socket server.
 */
public class Server {
    private ServerRMI rmiServer;
    private ServerTCP tcpServer;

    public Server() {
        try {
            this.rmiServer = new ServerRMI();
            this.tcpServer = new ServerTCP();
        } catch (RemoteException e) {
            System.err.println("Unable to create a new server, maybe one is already running.");
        }
    }
    public static void main(String[] args) {

        Server server = new Server();

        try {
            server.start();
        } catch (Exception e) {
            System.err.println("Unable to start the server.");
        }
    }

    private void start() {
        rmiServer.start();
        tcpServer.start();
    }
}
