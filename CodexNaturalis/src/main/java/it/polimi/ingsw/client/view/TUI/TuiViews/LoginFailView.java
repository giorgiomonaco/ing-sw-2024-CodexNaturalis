package it.polimi.ingsw.client.view.TUI.TuiViews;

public class LoginFailView implements TuiView{
    @Override
    public void play() {
        System.out.println("The chosen username is already taken, try to choose another one.");
    }
}
