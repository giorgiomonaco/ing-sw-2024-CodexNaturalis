package it.polimi.ingsw.client;

import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.client.stateManager.stateEnum;

public class Client {
    private stateEnum currentState;
    private String username;
    private Player player;


    public stateEnum getCurrentState() {
        return currentState;
    }

    public void setCurrentState(stateEnum currentState) {
        this.currentState = currentState;
    }
}
