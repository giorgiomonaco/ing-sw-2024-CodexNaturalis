package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;

import java.io.Serializable;

public class DisconnectionView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println(Colors.redColor + "GAME ABORTED!" + Colors.resetColor);
        System.out.println(Colors.redColor + "The game was closed because someone lost the connection during the login phase, when the game wasn't even started." + Colors.resetColor);
    }
}
