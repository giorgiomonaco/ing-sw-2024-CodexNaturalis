package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.ShowWinnerHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Player;

public class ShowWinnerMessage extends Message {
    private boolean win;
    private String winner;
    public ShowWinnerMessage(String senderUsername, boolean win, String winner) {
        super(messEnum.SHOW_WINNER, senderUsername);
        this.win = win;
        this.winner = winner;
    }

    @Override
    public MessageHandler genHandler() {
        return new ShowWinnerHandler();
    }
    public boolean getWin(){
        return win;
    }
    public String getName(){
        return winner;
    }
}
