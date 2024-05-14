package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.network.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReceiverTCP extends Thread{
    private ObjectInputStream in;
    private ClientTCP client;

    public ReceiverTCP(ObjectInputStream input, ClientTCP client) {
        in = input;
        this.client = client;
    }

    public void run(){
        Message msg;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                msg = (Message) in.readObject();
                client.manageMessage(msg);
            } catch (IOException e) {
                System.err.println("Lost connection to the server.");
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't cast the message.");
                throw new RuntimeException(e);
            }
        }
    }

}
