package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class LobbyView implements TuiView{
    @Override
    public void play() {
        System.out.println("\n+----------------------+\n" +
                "|        LOBBY         |\n" +
                "+----------------------+\n");
    }
}
