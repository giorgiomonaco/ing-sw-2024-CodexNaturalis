package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class PingMessage extends Message {
    public PingMessage(String senderUsername) {
        super(messEnum.PING, senderUsername);
    }

    public PingMessage(String senderUsername, String optDescription) {
        super(messEnum.PING, senderUsername, optDescription);
    }

    @Override
    public MessageHandler genHandler() {
        return null;
    }
}
