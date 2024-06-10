package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

public class ShowWinnerView implements TuiView {
    @Override
    public void play(Client client) {
        if (client.getWinner()) {
            System.out.println("YOU WIN");
        } else {
            System.out.println("YOU LOSE");
        }
    }
}
