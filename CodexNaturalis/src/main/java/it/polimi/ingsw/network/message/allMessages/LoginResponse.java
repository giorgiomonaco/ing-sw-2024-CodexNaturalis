package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class LoginResponse extends Message {
    public LoginResponse(messEnum messType, String senderUsername) {
        super(messType, senderUsername);
    }

    public LoginResponse(messEnum messType, String senderUsername, String optDescription) {
        super(messType, senderUsername, optDescription);
    }
}
