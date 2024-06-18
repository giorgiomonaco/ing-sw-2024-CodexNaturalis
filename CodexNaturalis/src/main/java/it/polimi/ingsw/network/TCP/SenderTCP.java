package it.polimi.ingsw.network.TCP;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SenderTCP{
    private ObjectOutputStream out;
    private Client client;

    public SenderTCP(ObjectOutputStream output, Client client) {
        this.out = output;
        this.client = client;
    }

    public void sendMessage(Message msg) {
        try {
            out.writeObject(msg);
            out.flush();
            out.reset();
        } catch (IOException e) {
            client.manageDisconnection();
        }
    }

}
