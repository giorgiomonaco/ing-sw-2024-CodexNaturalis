package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GameBoard;

import java.util.List;

public interface UserInterface {
    void run();
    void endGame();
    void error();
    void showMessage();

    void viewCards(List<Card> playerHand);
    void viewCard(Card card);
    void viewResources(int[] resources);
    void viewBoard(GameBoard gameboard);

}
