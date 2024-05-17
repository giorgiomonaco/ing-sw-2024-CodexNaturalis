package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class AlreadyStartedView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("YOU CANNOT JOIN THE GAME, WAIT FOR THE CURRENT GAME TO END");
    }
}
