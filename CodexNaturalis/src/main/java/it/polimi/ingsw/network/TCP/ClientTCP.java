package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.stateManager.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ConnectionActive;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientTCP extends Client {
    private Socket socket;
    private final String serverIP;
    private final int serverPort;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ExecutorService execService;
    private SenderTCP sender;
    private ReceiverTCP receiver;


    public ClientTCP(String IP, int port) {
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

        receiver = new ReceiverTCP(in, this);
        receiver.start();
        sender = new SenderTCP(out);
    }


    public void sendMessage(Message msg) {
        execService.submit(() -> sender.sendMessage(msg));
    }

    public void manageMessage(Message msg) {
        switch (msg.getType()) {
            case LOGIN_RESPONSE:
                if(msg.getDescription().equals("true")){
                    msg.printMessage();
                    setCurrentState(stateEnum.LOBBY);
                    getUI().run();
                } else {
                   System.out.println("Username already in use, try to choose another one.");
                   getUI().run();
                }
        }
    }

}