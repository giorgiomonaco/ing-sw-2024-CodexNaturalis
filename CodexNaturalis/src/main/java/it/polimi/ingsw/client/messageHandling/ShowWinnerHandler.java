package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ShowWinnerMessage;

public class ShowWinnerHandler implements MessageHandler {
    @Override
    public void handle(Message message, Client client) {
        ShowWinnerMessage showWinnerMessage = (ShowWinnerMessage) message;
        client.setCurrentState(stateEnum.SHOW_WINNER);
        client.setWinner(showWinnerMessage.getWin());
        client.getUI().run();
        System.exit(1);
    }
}
