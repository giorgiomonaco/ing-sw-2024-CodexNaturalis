package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.network.ClientConnection;
import it.polimi.ingsw.network.LoginResult;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ConnectionActive;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.allMessages.LoginResponse;
import it.polimi.ingsw.server.ServerHandler;

import java.io.*;
import java.net.Socket;


/**
 * The class manages the communication between server
 * and the client that belongs to the socket assigned.
 */
public class TCPClientHandler extends ClientConnection implements Runnable{
    private final Socket socket;
    private final ServerHandler handlerTCP;
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
        LoginResult result;

        try {
            do {
                request = (LoginRequest) in.readObject();
                result = manageLogin(request);
            } while(!result.isLogged());
        } catch (IOException e) {
            System.err.println("Lost connection with the client: " + socket);
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't cast a message of the client: " + socket);
        }

        // wait for the msg of the client (login already managed)
        while(!Thread.currentThread().isInterrupted()){
            try {
                msg = (Message) in.readObject();
                handlerTCP.manageMessage(msg);
            } catch (IOException e) {
                System.err.println("Lost connection with the client: " + socket);
                // have to manage disconnection of the client!!
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't cast a message of the client: " + socket);
                // IDK if I have to disconnect the client here, maybe just resend the message.
            }
        }

    }
/*
    public boolean manageLogin(LoginRequest msg) {
        boolean result;
        LoginResponse response;

        if(handlerTCP.getConnectedClients().isEmpty()){
            handlerTCP.getConnectedClients().add(msg.getUsername());
            System.out.println("New player added, username: " + msg.getUsername());
            // maybe have to start the game here
            response = new LoginResponse(ServerHandler.HOSTNAME, 1, msg.getUsername());
            result = true;
        } else if(handlerTCP.getConnectedClients().size() == handlerTCP.getGame().getNumOfPlayers()){
            response = new LoginResponse(ServerHandler.HOSTNAME, 4, msg.getUsername());
            result = false;
        } else if(handlerTCP.getConnectedClients().contains(msg.getUsername())){
                response = new LoginResponse(ServerHandler.HOSTNAME, 3, msg.getUsername());
                result = false;
        } else {
                handlerTCP.getConnectedClients().add(msg.getUsername());
                System.out.println("New player added, username: " + msg.getUsername());
                response = new LoginResponse(ServerHandler.HOSTNAME, 2, msg.getUsername());
                result = true;
        }

        try {
            out.writeObject(response);
            out.flush();
        } catch (IOException e) {
            System.err.println("Lost connection with the server.");
        }

        return result;
    }
*/

    public LoginResult manageLogin(LoginRequest request){
        setConnected(true);
        LoginResult result = handlerTCP.manageLoginRequest(request, this);

        if(result.isLogged() && result.isReconnected()){
            sendMessage(new LoginResponse(ServerHandler.HOSTNAME, 1, request.getUsername()));
        }
        else if(result.isLogged() && !result.isReconnected()){
            sendMessage(new LoginResponse(ServerHandler.HOSTNAME, 1, request.getUsername()));
            handlerTCP.newLoginRequest(request);
        } else {
            sendMessage(new LoginResponse(ServerHandler.HOSTNAME, 2, request.getUsername()));
        }

        return result;
    }

    public void sendMessage(Message msg){
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
