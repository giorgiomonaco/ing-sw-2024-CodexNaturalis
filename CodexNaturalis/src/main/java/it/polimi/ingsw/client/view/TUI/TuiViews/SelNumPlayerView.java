package it.polimi.ingsw.client.view.TUI.TuiViews;

public class SelNumPlayerView implements TuiView{
    @Override
    public void play() {
        System.out.println("--- You are the first player to connect to the game! ---" +
                "" +
                "Please insert 'num <x>' where x is the number of player of the game you want to create.");
    }
}
