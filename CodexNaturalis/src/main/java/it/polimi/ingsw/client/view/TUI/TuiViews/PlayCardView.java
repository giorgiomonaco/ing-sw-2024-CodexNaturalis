package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class PlayCardView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("CHOOSE A CARD TO PLAY");
    }
}
