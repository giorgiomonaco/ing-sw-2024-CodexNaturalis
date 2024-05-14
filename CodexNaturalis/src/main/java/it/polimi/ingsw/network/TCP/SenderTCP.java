package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.network.message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SenderTCP{
    private ObjectOutputStream out;

    public SenderTCP(ObjectOutputStream output) {
        out = output;
    }

    public void sendMessage(Message msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
