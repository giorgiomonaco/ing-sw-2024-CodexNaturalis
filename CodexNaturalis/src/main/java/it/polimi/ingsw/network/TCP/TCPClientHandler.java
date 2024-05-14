package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ConnectionActive;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.allMessages.LoginResponse;
import it.polimi.ingsw.server.ServerHandler;

import java.io.*;
import java.net.Socket;

import static sun.util.locale.LocaleUtils.isEmpty;

/**
 * The class manages the communication between server
 * and the client that belongs to the socket assigned.
 */
public class TCPClientHandler implements Runnable{
    private final Socket socket;
    private ServerHandler handlerTCP;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public TCPClientHandler(Socket socket, ServerHandler handler){
        handlerTCP = handler;
        this.socket = socket;
    }

    public void run(){

        try {
             synchronized (socket) {
                 out = new ObjectOutputStream(socket.getOutputStream());
                 in = new ObjectInputStream(socket.getInputStream());
             }
        } catch (IOException e) {
            System.err.println("Couldn't open the I/O for the TCP connection via server");
        }

        // send a msg to the client of the received connection
        Message msg;
        try {
            msg = new ConnectionActive(ServerHandler.HOSTNAME);
            out.writeObject(msg);
            out.flush();
            System.out.println("New active connection. Starting login phase.");
        } catch (IOException e) {
            System.err.println("Couldn't connect with the client");
            Thread.currentThread().interrupt();
        }

        // wait for the login message
        LoginRequest request;
        boolean result;

        try {
            do {
                request = (LoginRequest) in.readObject();
                result = manageLogin(request);
            } while(!result);
        } catch (IOException e) {
            System.err.println("Lost connection with the client: " + socket);
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't cast a message of the client: " + socket);
        }

        // wait for the msg of the client (the first is login)
        while(!Thread.currentThread().isInterrupted()){
            try {
                msg = (Message) in.readObject();
                handlerTCP.manageMessage(msg);
            } catch (IOException e) {
                System.err.println("Lost connection with the client: " + socket);
                // disconnessione sennò continua a stampare all'infinito
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't cast a message of the client: " + socket);
                // disconnessione ??
            }
        }

    }

    public boolean manageLogin(LoginRequest msg) {
        boolean result;
        LoginResponse response;

        if(handlerTCP.getConnectedClients().isEmpty()){
            handlerTCP.getConnectedClients().add(msg.getUsername());
            System.out.println("aggiunto username: " + msg.getUsername());
            // nuovo gioco
            response = new LoginResponse(ServerHandler.HOSTNAME, "first");
            result = true;
        } else {
            if(handlerTCP.getConnectedClients().contains(msg.getUsername())){
                response = new LoginResponse(ServerHandler.HOSTNAME, "false");
                result = false;
            }
            else {
                handlerTCP.getConnectedClients().add(msg.getUsername());
                System.out.println("aggiunto username: " + msg.getUsername());
                response = new LoginResponse(ServerHandler.HOSTNAME, "true");
                result = true;
            }
        }

        try {
            out.writeObject(response);
            out.flush();
        } catch (IOException e) {
            System.err.println("Lost connection with the server.");
        }

        return result;
    }

}
