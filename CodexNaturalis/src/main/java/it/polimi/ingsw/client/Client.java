package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.DrawCardResponse;
import it.polimi.ingsw.network.message.allMessages.LoginResponse;
import it.polimi.ingsw.network.message.allMessages.ShowCards;
import it.polimi.ingsw.network.message.allMessages.ShowUncoveredCardsResponse;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.client.states.stateEnum;

import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class Client implements Serializable {
    private stateEnum currentState;
    private String username;
    private UserInterface UI;


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
            manageState(msg);
        }
    }

    public void manageState(Message msg){

        switch(msg.getType()){
            case LOGIN_RESPONSE:
                LoginResponse response = (LoginResponse) msg;
                if(response.getResult() == 1){
                    System.out.println("Login successful!");
                } else {
                    System.out.println("The chosen username is already taken, try to choose another one.");
                }
                break;
            case WAITING_FOR_LOBBY:
                setCurrentState(stateEnum.WAITING_LOBBY);
                getUI().run();
                break;
            case SELECT_NUM_PLAYERS:
                setCurrentState(stateEnum.SELECT_NUM_PLAYERS);
                getUI().run();
                break;
            case REJECTED:
                setCurrentState(stateEnum.REJECTED);
                getUI().run();
                break;
            case ALREADY_STARTED:
                setCurrentState(stateEnum.ALREADY_STARTED);
                getUI().run();
                break;
            case SHOW_CARD:
                ShowCards showMsg = (ShowCards) msg;
                getUI().viewCards(showMsg.getPlayerHand());
                break;
            case DRAW_CARD_RESPONSE:
                DrawCardResponse drawMsg = (DrawCardResponse) msg;
                getUI().viewCard(drawMsg.getCard());
                break;
            case SHOW_UNCOVERED_CARDS_RESPONSE:
                ShowUncoveredCardsResponse uncoveredMsg = (ShowUncoveredCardsResponse) msg;
                getUI().viewUncoveredCards(uncoveredMsg.getCardList());
                break;

        }
    }
}
