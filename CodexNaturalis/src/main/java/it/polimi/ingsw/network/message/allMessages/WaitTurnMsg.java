package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.WaitTurnHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class WaitTurnMsg  extends Message {
    private List<Card> card;
    private Boards boards;
    private int[] resources;
    private int points = -1;

    public WaitTurnMsg(String senderUsername) {
        super(messEnum.WAIT_TURN, senderUsername);
    }

    public WaitTurnMsg(String senderUsername, List<Card> list, Boards boards, int[] resources, int points) {
        super(messEnum.WAIT_TURN, senderUsername);
        this.card = list;
        this.boards = boards;
        this.resources = resources;
        this.points = points;
    }

    @Override
    public MessageHandler createHandler() {
        return new WaitTurnHandler();
    }

    public int getPoints() {
        return points;
    }

    public int[] getResources() {
        return resources;
    }

    public Boards getBoards() {
        return boards;
    }

    public List<Card> getCard() {
        return card;
    }
}
