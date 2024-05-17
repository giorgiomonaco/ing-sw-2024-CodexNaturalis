package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.network.message.allMessages.AlreadyStarted;
import it.polimi.ingsw.server.ServerHandler;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.client.view.ViewTry;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameState;
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

public class MainController {
    private Game game;
    private final ServerHandler serverHandler;
    private GameSetUpper gameSetUpper;
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
         this.gameSetUpper = new GameSetUpper(game);
        //Start the setup of the game
        gameSetUpper.CreateGame(username);

    }
    public void playGame(){

    }

    public Game getGame() {
        return game;
    }


    public void joinPlayer(String username){
        if (!game.getGameState().equals(gameStateEnum.ACCEPT_PLAYER)) {
            serverHandler.sendMessageToPlayer(username,
                    new AlreadyStarted(ServerHandler.HOSTNAME));
            return;
        }
        try {
            game.addPlayer(new Player(game, username));
        } catch (IllegalStateException e) {
            System.err.println("Max number of player already reached.");
            serverHandler.sendMessageToPlayer(username,
                    new AlreadyStarted(ServerHandler.HOSTNAME));
        }

        if(game.getGameState().equals(gameStateEnum.START)){

        }
    }


    public void gameStarting(){
        gameSetUpper.gameSetUp();
    }
}
