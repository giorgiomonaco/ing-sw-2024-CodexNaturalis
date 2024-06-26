package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ShowWinnerMessage;

public class ShowWinnerHandler implements MessageHandler {
    @Override
    public void manage(Message message, Client client) {
        ShowWinnerMessage showWinnerMessage = (ShowWinnerMessage) message;
        client.setCurrentState(stateEnum.END_GAME);
        client.setWinnerName(showWinnerMessage.getName());
        client.setWinner(showWinnerMessage.getWin());
        if (showWinnerMessage.getPoints() != null) {
            client.setPoints(showWinnerMessage.getPoints());
        }
        client.getUI().run();
    }
}
