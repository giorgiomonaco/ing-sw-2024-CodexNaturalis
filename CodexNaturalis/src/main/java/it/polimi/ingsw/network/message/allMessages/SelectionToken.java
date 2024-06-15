package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectionToken extends Message {

    public SelectionToken(String senderUsername, String optDescription) {
        super(messEnum.SELECTION_TOKEN, senderUsername, optDescription);
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}
