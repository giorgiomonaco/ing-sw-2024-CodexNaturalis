package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class ShowCards extends Message {
    List<Card> playerHand;
    public ShowCards(messEnum messType, String senderUsername, List<Card> playerHand) {
        super(messType, senderUsername);
        this.playerHand = playerHand;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}
