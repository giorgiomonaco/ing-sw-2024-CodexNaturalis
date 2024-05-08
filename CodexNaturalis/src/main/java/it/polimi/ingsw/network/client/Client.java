package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.client.stateManager.stateEnum;

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
