package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

public class NotAllowedView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("PERMISSION DENIED!");
        System.out.println("You are not allowed to do this command now.");
    }
}
