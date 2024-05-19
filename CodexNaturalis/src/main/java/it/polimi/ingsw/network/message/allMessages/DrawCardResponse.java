package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

public class DrawCardResponse extends Message {
    private final Card card;

    public DrawCardResponse(messEnum messType, String senderUsername, Card card) {
        super(messType, senderUsername);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}
