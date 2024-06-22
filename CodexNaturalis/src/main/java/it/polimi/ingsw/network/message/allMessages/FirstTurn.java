package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.FirstTurnHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.ObjectiveCard;
import java.util.List;

public class FirstTurn extends Message {

    private final List<String> listOfTokens;
    private final List<ObjectiveCard> listOfPersonalObjCards;

    public FirstTurn(String senderUsername, List<String> tokens, List<ObjectiveCard> cards) {
        super(messEnum.FIRST_TURN, senderUsername);
        this.listOfTokens = tokens;
        this.listOfPersonalObjCards = cards;
    }

    @Override
    public MessageHandler genHandler() {
        return new FirstTurnHandler();
    }

    public List<ObjectiveCard> getListOfPersonalObjCards() {
        return listOfPersonalObjCards;
    }

    public List<String> getListOfTokens() {
        return listOfTokens;
    }


}
