package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.FirstTurn;

public class FirstTurnHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        FirstTurn firstTurn = (FirstTurn) msg;
        client.setPlayerObjective(firstTurn.getListOfPersonalObjCards());
        client.setAvailableTokens(firstTurn.getListOfTokens());
        client.setAdmin(firstTurn.isAdmin());
        client.setCurrentState(stateEnum.SELECT_TOKEN);
        client.getUI().run();
    }
}
