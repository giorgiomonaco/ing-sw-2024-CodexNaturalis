package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.server.RMI.ServerRMI;
import it.polimi.ingsw.network.server.TCP.ServerTCP;

import java.rmi.RemoteException;

/**
 * Main class of the server, it starts both the RMI server and the socket server.
 */
public class ServerMain {
    private ServerRMI rmiServer;
    private ServerTCP tcpServer;
    private ServerHandler handler;
    private ServerConfigNetwork configurationBase;

    public ServerMain() {
        try {
            this.configurationBase = new ServerConfigNetwork();
            handler = new ServerHandler(configurationBase);
            handler.init();
            this.rmiServer = new ServerRMI(configurationBase, handler);
            this.tcpServer = new ServerTCP(configurationBase, handler);

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
        rmiServer.start();
        tcpServer.start();
    }
}
