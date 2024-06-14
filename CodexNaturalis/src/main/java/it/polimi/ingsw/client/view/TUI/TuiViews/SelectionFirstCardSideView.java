package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.InitialCard;
import it.polimi.ingsw.server.model.ObjectiveCard;

import java.util.ArrayList;
import java.util.List;

public class SelectionFirstCardSideView implements TuiView{


    @Override
    public void play(Client client) {
        Tui view = (Tui) client.getUI();
        System.out.println("\n\nHere is your initial card!\n");
        InitialCard init = client.getInit();
        view.printInitialCard(init);
        System.out.println("\nSelect the side of the initial card, insert [front] or [back]:\n");

    }
}
