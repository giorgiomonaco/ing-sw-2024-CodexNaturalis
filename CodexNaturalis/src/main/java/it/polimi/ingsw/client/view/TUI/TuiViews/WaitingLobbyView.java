package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;

public class WaitingLobbyView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("Waiting for the creation of the lobby...");
    }
}
