package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectingNumPlayers extends Message {

    private final int numOfPlayers;
    public SelectingNumPlayers(String senderUsername, int numOfPlayers) {
        super(messEnum.SELECTING_NUM_PLAYERS, senderUsername);
        this.numOfPlayers = numOfPlayers;
    }

    public SelectingNumPlayers(String senderUsername, int numOfPlayers, String optDescription) {
        super(messEnum.SELECTING_NUM_PLAYERS, senderUsername, optDescription);
        this.numOfPlayers = numOfPlayers;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

}
