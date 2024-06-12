package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.GameAbortedHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class GameAborted extends Message {
    public GameAborted(String senderUsername) {
        super(messEnum.GAME_ABORTED, senderUsername);
    }

    public GameAborted(String senderUsername, String optDescription) {
        super(messEnum.GAME_ABORTED, senderUsername, optDescription);
    }

    @Override
    public MessageHandler createHandler() {
        return new GameAbortedHandler();
    }
}
