package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;

import java.util.List;

public class ShowFirstView implements TuiView{
    InitialCard card;

    public void play(InitialCard card) {
        this.card = card;
    }

    @Override
    public void play(Client client) {
        System.out.println("\nHere is your initial card:\n");

    }
}
