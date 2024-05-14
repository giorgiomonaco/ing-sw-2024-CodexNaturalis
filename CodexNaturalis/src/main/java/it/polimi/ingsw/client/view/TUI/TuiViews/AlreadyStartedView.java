package it.polimi.ingsw.client.view.TUI.TuiViews;

public class AlreadyStartedView implements TuiView{
    @Override
    public void play() {
        System.out.println("YOU CANNOT JOIN THE GAME, WAIT FOR THE CURRENT GAME TO END");
    }
}
