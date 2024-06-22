package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.NewPlayerJoinHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

import java.util.List;

public class NewPlayerJoin extends Message {

    private String newUser;
    public NewPlayerJoin(String senderUsername) {
        super(messEnum.NEW_PLAYER_JOIN, senderUsername);
    }

    public NewPlayerJoin(String senderUsername, String optDescription, String newUser) {
        super(messEnum.NEW_PLAYER_JOIN, senderUsername, optDescription);
        this.newUser = newUser;
    }

    public String getNewUser() {
        return newUser;
    }

    @Override
    public MessageHandler genHandler() {
        return new NewPlayerJoinHandler();
    }
}
