package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class WaitTurnView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("WAIT FOR YOUR TURN");
    }
}
