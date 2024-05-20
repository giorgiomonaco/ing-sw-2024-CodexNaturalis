package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.TUI.Tui;

public class GameStartedView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("\n+----------------------+\n" +
                "|     GAME STARTED     |\n" +
                "+----------------------+\n\nHere are your playable cards:\n");
        for(int i = 0; i < 3; i++) {
            Tui view = (Tui) client.getUI();
            view.printCard(client.getPlayerHand().get(i));
        }
    }
}
