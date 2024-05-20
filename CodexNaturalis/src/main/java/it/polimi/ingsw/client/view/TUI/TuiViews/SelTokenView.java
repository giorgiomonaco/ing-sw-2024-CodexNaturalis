package it.polimi.ingsw.client.view.TUI.TuiViews;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;

import java.util.ArrayList;
import java.util.List;

public class SelTokenView implements TuiView{
    Colors color = new Colors();
    List<String> availableTokens = new ArrayList<>();
    @Override
    public void play(Client client) {

        System.out.println("\n+----------------------+\n" +
                "|      GAME SET UP     |\n" +
                "+----------------------+\n\nSelect your token from the available ones:\n");
        availableTokens = client.getAvailableTokens();

        for (String s: availableTokens) {
            System.out.print(color.getColor(s) + s + color.resetColor + " ");
        }

        System.out.println("\n\nInsert color <sel> where sel is the selected color.");
    }
}
