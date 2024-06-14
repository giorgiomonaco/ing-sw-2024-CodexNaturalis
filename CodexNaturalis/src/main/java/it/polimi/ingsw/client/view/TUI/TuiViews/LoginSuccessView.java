package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

public class LoginSuccessView implements TuiView {
    @Override
    public void play(Client client) {
        System.out.println("\nLogin successful!");
    }
}
