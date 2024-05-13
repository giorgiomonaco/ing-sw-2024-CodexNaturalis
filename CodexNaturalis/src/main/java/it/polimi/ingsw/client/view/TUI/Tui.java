package it.polimi.ingsw.client.view.TUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.stateManager.stateEnum;
import it.polimi.ingsw.client.view.UserInterface;

public class Tui implements UserInterface {

    private stateEnum currentState;

    public Tui(Client client){
        currentState = client.getCurrentState();
        run();
    }


    @Override
    public void run() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void error() {

    }

    @Override
    public void showMessage() {

    }
}
