package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class ShowHandRequest extends Message {
    public ShowHandRequest(messEnum messType, String senderUsername) {
        super(messType, senderUsername);
    }

}
