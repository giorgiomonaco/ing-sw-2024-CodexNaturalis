package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class ConnectionActive extends Message {
    public ConnectionActive(String senderUsername) {
        super(messEnum.CONNECTION_ACTIVE, senderUsername);
    }

    public ConnectionActive(String senderUsername, String optDescription) {
        super(messEnum.CONNECTION_ACTIVE, senderUsername, optDescription);
    }

    @Override
    public MessageHandler genHandler() {
        return null;
    }
}
