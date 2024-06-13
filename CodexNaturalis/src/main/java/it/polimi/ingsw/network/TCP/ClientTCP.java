package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ConnectionActive;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientTCP extends Client{
    private Socket socket;
    private final String serverIP;
    private final int serverPort;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ExecutorService execService;
    private SenderTCP sender;
    private ReceiverTCP receiver;


    public ClientTCP(String IP, int port) throws RemoteException {
        super();
        serverIP = IP;
        serverPort = port;
        execService = Executors.newSingleThreadExecutor();
        start();
    }

    public void start() {

        try {
            socket = new Socket(serverIP, serverPort);
        } catch (IOException e) {
            System.err.println("Couldn't connect to the TCP server " + serverIP);
            manageDisconnection();
        }

        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Couldn't get the I/O for the TCP connection to the server " + serverIP);
            manageDisconnection();
        }

        Message connectionMessage;
        try {
            connectionMessage = (ConnectionActive) in.readObject();
        } catch (IOException e) {
            System.err.println("Lost connection with the server " + serverIP);
            manageDisconnection();
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't cast the message from the server " + serverIP);
            manageDisconnection();
        }

        System.out.println("TCP Client ready to receive and send.");

        receiver = new ReceiverTCP(in, this);
        receiver.start();
        sender = new SenderTCP(out, this);
    }


    public void sendMessage(Message msg) {
        execService.submit(() -> sender.sendMessage(msg));
    }


}