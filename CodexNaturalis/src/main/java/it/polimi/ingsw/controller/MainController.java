package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.ViewTry;

public class MainController {
    //This class is the main source of control over the game
    //We communicate through this to invoke model methods and update view

    //we need the view
    private static ViewTry view;
    //it needs to know the game it's referring to and it's state
    private static Game game;


    //Constructor, it only needs a game to control
    public MainController(){
        this.game = new Game(new GameState());
    }

    //The main controller has to start the view as soon as the game is created
    //then has to process every response from the view and use the managers to
    //modify the state of the game and use the managers to modify everything


    //Here it plays the game
    public void playGame(){
        //To play the game it first creates the view to interact with the user
        view = new ViewTry();

        //Then creates  the game set up
        GameSetUpper gameSetUpper = new GameSetUpper(game, view);
        //Start the setup of the game
        gameSetUpper.gameSetUp();


        //we create the game logic
        //the thing that manages all the game played after setup
        GameLogic gameLogic = new GameLogic(game, view);
        gameLogic.playGame();
    }

}
