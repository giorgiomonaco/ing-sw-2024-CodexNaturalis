package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.TUI.Tui;

public class GameStartedView implements TuiView{
    @Override
    public void play(Client client) {

        System.out.println("\n" +
                "                                                                                                                    \n" +
                "\t\t  ____    ___    ____    _____  __  __      _   _      _      _____   _   _   ____       _      _       ___   ____  \n" +
                "\t\t / ___|  / _ \\  |  _ \\  | ____| \\ \\/ /     | \\ | |    / \\    |_   _| | | | | |  _ \\     / \\    | |     |_ _| / ___| \n" +
                "\t\t| |     | | | | | | | | |  _|    \\  /      |  \\| |   / _ \\     | |   | | | | | |_) |   / _ \\   | |      | |  \\___ \\ \n" +
                "\t\t| |___  | |_| | | |_| | | |___   /  \\      | |\\  |  / ___ \\    | |   | |_| | |  _ <   / ___ \\  | |___   | |   ___) |\n" +
                "\t\t \\____|  \\___/  |____/  |_____| /_/\\_\\     |_| \\_| /_/   \\_\\   |_|    \\___/  |_| \\_\\ /_/   \\_\\ |_____| |___| |____/ \n" +
                "                                                    by CRANIO creations                                                                \n");

        Tui view = (Tui) client.getUI();
        System.out.print("\n\t\t\t\t\tHere are your playable cards:\n");
        view.printCards(client.getPlayerHand());
        /*
        for(int i = 0; i < 3; i++) {
            System.out.println("\nCARTA "+(i+1)+":");
            view.printCard(client.getPlayerHand().get(i));
        }*/

        System.out.println();
    }
}
