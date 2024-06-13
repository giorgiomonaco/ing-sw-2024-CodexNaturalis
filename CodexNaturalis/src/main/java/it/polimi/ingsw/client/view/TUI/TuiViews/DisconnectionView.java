package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;

import java.io.Serializable;

public class DisconnectionView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println(Colors.redColor + "GAME ABORTED" + Colors.resetColor);
        System.out.println(Colors.redColor + "The game was closed because the connection was lost!" +
                "\nSomeone lost the connection during the setup phase, when the game wasn't even started, " +
                "\nor the server crashed for some reason." + Colors.resetColor);
        System.exit(1);
    }
}
