package it.polimi.ingsw.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The class creates the ServerSocket and also the threads
 * that manage the accepted connections.
 */
public class ServerTCP {
    static int PORT = 1235;

    public void start(){
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("The port " + PORT + " is not available for the TCP Server.");
            return;
        }
        System.out.println("TCP server is ready on port: " + PORT);

        while(true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new TCPClientHandler(socket));
            } catch (IOException e) {
                break; // You join here if the serverSocket has been closed.
            }
        }
        executor.shutdown();
    }

    public void stop(){

    }
}
