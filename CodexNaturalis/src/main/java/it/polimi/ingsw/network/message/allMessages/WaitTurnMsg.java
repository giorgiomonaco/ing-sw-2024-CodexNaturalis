package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.WaitTurnHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;
import it.polimi.ingsw.server.model.ObjectiveCard;

import java.util.List;

public class WaitTurnMsg  extends Message {
    private List<Card> card;
    private Boards boards;
    private int[] resources;
    private int points;
    private int currIndex;
    private List<ObjectiveCard> common;
    private ObjectiveCard personal;
    private InitialCard init = null;
    private List<String> userList;

    public WaitTurnMsg(String senderUsername, List<Card> list, Boards boards, int[] resources, int points, int currentIndex) {
        super(messEnum.WAIT_TURN, senderUsername);
        this.card = list;
        this.boards = boards;
        this.resources = resources;
        this.points = points;
        this.currIndex = currentIndex;
    }

    public WaitTurnMsg(String senderUsername, List<ObjectiveCard> common, List<String> userList, ObjectiveCard personal, InitialCard init){
        super(messEnum.WAIT_TURN, senderUsername);
        this.common = common;
        this.personal = personal;
        this.init = init;
        this.userList = userList;
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

    public int getCurrIndex() {
        return currIndex;
    }

    public List<ObjectiveCard> getCommon() {
        return common;
    }

    public ObjectiveCard getPersonal() {
        return personal;
    }

    public InitialCard getInit() {
        return init;
    }

    public List<String> getUserList() {
        return userList;
    }
}
