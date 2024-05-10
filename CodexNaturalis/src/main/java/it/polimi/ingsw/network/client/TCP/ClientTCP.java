package it.polimi.ingsw.network.client.TCP;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ConnectionActive;

import java.io.*;
import java.net.Socket;

public class ClientTCP extends Client {
    private Socket socket;
    private final String serverIP;
    private final int serverPort;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ClientTCP(String IP, int port) {
        serverIP = IP;
        serverPort = port;
        start();
    }

    public void start() {

        try {
            socket = new Socket(serverIP, serverPort);
        } catch (IOException e) {
            System.err.println("Couldn't connect to the TCP server " + serverIP);
        }

        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Couldn't get the I/O for the TCP connection to the server " + serverIP);
        }

        Message connectionMessage;
        try {
            connectionMessage = (ConnectionActive) in.readObject();
            connectionMessage.printMessage();
        } catch (IOException e) {
            System.err.println("Lost connection with the server " + serverIP);
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't cast the message from the server " + serverIP);
        }

        System.out.println("TCP Client ready to receive and send.");

    }

}