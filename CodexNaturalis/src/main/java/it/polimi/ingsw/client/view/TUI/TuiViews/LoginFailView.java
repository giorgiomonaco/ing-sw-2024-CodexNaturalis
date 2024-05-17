package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class LoginFailView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("The chosen username is already taken, try to choose another one.");
    }
}
