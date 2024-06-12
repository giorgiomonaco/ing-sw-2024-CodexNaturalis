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
            handlerTCP.playerDisconnection(this);
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't cast a message of the client: " + socket);
            handlerTCP.playerDisconnection(this);
        }

        // wait for the msg of the client (login already managed)
        while(!Thread.currentThread().isInterrupted()){
            try {
                msg = (Message) in.readObject();
                handlerTCP.manageMessage(msg);
            } catch (IOException e) {
                System.err.println("Lost connection with the client: " + socket);
                Thread.currentThread().interrupt();
                handlerTCP.playerDisconnection(this);
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't cast a message of the client: " + socket);
                handlerTCP.playerDisconnection(this);
            }
        }

    }

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
            sendMessage(new LoginResponse(ServerHandler.HOSTNAME, 2, "Username already in use, try to insert another one."));
        }

        return result;
    }

    public void sendMessage(Message msg){
        try {
            out.writeObject(msg);
            out.flush();
            out.reset();
        } catch (IOException e) {
            System.err.println("Lost connection with the client: " + socket);
            handlerTCP.playerDisconnection(this);
        }
    }

}
