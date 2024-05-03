package it.polimi.ingsw.network.server;

import java.rmi.RemoteException;

/**
 * Main class of the server, it starts both the RMI server and the socket server.
 */
public class ServerMain {
    private ServerRMI rmiServer;
    private ServerTCP tcpServer;
    private ServerHandler handler;
    private ServerConfigurationBase configurationBase;

    public ServerMain() {
        try {
            this.rmiServer = new ServerRMI();
            this.tcpServer = new ServerTCP();
            this.configurationBase = new ServerConfigurationBase();
        } catch (RemoteException e) {
            System.err.println("Unable to create a new server, maybe one is already running.");
        }
    }
    public static void main(String[] args) {

        ServerMain server = new ServerMain();

        try {
            server.start();
        } catch (Exception e) {
            System.err.println("Error on starting the server.");
        }
    }

    private void start() {
        handler = new ServerHandler(configurationBase);
        handler.init();
        rmiServer.start();
        tcpServer.start();
    }
}
