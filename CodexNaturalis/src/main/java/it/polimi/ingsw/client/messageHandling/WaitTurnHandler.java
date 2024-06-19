package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.WaitTurnMsg;

public class WaitTurnHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        WaitTurnMsg wait = (WaitTurnMsg) msg;

        client.setPoints(wait.getPoints());
        client.setBoards(wait.getBoards());
        client.setPlayerHand(wait.getCard());
        client.setResources(wait.getResources());
        client.setCurrIndex(wait.getCurrIndex());

        client.setCurrentState(stateEnum.WAITING_TURN);
        client.getUI().run();
    }
}
