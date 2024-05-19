package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class CommonMessage extends Message {

    public CommonMessage(String senderUsername, String optDescription) {
        super(messEnum.COMMON, senderUsername, optDescription);
    }


}
