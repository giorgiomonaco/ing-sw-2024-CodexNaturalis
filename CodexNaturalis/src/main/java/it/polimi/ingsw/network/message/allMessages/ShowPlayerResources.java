package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class ShowPlayerResources extends Message {
    int [] Resources;

    public ShowPlayerResources(messEnum messType, String senderUsername, int[] Resources){
        super(messType, senderUsername);
        this.Resources= Resources;
    }

    public int[] getResources() {
        return Resources;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}


