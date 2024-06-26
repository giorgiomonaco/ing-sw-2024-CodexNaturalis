package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.ShowWinnerHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
public class ShowWinnerMessage extends Message {
    private final boolean win;
    private final String winner;
    private final int[] points;

    public ShowWinnerMessage(String senderUsername, boolean win, String winner, int[] points) {
        super(messEnum.SHOW_WINNER, senderUsername);
        this.win = win;
        this.winner = winner;
        this.points = points;
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

    public int[] getPoints() {
        return points;
    }
}
