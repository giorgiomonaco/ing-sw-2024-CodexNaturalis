package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;

import java.io.Serializable;
import java.util.Scanner;

public class LoginView implements TuiView{
    @Override
    public void play(Client client) {
        System.out.println("\n+----------------------+\n" +
                "|        LOGIN         |\n" +
                "+----------------------+\n\nInsert 'login <usr>' where usr is your username: ");
    }
}
