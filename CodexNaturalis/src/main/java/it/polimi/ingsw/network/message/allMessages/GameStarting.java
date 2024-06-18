package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.GameStartHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;
import it.polimi.ingsw.server.model.ObjectiveCard;
import it.polimi.ingsw.server.model.ResourceCard;

import java.util.ArrayList;
import java.util.List;

public class GameStarting extends Message {

    private List<Card> playerHand = new ArrayList<>();
    private InitialCard card;
    private List<ObjectiveCard> commonObjectives = new ArrayList<>();
    private boolean admin;

    public GameStarting(String senderUsername, boolean admin, List<Card> playerHand, InitialCard card, List<ObjectiveCard> commonObjectives) {
        super(messEnum.GAME_STARTING, senderUsername);
        this.playerHand = playerHand;
        this.card = card;
        this.admin = admin;
        this.commonObjectives = commonObjectives;
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

    public InitialCard getCard() {
        return card;
    }

    public boolean isAdmin() {
        return admin;
    }

    public List<ObjectiveCard> getCommonObjectives() {
        return commonObjectives;
    }
}
