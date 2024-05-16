package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.ServerHandler;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.client.view.ViewTry;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameState;

public class MainController {
    //This class is the main source of control over the game
    //We communicate through this to invoke model methods and update view

    //it needs to know the game it's referring to and it's state
    private Game game;

    private ServerHandler serverHandler;
    //Constructor, it only needs a game to control
    public MainController(ServerHandler serverHandler){
        this.serverHandler =  serverHandler;

    }

    //The main controller has to start the view as soon as the game is created
    //then has to process every response from the view and use the managers to
    //modify the state of the game and use the managers to modify everything


    //Here it plays the game
    public void gameCreation(String username, int num){
        //To play the game it first creates the view to interact with the user
        this.game = new Game();
        this.game.setPlayersNumber(num);
        //Then creates  the game set up
        GameSetUpper gameSetUpper = new GameSetUpper(game);
        //Start the setup of the game
        gameSetUpper.CreateGame(username);

    }
    public void playGame(){

    }
    public void joinPlayer(String username){

    }
}
