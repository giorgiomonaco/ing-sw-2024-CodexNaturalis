package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.DrawCardHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class DrawCardRequest extends Message {

    List<Card> goldCards;
    List<Card> resourceCards;

    @Override
    public MessageHandler createHandler() {
        return new DrawCardHandler();
    }

    public DrawCardRequest(String senderUsername, List<Card> list, List<Card> list2) {
        super(messEnum.DRAW_CARD_REQUEST, senderUsername);
        goldCards = list;
        resourceCards = list2;
    }

    public List<Card> getGoldCards() {
        return goldCards;
    }

    public List<Card> getResourceCards() {
        return resourceCards;
    }

    public void setGoldCards(List<Card> goldCards) {
        this.goldCards = goldCards;
    }

    public void setResourceCards(List<Card> resourceCards) {
        this.resourceCards = resourceCards;
    }
}
