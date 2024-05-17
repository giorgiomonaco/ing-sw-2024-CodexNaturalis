package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;
import java.util.Scanner;

public class LoginView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("---LOGIN PHASE---\nInsert 'login <usr>' where usr is your username: ");
    }
}
