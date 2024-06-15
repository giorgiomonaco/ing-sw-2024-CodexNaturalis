package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;

public class LobbyView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("\n+----------------------+\n" +
                "|        LOBBY         |\n" +
                "+----------------------+\n");
        System.out.println(client.getServerLastMessage());
        System.out.println("ACTUAL LOBBY:");
        for(String s : client.getPlayerList()){
            System.out.println(s + " ");
        }
    }
}
