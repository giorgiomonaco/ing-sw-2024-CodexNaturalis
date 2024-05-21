package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class ShowFirstRequest extends Message {
    public ShowFirstRequest(messEnum messEnum, String username) {
            super(messEnum, username);
        }

        @Override
        public MessageHandler createHandler() {
            return null;
        }

    }