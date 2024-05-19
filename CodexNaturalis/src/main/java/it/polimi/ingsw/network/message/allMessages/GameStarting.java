package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

import java.util.List;

public class GameStarting extends Message {


    public GameStarting(String senderUsername) {
        super(messEnum.GAME_STARTING, senderUsername);
    }

    public GameStarting(String senderUsername, String optDescription) {
        super(messEnum.GAME_STARTING, senderUsername, optDescription);
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}
