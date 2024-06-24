package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class LoginRequest extends Message {
    public LoginRequest(messEnum messType, String senderUsername) {
        super(messType, senderUsername);
    }

    public LoginRequest(messEnum messType, String senderUsername, String optDescription) {
        super(messType, senderUsername, optDescription);
    }

    @Override
    public MessageHandler genHandler() {
        return null;
    }
}
