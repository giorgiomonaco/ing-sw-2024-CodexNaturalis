package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class LoginSucView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("Login successful!");
    }
}
