package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class AlreadyStartedView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("GAME ALREADY STARTED");
        System.out.println("Sorry, the game you are trying to join is already started, \nor the max number of player is already reached, wait for this game to finish.");
        System.exit(0);
    }
}
