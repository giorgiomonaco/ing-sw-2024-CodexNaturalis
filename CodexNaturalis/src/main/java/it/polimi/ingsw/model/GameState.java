package it.polimi.ingsw.model;

public class GameState {
    //this class contains the game state
    //We want it here to separate the state form the object
    //So we will have a game object containing the "physical" part of the game state
    //And a super simple class representing the higher level concept of the game state
    //Like if its starting, ending, end, ...
    //We may add states for connection related things

    //Default state, in which we have nothing, the game has just being created
    private final int GAME_STATE_SLEEP = 0;

    //Start of everything, i.e.:
    //creation of every obj of the game
    //creation of players
    //distributing everything to the players...
    private final int GAME_STATE_INITIALIZING = 1;

    //Normal advance of the game, based on players choices
    private final int GAME_STATE_PLAYING = 2;

    //Start of the ending process of the game i.e.:
    //last turn
    //points calculation...
    private final int GAME_STATE_ENDING = 3;

    //End of the game, starting end of everything related to the game
    //outside the gaming part
    //then we return to sleep after everything ended correctly
    private final int GAME_STATE_END = 4;

    //Error state (not crash maybe idk)
    private final int GAME_STATE_ERROR = 5;

    private int gameState;

    //getter of the game state
    //important to take decision or trigger events
    public int getGameState() {
        return gameState;
    }

    //Setter of game state, important for advancing in
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}
