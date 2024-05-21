package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.PlayCardHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class PlayCardReq extends Message {
    List<Card> card;
    Boards boards;
    int[] resources;

    public PlayCardReq(String senderUsername, List<Card> list, Boards boards, int[] resources) {
        super(messEnum.PLAY_CARD_REQUEST, senderUsername);
        this.card = list;
        this.boards = boards;
        this.resources = resources;
    }

    public List<Card> getCard() {
        return card;
    }

    public Boards getBoards() {
        return boards;
    }

    @Override
    public MessageHandler createHandler() {
        return new PlayCardHandler();
    }

    public int[] getResources() {
        return resources;
    }
}
