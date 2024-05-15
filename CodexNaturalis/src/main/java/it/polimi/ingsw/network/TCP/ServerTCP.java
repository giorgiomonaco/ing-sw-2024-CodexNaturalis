package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.server.ServerHandler;

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
    static int PORT;
    private ServerHandler handlerTCP;
    public List<TCPClientHandler> connectedClients;

    public ServerTCP(ServerConfigNetwork data, ServerHandler handler){
        handlerTCP = handler;
        PORT = data.getPortTCP();
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
        System.out.println("--- TCP server is ready on port: " + PORT + " ---");

        while(true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("New TCP connection accepted on socket: " + socket);
                TCPClientHandler acceptedClient;
                acceptedClient = new TCPClientHandler(socket, handlerTCP);

                connectedClients.add(acceptedClient);
                executor.submit(acceptedClient);
                //Thread newThread = new Thread(acceptedClient);
                // newThread.start();
            } catch (IOException e) {
                break; // You join here if the serverSocket has been closed.
            }
        }
        executor.shutdown();
    }

    public void stop(){

    }
}
