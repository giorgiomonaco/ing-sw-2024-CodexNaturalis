package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class DrawCardRequest extends Message {
    String whereToDraw;
    int cardIndex;
    public DrawCardRequest(messEnum messType, String senderUsername) {
        super(messType, senderUsername);
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }

    public DrawCardRequest(messEnum messType, String senderUsername, String whereToDraw, int cardIndex) {
        super(messType, senderUsername);
        this.cardIndex = cardIndex;
        this.whereToDraw = whereToDraw;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public String getWhereToDraw() {
        return whereToDraw;
    }
}
