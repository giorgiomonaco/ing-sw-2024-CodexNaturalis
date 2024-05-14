package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.util.Scanner;

public class LoginView implements TuiView{
    @Override
    public void start() {
        System.out.println("---LOGIN PHASE---\nInsert 'login <usr>' where usr is your username: ");
    }
}
