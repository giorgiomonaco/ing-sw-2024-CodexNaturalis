package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.client.view.ViewTry;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

public class GameSetUpper {
    //this class manage the creation of the managers of the game and the start of the game
    //Things like distribution of tokens to players, creating and adding players to the game
    //distribution of first cards to players

    private final Game game;

    //Constructor
    public GameSetUpper(Game game){
        this.game = game;
    }

    //Initialization of the game
    public void gameSetUp(){
       //create the token manager
        TokenManager tokenManager = new TokenManager(game);
        //we give every player a token
        tokenManager.initializeTokens();

        //create a manager for the boards of each player
        GameBoardManager gameBoardManager = new GameBoardManager(game);
        //initialize the game boards
        gameBoardManager.initializeBoards();


        //After we have initialized all the cards and tokens we initialize the common board
        //to initialize all common things: Discovered cards and Points tracker
        //we create the common board manager
        CommonBoardManager commonBoardManager = new CommonBoardManager(game);
        //We initialize the common board:
        //Place the points tracker and put the tokens at start
        //Discover the first cards onto the common board
        commonBoardManager.initializeCommonBoard();

        //Creation of the draw manager
        DrawManager drawManager = new DrawManager(game);
        //Initialization of hands:
        //Every player draw the first cards
        //Choice about the secret obj card
        //then distribution of the initial card
        //and place it at starting position on the personal game board
        drawManager.initializePlayersHand();

    }

        public void CreateGame(String username){
            //Initialization of the players
            game.addPlayer(new Player(username));
            game.setGameState(gameStateEnum.ACCEPT_PLAYER);
            //create the whole card manager
            CardManager cardManager = new CardManager(game);
            //initialize all the cards in the game:
            //creating and placing them into the right decks in right places
            // cardManager.initializeAllCards();

        }

}
