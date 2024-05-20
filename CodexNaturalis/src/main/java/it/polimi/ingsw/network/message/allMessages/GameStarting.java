package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.GameStartHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.ResourceCard;

import java.util.List;

public class GameStarting extends Message {

    private List<Card> playerHand;

    public GameStarting(String senderUsername, List<Card> playerHand) {
        super(messEnum.GAME_STARTING, senderUsername);
        this.playerHand = playerHand;
    }

    public GameStarting(String senderUsername, String optDescription) {
        super(messEnum.GAME_STARTING, senderUsername, optDescription);
    }

    @Override
    public MessageHandler createHandler() {
        return new GameStartHandler();
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }
}
