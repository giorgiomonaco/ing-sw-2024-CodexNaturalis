package it.polimi.ingsw.network.server;

import java.net.Socket;

/**
 * The class manages the communication between server
 * and the client that belongs to the socket assigned.
 */
public class TCPClientHandler implements Runnable{
    private Socket socket;
    public TCPClientHandler(Socket socket){
        this.socket = socket;
    }
    public void run(){
    }
}
