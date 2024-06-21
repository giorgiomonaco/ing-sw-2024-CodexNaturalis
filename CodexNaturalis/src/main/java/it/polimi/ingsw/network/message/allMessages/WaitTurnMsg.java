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
    private int[] points;
    private int currIndex;
    private List<ObjectiveCard> common;
    private ObjectiveCard personal;
    private InitialCard init = null;
    private List<String> userList;
    private Card[][] board;
    private final int flag;
    private List<String> tokens;

    // Constructor for the end of the personal turn
    public WaitTurnMsg(String senderUsername, List<Card> list, Boards boards, int[] resources, int[] points) {
        super(messEnum.WAIT_TURN, senderUsername);
        flag = 1;
        this.card = list;
        this.boards = boards;
        this.resources = resources;
        this.points = points;
    }

    // Constructor for the end of the other players turn
    public WaitTurnMsg(String senderUsername, Card[][] board, int[] points) {
        super(messEnum.WAIT_TURN, senderUsername);
        flag = 2;
        this.board = board;
        this.points = points;
    }

    // Constructor for the end of the other players first turn
    public WaitTurnMsg(String senderUsername, Card[][] board, int[] points, List<String> tokens) {
        super(messEnum.WAIT_TURN, senderUsername);
        flag = 5;
        this.board = board;
        this.points = points;
        this.tokens = tokens;
    }

    // Constructor to set the right turn
    public WaitTurnMsg(String senderUsername, int currIndex) {
        super(messEnum.WAIT_TURN, senderUsername);
        flag = 3;
        this.currIndex = currIndex;
    }

    // Constructor for the manage of the reconnection
    public WaitTurnMsg(String senderUsername, List<ObjectiveCard> common, List<String> userList, ObjectiveCard personal, InitialCard init, List<String> tokens){
        super(messEnum.WAIT_TURN, senderUsername);
        flag = 4;
        this.common = common;
        this.personal = personal;
        this.init = init;
        this.userList = userList;
        this.tokens = tokens;
    }

    @Override
    public MessageHandler createHandler() {
        return new WaitTurnHandler();
    }

    public int[] getPoints() {
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

    public Card[][] getBoard() {
        return board;
    }

    public int getFlag() {
        return flag;
    }

    public List<String> getTokens() {
        return tokens;
    }
}
