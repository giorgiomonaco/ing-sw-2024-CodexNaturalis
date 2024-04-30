package it.polimi.ingsw.controller;


import it.polimi.ingsw.controller.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.view.ViewTry;

public class GameSetUpper {
    //this class manage the creation of the managers of the game and the start of the game
    //Things like distribution of tokens to players, creating and adding players to the game
    //distribution of first cards to players

    private final Game game;
    private final ViewTry view;

    //Constructor
    public GameSetUpper(Game game, ViewTry view){
        this.game = game;
        this.view = view;
    }

    //Initialization of the game
    public void gameSetUp(){
        //First thing it does is make the game advance to "initializing state"
        //since game starts in sleep by default = +1 is initialization
        //may be checkable by an if (getState == 0) ...
        //gameState = 1 -> initializing of the game
        game.setGameState(1);

        //creation of player manager
        PlayerManager playerManager = new PlayerManager(game, view);
        //Initialization of the players
        playerManager.playersInitialization();

        //create the token manager
        TokenManager tokenManager = new TokenManager(game,view);
        //we give every player a token
        tokenManager.initializeTokens();

        //create a manager for the boards of each player
        GameBoardManager gameBoardManager = new GameBoardManager(game);
        //initialize the game boards
        gameBoardManager.initializeBoards();

        //create the whole card manager
        CardManager cardManager = new CardManager(game);
        //initialize all the cards in the game:
        //creating and placing them into the right decks in right places
        cardManager.initializeAllCards();

        //After we have initialized all the cards and tokens we initialize the common board
        //to initialize all common things: Discovered cards and Points tracker
        //we create the common board manager
        CommonBoardManager commonBoardManager = new CommonBoardManager(game);
        //We initialize the common board:
        //Place the points tracker and put the tokens at start
        //Discover the first cards onto the common board
        commonBoardManager.initializeCommonBoard();

        //Creation of the draw manager
        DrawManager drawManager = new DrawManager(game, view);
        //Initialization of hands:
        //Every player draw the first cards
        //Choice about the secret obj card
        //then distribution of the initial card
        //and place it at starting position on the personal game board
        drawManager.initializePlayersHand();

    }



}
