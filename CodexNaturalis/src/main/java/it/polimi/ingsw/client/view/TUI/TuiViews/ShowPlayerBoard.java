package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.server.model.GameBoard;

import java.io.Serializable;

public class ShowPlayerBoard implements TuiView, Serializable {
    private GameBoard gameboard;
    public void play(GameBoard gameBoard){
        this.gameboard=gameBoard;

        //printa gameboard

    }







    @Override
    public void play() {

    }
}
