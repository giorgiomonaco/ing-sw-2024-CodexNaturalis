package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.network.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReceiverTCP {
    private ObjectInputStream in;

    public ReceiverTCP(ObjectInputStream input) {
        in = input;
    }

    public void run(){
        Message msg;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                msg = (Message) in.readObject();
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
