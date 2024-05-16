package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class RejectedMessage extends Message {
    public RejectedMessage(String senderUsername) {
        super(messEnum.REJECTED, senderUsername);
    }

    public RejectedMessage(String senderUsername, String optDescription) {
        super(messEnum.REJECTED, senderUsername, optDescription);
    }
}
