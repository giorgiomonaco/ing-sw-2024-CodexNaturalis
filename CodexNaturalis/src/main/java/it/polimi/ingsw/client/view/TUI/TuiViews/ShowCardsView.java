package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.server.model.Card;

import java.io.Serializable;
import java.util.List;

public class ShowCardsView implements TuiView, Serializable {
    List<Card> playerHand;

    public void play(List<Card> list) {
        this.playerHand = list;

    }

    @Override
    public void play() {


    }
}
