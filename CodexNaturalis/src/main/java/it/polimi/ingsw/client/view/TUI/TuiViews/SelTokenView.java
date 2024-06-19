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

        availableTokens = client.getAvailableTokens();

        if(!client.getPlayerList().getFirst().equals(client.getUsername())) {

            System.out.println("\n+----------------------+\n" +
                    "|      GAME SET UP     |\n" +
                    "+----------------------+\n\nSelect your token from the available ones:\n");

            for (String s : availableTokens) {
                System.out.print(color.getColor(s) + s + color.resetColor + " ");
            }

            System.out.println("\n\nInsert color <sel> where sel is the selected color.");

        }
        else {

            System.out.println("\n+----------------------+\n" +
                    "|      GAME SET UP     |\n" +
                    "+----------------------+\n\nYou are the first player!\nYou have the " + color.lightGrayColor + "black" + color.resetColor +" first player token");

            System.out.println("\nSelect your token from the available ones:\n");

            for (String s : availableTokens) {
                System.out.print(color.getColor(s) + s + color.resetColor + " ");
            }

            System.out.println("\n\nInsert color <sel> where sel is the selected color.");
        }

    }
}
