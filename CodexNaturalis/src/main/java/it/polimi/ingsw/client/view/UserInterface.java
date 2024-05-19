package it.polimi.ingsw.client.view;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public interface UserInterface {
    void run();
    void endGame();
    void printErrorMessage(Message msg);
    void printMessage(Message msg);

    void viewCards(List<Card> playerHand);
    void viewCard(Card card);


}
