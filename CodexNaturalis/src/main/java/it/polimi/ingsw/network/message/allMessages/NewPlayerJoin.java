package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

import java.util.List;

public class NewPlayerJoin extends Message {

    private List<String> playersInLobby;
    public NewPlayerJoin(String senderUsername) {
        super(messEnum.NEW_PLAYER_JOIN, senderUsername);
    }

    public NewPlayerJoin(String senderUsername, String optDescription, List<String> players) {
        super(messEnum.NEW_PLAYER_JOIN, senderUsername, optDescription);
        this.playersInLobby = players;
    }

    public List<String> getPlayersInLobby() {
        return playersInLobby;
    }
}
