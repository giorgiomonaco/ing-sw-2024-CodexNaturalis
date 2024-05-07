package it.polimi.ingsw.controller;


import it.polimi.ingsw.controller.AvailableResourcesManager;
import it.polimi.ingsw.controller.DrawManager;
import it.polimi.ingsw.controller.PlayCardManager;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.ViewTry;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameLogic {
    //this class represents the game states while
    //the game is being played after setup phase
    //It cycle on the players to make them play their turn, and
    //it manages every aspect of the game
    //(manages calling the various managers of different phases)
    //-------------------!!!WILL BE A HUGE CLASS IMO!!!!!-------------------

    //First we need the game it is referring to
    private final Game game;
    private final ViewTry view;

    //then we need the game states
    //play: turns keep going until ending conditions reached
    //ending: last turns for the players after reaching end conditions
    //end: end of everything, declare winner
    private static final int STATE_PLAY = 1;
    private static final int STATE_ENDING = 2;
    private static final int STATE_END = 3;
    //And we need the variable for the game state
    //default value at 0, so none of the states before we actually start playing
    private int gameState;


    //constructor
    public GameLogic(Game game, ViewTry view){
        this.game = game;
        this.view = view;
    }


    //Start the game and let players play
    public void playGame(){
        //---------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!---------------------
        //don't like cus there is already another one. We have to create (another) draw manager to manage the draw
        //very, very ugly to create it in the main tho so better like this
        //I think the previous will be garbage collected after exiting setup
        DrawManager drawManager = new DrawManager(game, view);

        //We also need an object that manages the play of the cards
        //to make this class not 10k lines and so complex
        PlayCardManager playCardManager = new PlayCardManager(game, view);

        //we also need a manager for the resource available for each player to
        //play the cards and give points based on visible symbols
        AvailableResourcesManager availableResourcesManager = new AvailableResourcesManager(game);
        //we can decide if create a specific method to initialize the resource of the first card
        //instead of doing a cycle on the whole grid (very, very not efficient)
        PointTrackerManager pointTrackerManager = new PointTrackerManager(game);




        //set the game in play state to start playing
        gameState = STATE_PLAY;

        //we cycle upon the list of players
        //we make them pass through all the game phases one after the other
        do{
            for(Player p : game.getPlayerList()){
                //first we set the current player the one that is actually playing
                game.setCurrentPlayer(p);
                /*
                We call the method of the play card manager
                that starts the play phase
                It may update the game state to "play phase"?
                 */
                playCardManager.startPlayPhase(p);
                //moving the player token
                pointTrackerManager.moveToken(game.getCurrentPlayer().getPlayerToken(), game.getCurrentPlayer().getPlayerPoints());
                /*
                Then we have the draw phase of the player
                 */

                }

            } while(gameState == STATE_PLAY);

    }

    //getter of the game state
    public int getGameState() {
        return gameState;
    }
}
