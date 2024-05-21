package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.ObjectiveCard;

import java.util.ArrayList;
import java.util.List;

public class SelObjView implements TuiView{

    List<ObjectiveCard> availableObj = new ArrayList<>();
    @Override
    public void play(Client client) {
        System.out.println("\n\nSelect your personal objective card from the available ones:\n");

        availableObj = client.getPlayerObjective();
        int i = 1;
        for(ObjectiveCard c: availableObj){
            System.out.println("\nCARD " + i + " :\n");
            Tui view = (Tui) client.getUI();
            view.printObjectiveCard(c);
            i++;
        }

        System.out.println("\n\nInsert obj <x> where x is the number of the card you want:\n");
    }
}
