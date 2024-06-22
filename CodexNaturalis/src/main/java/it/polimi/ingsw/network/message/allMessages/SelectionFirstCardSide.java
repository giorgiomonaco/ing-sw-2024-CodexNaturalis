package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.messageHandling.SelFirstCardHandler;
import it.polimi.ingsw.client.messageHandling.SelectObjectiveHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class SelectionFirstCardSide extends Message {

    private String selection;

    public SelectionFirstCardSide(String senderUsername, String selection) {
    super(messEnum.SELECTION_FIRSTCARD, senderUsername);
    this.selection = selection;
}



    @Override
    public MessageHandler genHandler() {
        return new SelectObjectiveHandler();
    }

    public String getSelection() {
        return selection;
    }
}

