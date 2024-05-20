package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;

import java.util.List;

public class ShowFirst extends Message {

    InitialCard card;

    public ShowFirst(messEnum messType, String senderUsername, InitialCard card) {
        super(messType, senderUsername);
        this.card = card;
    }


    @Override
    public MessageHandler createHandler() {
        return null;
    }

    {
    }

    public InitialCard getCard() {
        return card;
    }
}