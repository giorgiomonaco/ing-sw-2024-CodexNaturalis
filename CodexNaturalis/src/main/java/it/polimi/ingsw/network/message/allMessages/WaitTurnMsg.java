package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.WaitTurnHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class WaitTurnMsg  extends Message {
    public WaitTurnMsg(String senderUsername) {
        super(messEnum.WAIT_TURN, senderUsername);
    }

    @Override
    public MessageHandler createHandler() {
        return new WaitTurnHandler();
    }
}
