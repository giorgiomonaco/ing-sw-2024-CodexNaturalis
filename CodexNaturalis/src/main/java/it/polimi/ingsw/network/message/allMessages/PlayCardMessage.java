package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class PlayCardMessage extends Message {
    List<Card> card;

    public PlayCardMessage(messEnum messType, String senderUsername, List<Card> list){
        super(messType, senderUsername);
        this.card = list;
    }

    public List<Card> getCard() {
        return card;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}
