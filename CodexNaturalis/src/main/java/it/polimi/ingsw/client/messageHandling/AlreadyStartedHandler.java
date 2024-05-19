package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;

public class AlreadyStartedHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        client.setCurrentState(stateEnum.ALREADY_STARTED);
        client.getUI().run();
    }
}
