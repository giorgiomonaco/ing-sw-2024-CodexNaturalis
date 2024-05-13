package it.polimi.ingsw.client.view.TUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.stateManager.stateEnum;
import it.polimi.ingsw.client.view.TUI.TuiViews.LoginView;
import it.polimi.ingsw.client.view.TUI.TuiViews.TuiView;
import it.polimi.ingsw.client.view.UserInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tui implements UserInterface {

    private stateEnum currentState;
    private Map<TuiView, stateEnum> phaseView;

    public Tui(Client client){
        currentState = client.getCurrentState();
        phaseView = new HashMap<>();

        phaseView.put(new LoginView(), stateEnum.LOGIN);

        run();
    }


    @Override
    public void run() {
        switch(currentState){
            case LOGIN:

                break;
            case LOBBY:


        }
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
