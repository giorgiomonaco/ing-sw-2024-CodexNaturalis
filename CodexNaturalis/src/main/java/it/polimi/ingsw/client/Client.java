package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.server.model.Card;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public abstract class Client extends UnicastRemoteObject implements Serializable {

    private String username;
    private UserInterface UI;
    private stateEnum currentState;
    private List<String> playerList = new ArrayList<>();
    private List<Card> playerHand = new ArrayList<>();


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

    public void manageState(Message msg){

        switch(msg.getType()){
            case SHOW_CARD:
                ShowCards showMsg = (ShowCards) msg;
                getUI().viewCards(showMsg.getPlayerHand());
                break;
            case DRAW_CARD_RESPONSE:
                DrawCardResponse drawMsg = (DrawCardResponse) msg;
                getUI().viewCard(drawMsg.getCard());
                break;
            // case SHOW_UNCOVERED_CARDS_RESPONSE:
            //    ShowUncoveredCardsResponse uncoveredMsg = (ShowUncoveredCardsResponse) msg;
            //    getUI().viewUncoveredCards(uncoveredMsg.getCardList());
            //    break;

        }
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
}
