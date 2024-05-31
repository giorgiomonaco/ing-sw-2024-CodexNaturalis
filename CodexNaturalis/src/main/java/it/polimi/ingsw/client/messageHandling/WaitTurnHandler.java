package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;

public class WaitTurnHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        WaitTurnHandler waitTurnHandler = new WaitTurnHandler();
        client.setCurrentState(stateEnum.WAITING_TURN);
        client.getUI().run();
    }
}
