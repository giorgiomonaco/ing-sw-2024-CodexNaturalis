package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.GameStarting;

public class GameSetUpHandler implements MessageHandler {
    @Override
    public void manage(Message msg, Client client) {

        client.setCurrentState(stateEnum.GAME_SETUP);

        GameStarting game = (GameStarting) msg;
        client.setPlayerHand(game.getPlayerHand());
        client.setInit(game.getCard());
        client.setCommonObjectives(game.getCommonObjectives());

        client.getUI().run();

    }
}
