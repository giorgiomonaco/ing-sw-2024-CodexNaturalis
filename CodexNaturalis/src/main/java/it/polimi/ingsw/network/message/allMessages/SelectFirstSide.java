package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.SelFirstCardHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectFirstSide extends Message {

    private String username = new String();

    public SelectFirstSide(messEnum messType, String senderUsername) {
        super(messType, senderUsername);
        this.username=senderUsername;
    }


    @Override
    public MessageHandler createHandler() {
       return new SelFirstCardHandler();
    }
}
