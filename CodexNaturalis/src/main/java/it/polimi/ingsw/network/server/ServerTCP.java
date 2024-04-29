package it.polimi.ingsw.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerTCP {
    static final String SERVER_ADDRESS = "127.0.0.1"; // IP address of the server
    static final int SERVER_PORT = 1235; // Server port

  public void start(){
          try {
            // connection
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connesso al server " + SERVER_ADDRESS + " sulla porta " + SERVER_PORT);

            // stream creation for server comunication
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // trying to send a message
            // out.println("Ciao, server!");

            // reading server answer and reply
            // String rispostaDalServer = in.readLine();
            // System.out.println("Risposta dal server: " + rispostaDalServer);

            // socket closure
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    }


