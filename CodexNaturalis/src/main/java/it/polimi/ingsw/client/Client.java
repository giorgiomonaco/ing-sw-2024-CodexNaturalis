package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.server.model.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public abstract class Client extends UnicastRemoteObject implements Serializable {

    private InitialCard Init;
    private String username;
    private UserInterface UI;
    private stateEnum currentState;
    private List<String> playerList = new ArrayList<>();
    private List<Card> playerHand = new ArrayList<>();
    private List<ObjectiveCard> playerObjective = new ArrayList<>();
    private List<String> availableTokens = new ArrayList<>();
    private boolean admin = false;
    private Boards boards;
    private int[] resources;
    private List<Card> visibleGoldCards = new ArrayList<>();
    private List<Card> visibleResourceCards = new ArrayList<>();
    private boolean winner = false;

    private List<Chat> chat;


    protected Client() throws RemoteException {
    }


    public stateEnum getCurrentState() {
        return currentState;
    }

    public void setCurrentState(stateEnum currentState) {
        this.currentState = currentState;
    }

    public void sendMessage(Message msg) throws RemoteException {}

    public void setUI(UserInterface UI) {
        this.UI = UI;
    }

    public UserInterface getUI() {
        return UI;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void manageMessage(Message msg) {
        if(msg.getType().equals(messEnum.PING)){

        } else {
            callHandler(msg);
        }
    }

    public void callHandler(Message msg){
        msg.createHandler().handle(msg,this);
    }

    public List<String> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<String> playerList) {
        this.playerList = playerList;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public List<ObjectiveCard> getPlayerObjective() {
        return playerObjective;
    }

    public void setPlayerObjective(List<ObjectiveCard> playerObjective) {
        this.playerObjective = playerObjective;
    }

    public List<String> getAvailableTokens() {
        return availableTokens;
    }

    public void setAvailableTokens(List<String> availableTokens) {
        this.availableTokens = availableTokens;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public InitialCard getInit() {
        return Init;
    }

    public void setInit(InitialCard init) {
        Init = init;
    }

    public Boards getBoards() {
        return boards;
    }

    public void setBoards(Boards boards) {
        this.boards = boards;
    }

    public int[] getResources() {
        return resources;
    }

    public void setResources(int[] resources) {
        this.resources = resources;
    }
    public void setVisibleGoldCards(List<Card> visibleGoldCards) {
        this.visibleGoldCards = visibleGoldCards;
    }
    public List<Card> getVisibleGoldCards() {
        return visibleGoldCards;
    }
    public void setVisibleResourceCards(List<Card> visibleResourceCards) {
        this.visibleResourceCards = visibleResourceCards;
    }
    public List<Card> getVisibleResourceCards() {
        return visibleResourceCards;
    }

    public void setWinner(boolean win) {
        this.winner = win;
    }

    public boolean getWinner() {
        return winner;
    }

    public List<Chat> getChat() {
        return chat;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }
}
