package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

public class ShowWinnerView implements TuiView {
    @Override
    public void play(Client client) {
        if (client.getWinner()) {
            System.out.println("\n" +
                    " \\ \\   /  _ \\   |   |     \\ \\        / _ _|   \\  | \n" +
                    "  \\   /  |   |  |   |      \\ \\  \\   /    |     \\ | \n" +
                    "     |   |   |  |   |       \\ \\  \\ /     |   |\\  | \n" +
                    "    _|  \\___/  \\___/         \\_/\\_/    ___| _| \\_| \n" +
                    "                                                   \n");
        } else {
            System.out.println("\n" +
                    " \\ \\   /  _ \\   |   |      |       _ \\    ___|   ____| \n" +
                    "  \\   /  |   |  |   |      |      |   | \\___ \\   __|   \n" +
                    "     |   |   |  |   |      |      |   |       |  |     \n" +
                    "    _|  \\___/  \\___/      _____| \\___/  _____/  _____| \n" +
                    "                                                       \n");
            //the winner is client.getWinner
        }
    }
}
