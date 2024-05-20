package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;

public class SelNumPlayerView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println( "\n+-----------------------------+\n" +
                "|  SELECT NUMBER OF PLAYERS   |\n" +
                "+-----------------------------+\n" + "\n-- You are the first player to connect to the game! --" +
                            "\nPlease insert 'num <x>' where x is the number of player of the game you want to create.");
    }
}
