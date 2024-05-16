package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class AlreadyStarted extends Message {
    public AlreadyStarted(String senderUsername) {
        super(messEnum.ALREADY_STARTED, senderUsername);
    }

    public AlreadyStarted(String senderUsername, String optDescription) {
        super(messEnum.ALREADY_STARTED, senderUsername, optDescription);
    }
}
