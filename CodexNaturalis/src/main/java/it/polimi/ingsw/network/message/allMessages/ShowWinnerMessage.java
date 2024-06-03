package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.ShowWinnerHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Player;

public class ShowWinnerMessage extends Message {
    boolean win;
    public ShowWinnerMessage(String senderUsername, boolean win) {
        super(messEnum.SHOW_WINNER, senderUsername);
        this.win = win;
    }

    @Override
    public MessageHandler createHandler() {
        return new ShowWinnerHandler();
    }
    public boolean getWin(){
        return win;
    }
}
