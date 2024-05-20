package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.FirstTurnHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.ObjectiveCard;


import java.util.List;

public class FirstTurn extends Message {

    List<String> listOfTokens;
    List<ObjectiveCard> listOfPersonalObjCards;

    public FirstTurn(String senderUsername) {
        super(messEnum.FIRST_TURN, senderUsername);
    }

    public FirstTurn(String senderUsername, String optDescription) {
        super(messEnum.FIRST_TURN, senderUsername, optDescription);
    }

    @Override
    public MessageHandler createHandler() {
        return new FirstTurnHandler();
    }
}
