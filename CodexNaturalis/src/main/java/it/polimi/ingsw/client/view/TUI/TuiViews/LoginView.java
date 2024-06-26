package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;
import java.util.Scanner;

public class LoginView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("\n+----------------------+\n" +
                "|        LOGIN         |\n" +
                "+----------------------+\n\n" +
                "\n\nInsert chat <destination> <message> to send a message, where destination is either: player's username or toAll"+
                "\n\nInsert [login <usr>] where usr is your username: "
        );
    }
}
