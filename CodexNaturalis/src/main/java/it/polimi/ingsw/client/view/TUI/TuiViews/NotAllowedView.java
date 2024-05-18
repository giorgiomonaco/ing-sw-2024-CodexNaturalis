package it.polimi.ingsw.client.view.TUI.TuiViews;

public class NotAllowedView implements TuiView{
    @Override
    public void play() {
        System.out.println("PERMISSION DENIED!");
        System.out.println("You are not allowed to do this command now.");
    }
}
