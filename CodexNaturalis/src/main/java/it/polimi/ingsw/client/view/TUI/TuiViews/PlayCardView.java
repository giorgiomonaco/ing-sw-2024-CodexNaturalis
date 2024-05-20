package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;

public class PlayCardView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("CHOOSE A CARD TO PLAY");
    }
}
