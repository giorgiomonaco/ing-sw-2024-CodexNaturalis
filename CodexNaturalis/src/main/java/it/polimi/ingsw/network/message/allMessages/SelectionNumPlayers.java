package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectionNumPlayers extends Message {

    private final int numOfPlayers;
    public SelectionNumPlayers(String senderUsername, int numOfPlayers) {
        super(messEnum.SELECTION_NUM_PLAYERS, senderUsername);
        this.numOfPlayers = numOfPlayers;
    }

    public SelectionNumPlayers(String senderUsername, int numOfPlayers, String optDescription) {
        super(messEnum.SELECTION_NUM_PLAYERS, senderUsername, optDescription);
        this.numOfPlayers = numOfPlayers;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

}
