package it.polimi.ingsw.network.client.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTCP {
    private Socket socket;
    private final String serverIP;
    private final int serverPort;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIN;


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
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            stdIN = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.err.println("Couldn't get the I/O for the TCP connection to the server " + serverIP);
        }

        System.out.println("TCP Client ready to receive and send.");
    }

}