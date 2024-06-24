package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.PlayCardReq;
import it.polimi.ingsw.client.states.stateEnum;

public class PlayCardHandler implements MessageHandler {
    @Override
    public void manage(Message msg, Client client) {
        PlayCardReq req = (PlayCardReq) msg;
        client.setPoints(req.getPoints());
        client.setBoards(req.getBoards());
        client.setPlayerHand(req.getCard());
        client.setResources(req.getResources());
        client.setCurrentState(stateEnum.PLAY_CARD);
        client.getUI().run();
    }
}
