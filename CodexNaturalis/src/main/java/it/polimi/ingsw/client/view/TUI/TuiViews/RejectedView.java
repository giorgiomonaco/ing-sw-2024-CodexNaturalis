package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class RejectedView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("Sorry, the game is full.");
    }
}
