package it.polimi.ingsw.client.view.TUI.TuiViews;


public class RejectedView implements TuiView{
    @Override
    public void play() {
        System.out.println("\n+----------------------+\n" +
                "|   REQUEST REJECTED   |\n" +
                "+----------------------+\n\nSorry, your request to join the game has been rejected, the game may is already full.");
        System.exit(0);
    }
}
