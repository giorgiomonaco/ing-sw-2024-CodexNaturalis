package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.GameStarting;

public class GameStartHandler implements MessageHandler {
    @Override
    public void handle(Message msg, Client client) {
        client.setCurrentState(stateEnum.GAME_STARTED);

        GameStarting game = (GameStarting) msg;
        client.setPlayerHand(game.getPlayerHand());

        client.getUI().run();
    }
}
