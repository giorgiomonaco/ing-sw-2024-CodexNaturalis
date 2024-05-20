package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;

public class AlreadyStartedView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("\n+--------------------------+\n" +
                "|   GAME ALREADY STARTED   |\n" +
                "+--------------------------+\n\nSorry, the game you are trying to join is already started, \nor the max number of player is already reached, wait for this game to finish.");
        System.exit(0);
    }
}
