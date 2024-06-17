package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.DrawCardHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class DrawCardRequest extends Message {

    private List<Card> goldCards;
    private List<Card> resourceCards;
    private String goldDeck;
    private String resDeck;

    @Override
    public MessageHandler createHandler() {
        return new DrawCardHandler();
    }

    public DrawCardRequest(String senderUsername, List<Card> list, List<Card> list2, String gold, String res) {
        super(messEnum.DRAW_CARD_REQUEST, senderUsername);
        this.goldCards = list;
        this.resourceCards = list2;
        this.goldDeck = gold;
        this.resDeck = res;
    }

    public List<Card> getGoldCards() {
        return goldCards;
    }

    public List<Card> getResourceCards() {
        return resourceCards;
    }

    public String getGoldDeck() {
        return goldDeck;
    }

    public String getResDeck() {
        return resDeck;
    }
}
