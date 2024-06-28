package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

import java.util.ArrayList;
import java.util.List;

public class GameSetUpper {
    /*
    this class manage the creation of the managers of the game and the start of the game
    Things like distribution of tokens to players, creating and adding players to the game
    distribution of first cards to players
    */
    private final Game game;

    //Constructor
    public GameSetUpper(Game game){
        this.game = game;
    }


    /**
     * Sets up the game by initializing all necessary components, including tokens, player boards, the common board,
     * and player hands. This method orchestrates the creation and initialization of managers responsible for different
     * aspects of the game setup.
     */
    public void gameSetUp(){

        List<String> availableTokens = new ArrayList<>();
        availableTokens.add("red");
        availableTokens.add("blue");
        availableTokens.add("green");
        availableTokens.add("yellow");
        game.setAvailableTokens(availableTokens);


        //create a manager for the boards of each player
        BoardsManager boardsManager = new BoardsManager(game);
        //initialize the game boards
        boardsManager.initializeBoards();





        //Creation of the draw manager
        DrawManager drawManager = new DrawManager(game);
        //Initialization of hands:
        //Every player draw the first cards
        //Choice about the secret obj card
        //then distribution of the initial card
        //and place it at starting position on the personal game board
        drawManager.initializePlayersHand();

    }


    /**
     * Creates a new game with the specified username. This method initializes the player, sets the game state,
     * and initializes all the cards in the game.
     *
     * @param username The username of the player who is creating the game.
     */
    public void CreateGame(String username){

        //Initialization of the players
        game.addPlayer(new Player(username));
        game.setGameState(gameStateEnum.ACCEPT_PLAYER);
        //create the whole card manager
        CardManager cardManager = new CardManager(game);
        //initialize all the cards in the game:
        //creating and placing them into the right decks in right places
        cardManager.initializeAllCards();

    }

}
