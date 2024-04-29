package it.polimi.ingsw.network.client;
import it.polimi.ingsw.network.server.RMIServerInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;


public class ClientTCP {
    public static void main(String[] args) {
        // Indirizzo IP e porta del server
        String serverAddress = "127.0.0.1"; // Indirizzo IP del server
        int serverPort = 12345; // Porta del server

        try {
            // Creazione della socket del client e connessione al server
            Socket socket = new Socket(serverAddress, serverPort);

            // Creazione degli stream di input e output per la comunicazione con il server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Invio di un messaggio al server
            out.println("Ciao, server!");

            // Lettura della risposta dal server
            String rispostaDalServer = in.readLine();
            System.out.println("Risposta dal server: " + rispostaDalServer);

            // Chiusura della socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
