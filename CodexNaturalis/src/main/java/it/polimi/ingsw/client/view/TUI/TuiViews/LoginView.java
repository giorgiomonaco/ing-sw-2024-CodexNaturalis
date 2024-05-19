package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;
import java.util.Scanner;

public class LoginView implements TuiView{
    @Override
    public void play() {
        System.out.println("\n+----------------------+\n" +
                "|        LOGIN         |\n" +
                "+----------------------+\n\nInsert 'login <usr>' where usr is your username: ");
    }
}
