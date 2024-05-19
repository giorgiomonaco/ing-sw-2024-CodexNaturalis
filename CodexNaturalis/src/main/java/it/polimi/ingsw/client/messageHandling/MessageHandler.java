package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.Message;

public interface MessageHandler {
    public void handle(Message msg, Client client);
}
