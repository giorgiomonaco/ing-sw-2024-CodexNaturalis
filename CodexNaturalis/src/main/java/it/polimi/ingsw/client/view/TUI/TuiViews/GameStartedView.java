package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.TUI.Tui;

public class GameStartedView implements TuiView{
    @Override
    public void play(Client client) {

        System.out.println("\n\n***********************************************************************************************************************************************************************\n" +
                "***********************************************************************************************************************************************************************\n" +
                "                                                                                                                    \n" +
                "\t\t  ____    ___    ____    _____  __  __      _   _      _      _____   _   _   ____       _      _       ___   ____  \n" +
                "\t\t / ___|  / _ \\  |  _ \\  | ____| \\ \\/ /     | \\ | |    / \\    |_   _| | | | | |  _ \\     / \\    | |     |_ _| / ___| \n" +
                "\t\t| |     | | | | | | | | |  _|    \\  /      |  \\| |   / _ \\     | |   | | | | | |_) |   / _ \\   | |      | |  \\___ \\ \n" +
                "\t\t| |___  | |_| | | |_| | | |___   /  \\      | |\\  |  / ___ \\    | |   | |_| | |  _ <   / ___ \\  | |___   | |   ___) |\n" +
                "\t\t \\____|  \\___/  |____/  |_____| /_/\\_\\     |_| \\_| /_/   \\_\\   |_|    \\___/  |_| \\_\\ /_/   \\_\\ |_____| |___| |____/ \n" +
                "                                                    by CRANIO creations" +
                "\n\n***********************************************************************************************************************************************************************\n");

        Tui view = (Tui) client.getUI();
        Legend();
        CardExemple();
        System.out.print("\n\t\t\t\t\tHere are your playable cards:\n");
        view.printCards(client.getPlayerHand());

        System.out.println();
    }

    private void CardExemple() {
        Colors color = new Colors();
        System.out.println(
                        "\nHere is an exemple of a card:" +
                "\n             Here are the points you will receive by placing the card" +
                "\n                                       |" +
                "\n                                       v " +
                "\nempty angle, you can cover it ->  E====2===="+color.greenColor+"L"+color.resetColor+" <- L means Leaf, a resource! Collect them!" +
                "\n                                  |         |" +
                "\n                                  |   ORA   | <- color of the card" +
                "\n                                  |         |" +
                "\n        objective item, scroll -> s===FFF===X <- X means you won't be able to place a card upon" +
                "\n                                       ^" +
                "\n                                       |" +
                "\n               These are the resources you need to place the card\n"+
                "\n***********************************************************************************************************************************************************************" +
        "\n***********************************************************************************************************************************************************************\n"
                );
    }

    private void Legend() {
        Colors color = new Colors();
        System.out.println("\nHere is the legend:" +
                "\n      resources:  |"+color.orangeColor+"M"+color.resetColor+" -> MUSHROOM                  "+color.purpleColor+"B"+color.resetColor+" -> BUTTERFLY               " +
                "\n                  |"+color.blueColor+"F"+color.resetColor+" -> FOX                       "+color.greenColor+"L"+color.resetColor+" -> LEAF                      " +
                "\n" +
                "\nobjectives item:  s -> SCROLL             b -> BOTTLE           f -> fether" +
                "\n" +
                "\nX -> NO angol (not coveralbe)        E -> EMPTY angol (coverable)" +
                "\n" +
                "\n***********************************************************************************************************************************************************************\n" +
                "************************************************************************************************************************************************************************\n"
        );
    }
}
