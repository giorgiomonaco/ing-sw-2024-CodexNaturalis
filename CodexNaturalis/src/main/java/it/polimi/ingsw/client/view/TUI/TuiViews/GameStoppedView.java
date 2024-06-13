package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

public class GameStoppedView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("\n-- GAME STOPPED --");
        System.out.println("If nobody rejoin the game in 30 seconds, you will win the game.\n");
    }
}
