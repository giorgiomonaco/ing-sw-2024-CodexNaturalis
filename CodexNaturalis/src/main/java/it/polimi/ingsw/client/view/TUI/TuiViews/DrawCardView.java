package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.server.model.Card;

import java.io.Serializable;

public class DrawCardView implements TuiView, Serializable {

    @Override
    public void play() {
        System.out.println("CHOOSE A CARD TO DRAW");
    }

    public void response(Card card) {
        //TODO
    }
}
