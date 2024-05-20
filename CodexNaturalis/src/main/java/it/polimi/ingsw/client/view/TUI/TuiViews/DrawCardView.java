package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;
import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.server.model.VisibleAngle;

import java.util.Scanner;

public class DrawCardView implements TuiView {

    @Override
    public void play(Client client) {
        System.out.println("CHOOSE A CARD TO DRAW");
    }
}

