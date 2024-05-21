package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.PlayCardResponse;
import it.polimi.ingsw.client.states.stateEnum;

public class PlayCardHandler implements MessageHandler {
    @Override
    public void handle(Message msg, Client client) {
        PlayCardResponse response = (PlayCardResponse) msg;
        client.setCurrentState(stateEnum.PLAY_CARD_RESPONSE);
        client.setBoards(response.getBoards());
        client.setPlayerHand(response.getCard());
        client.setResources(response.getResources());
        client.getUI().run();
    }
}
