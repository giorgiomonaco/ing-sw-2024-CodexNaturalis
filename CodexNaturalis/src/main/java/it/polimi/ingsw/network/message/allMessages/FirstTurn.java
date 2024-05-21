package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.FirstTurnHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.InitialCard;
import it.polimi.ingsw.server.model.ObjectiveCard;


import java.util.ArrayList;
import java.util.List;

public class FirstTurn extends Message {

    private List<String> listOfTokens = new ArrayList<>();
    private List<ObjectiveCard> listOfPersonalObjCards;
    private boolean admin = false;

    public FirstTurn(String senderUsername, List<String> tokens, List<ObjectiveCard> cards) {
        super(messEnum.FIRST_TURN, senderUsername);
        this.listOfTokens = tokens;
        this.listOfPersonalObjCards = cards;
    }

    public FirstTurn(String senderUsername, String token, List<ObjectiveCard> cards) {
        super(messEnum.FIRST_TURN, senderUsername);
        this.listOfTokens.add(token);
        this.listOfPersonalObjCards = cards;
        this.admin = true;
    }


    public FirstTurn(String senderUsername, String optDescription) {
        super(messEnum.FIRST_TURN, senderUsername, optDescription);
    }

    @Override
    public MessageHandler createHandler() {
        return new FirstTurnHandler();
    }

    public List<ObjectiveCard> getListOfPersonalObjCards() {
        return listOfPersonalObjCards;
    }

    public List<String> getListOfTokens() {
        return listOfTokens;
    }

    public boolean isAdmin() {
        return admin;
    }

}
