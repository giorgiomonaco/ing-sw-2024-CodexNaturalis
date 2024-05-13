package it.polimi.ingsw.view.TUI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.stateManager.stateEnum;
import it.polimi.ingsw.view.UserInterface;

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
