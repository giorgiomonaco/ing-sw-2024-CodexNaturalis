package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectionObjCard extends Message {

    private int selection;

    public SelectionObjCard(String senderUsername, int selection) {
        super(messEnum.SELECTION_OBJECTIVE, senderUsername);
        this.selection = selection;
    }

    public SelectionObjCard(String senderUsername, String optDescription, int selection) {
        super(messEnum.SELECTION_OBJECTIVE, senderUsername, optDescription);
        this.selection = selection;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }

    public int getSelection() {
        return selection;
    }
}
