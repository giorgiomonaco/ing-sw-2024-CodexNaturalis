package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class RejectedView implements TuiView{
    @Override
    public void play() {
        System.out.println("REQUEST REJECTED");
        System.out.println("Sorry, your request to join the game has been rejected, the game may is already full.");
        System.exit(0);
    }
}
