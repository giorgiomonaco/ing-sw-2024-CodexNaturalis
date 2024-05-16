package it.polimi.ingsw.client.view.TUI.TuiViews;

public class RejectedView implements TuiView {
    @Override
    public void play() {
        System.out.println("Sorry, the game is full.");
    }
}
