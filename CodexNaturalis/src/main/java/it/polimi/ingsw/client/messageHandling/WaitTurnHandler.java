package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.WaitTurnMsg;

public class WaitTurnHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        WaitTurnMsg wait = (WaitTurnMsg) msg;

        if (wait.getFlag() == 1) {
            client.setPoints(wait.getPoints());
            client.setBoards(wait.getBoards());
            client.setPlayerHand(wait.getCard());
            client.setResources(wait.getResources());
            client.setCurrIndex(wait.getCurrIndex());
        } else if (wait.getFlag() == 2) {
            client.setGameBoards(wait.getBoard(), client.getCurrIndex());
            client.setPoints(wait.getPoints());
        } else if (wait.getFlag() == 3) {
            client.setCurrIndex(wait.getCurrIndex());
        } else {
            client.setInit(wait.getInit());
            client.setCommonObjectives(wait.getCommon());
            client.setObjective(wait.getPersonal());
            client.setPlayerList(wait.getUserList());
        }

        client.setCurrentState(stateEnum.WAITING_TURN);
        client.getUI().run();
    }
}
