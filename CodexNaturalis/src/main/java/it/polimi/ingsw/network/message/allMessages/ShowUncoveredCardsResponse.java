package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class ShowUncoveredCardsResponse extends Message {
    private List<Card> cardList;

    public ShowUncoveredCardsResponse(messEnum messType, String senderUsername, List<Card> cardList) {
        super(messType, senderUsername);
        this.cardList = cardList;
    }
    public List<Card> getCardList() {
        return cardList;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}
