package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.ServerNetwork;
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
public class ServerTCP extends Thread{
    static int PORT;
    private ServerHandler handlerTCP;
    public List<TCPClientHandler> connectedClients;

    public ServerTCP(ServerNetwork data, ServerHandler handler){
        handlerTCP = handler;
        PORT = data.getPortTCP();
        connectedClients = new ArrayList<>();
    }

    public void run(){
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
                System.out.println(Colors.yellowColor + "New TCP connection accepted." + Colors.resetColor);
                TCPClientHandler acceptedClient;
                acceptedClient = new TCPClientHandler(socket, handlerTCP);

                connectedClients.add(acceptedClient);
                executor.submit(acceptedClient);
            } catch (IOException e) {
                break; // You join here if the serverSocket has been closed.
            }
        }
        executor.shutdown();
    }

}
