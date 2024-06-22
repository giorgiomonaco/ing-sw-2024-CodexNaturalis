package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;

public class GameStoppedHandler implements MessageHandler{
    @Override
    public void manage(Message msg, Client client) {
        client.setCurrentState(stateEnum.GAME_STOPPED);
        client.getUI().run();
    }
}
