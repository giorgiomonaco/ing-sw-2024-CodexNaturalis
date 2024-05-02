package it.polimi.ingsw.network.server;

import it.polimi.ingsw.model.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The class creates the ServerSocket and also the threads
 * that manage the accepted connections.
 */
public class ServerTCP {
    static int PORT = 1235;
    public List<TCPClientHandler> connectedClients;

    public ServerTCP(){
        connectedClients = new ArrayList<>();
    }

    public void start(){
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("The port " + PORT + " is not available for the TCP server.");
            return;
        }
        System.out.println("TCP server is ready on port: " + PORT);

        while(true) {
            try {
                Socket socket = serverSocket.accept();
                TCPClientHandler acceptedClient;
                acceptedClient = new TCPClientHandler(socket);

                executor.submit(acceptedClient);
                connectedClients.add(acceptedClient);
            } catch (IOException e) {
                break; // You join here if the serverSocket has been closed.
            }
        }
        executor.shutdown();
    }

    public void stop(){

    }
}
