package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.Card;

import java.util.List;

public interface UserInterface {
    void run();
    void endGame();
    void error();
    void showMessage();

    void viewCards(List<Card> playerHand);
    void viewCard(Card card);

}
