package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.SelectNumPlayersHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectNumPlayers extends Message {
    public SelectNumPlayers(String senderUsername) {
        super(messEnum.SELECT_NUM_PLAYERS, senderUsername);
    }

    public SelectNumPlayers(String senderUsername, String optDescription) {
        super(messEnum.SELECT_NUM_PLAYERS, senderUsername, optDescription);
    }

    @Override
    public MessageHandler createHandler() {
        return new SelectNumPlayersHandler();
    }
}
