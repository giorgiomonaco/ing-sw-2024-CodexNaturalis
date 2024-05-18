package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.server.model.Card;

import java.util.List;

public interface TuiView {
    void play();

    void viewUncoveredCards(List<Card> cardList);
}
