package it.polimi.ingsw.model;


import it.polimi.ingsw.controller.GameLogic;
import it.polimi.ingsw.controller.GameSetUpper;

public class Main {
    public static void main(String[] args) {

        //Create the game
        Game game = new Game();

        //Create  the game set up
        GameSetUpper gameSetUpper = new GameSetUpper(game);
        //Start the setup of the game
        gameSetUpper.gameSetUp();

        //we create the game logic
        //the thing that manages all the game played after setup
        GameLogic gameLogic = new GameLogic(game);
        gameLogic.playGame();

        }
    }