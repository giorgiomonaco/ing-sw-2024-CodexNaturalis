package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class LobbyCreation extends Message {
    public LobbyCreation(String senderUsername) {
        super(messEnum.LOBBY, senderUsername);
    }

    public LobbyCreation(String senderUsername, String optDescription) {
        super(messEnum.LOBBY, senderUsername, optDescription);
    }
}
