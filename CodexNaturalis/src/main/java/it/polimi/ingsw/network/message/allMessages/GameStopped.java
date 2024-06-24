package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.GameStoppedHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class GameStopped extends Message {
    public GameStopped(String senderUsername) {
        super(messEnum.GAME_STOPPED, senderUsername);
    }

    @Override
    public MessageHandler genHandler() {
        return new GameStoppedHandler();
    }
}
