package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;

public class DrawCardResponse extends Message {
    private final int choice;

    public DrawCardResponse(String senderUsername, int choice) {
        super(messEnum.DRAW_CARD_RESPONSE, senderUsername);
        this.choice = choice;
    }

    public int getChoice() {
        return choice;

    }

    @Override
    public MessageHandler genHandler() {
        return null;
    }
}
