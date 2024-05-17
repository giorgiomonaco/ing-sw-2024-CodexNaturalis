package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class LobbyView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("WAITING FOR OTHER PLAYERS TO JOIN THE GAME");
    }
}
