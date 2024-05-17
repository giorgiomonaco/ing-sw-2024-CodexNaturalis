package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class WaitingLobbyView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("Waiting for the creation of the lobby...");
    }
}
