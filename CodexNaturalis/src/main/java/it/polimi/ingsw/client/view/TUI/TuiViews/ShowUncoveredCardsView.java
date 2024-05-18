package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.server.model.Card;

import java.io.Serializable;
import java.util.List;

public class ShowUncoveredCardsView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("Here are the uncovered cards:");
    }

    public void viewUncoveredCards(List<Card> cardList) {
        //todo
    }
}
