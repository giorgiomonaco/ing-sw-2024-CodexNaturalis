package it.polimi.ingsw.model;


import it.polimi.ingsw.controller.GameLogic;
import it.polimi.ingsw.controller.GameSetUpper;
import it.polimi.ingsw.controller.MainController;

public class Main {
    public static void main(/*String[] args*/) {

        //We want a controller that creates the obj game and the game state to handle it
        // We want to create a main controller to which communicate for any changes and
        //Handling every situation comes up
        MainController mainController = new MainController();
        //The controller has to do everything to set up
        //Nothing is started in the model
        mainController.playGame();



        }
    }