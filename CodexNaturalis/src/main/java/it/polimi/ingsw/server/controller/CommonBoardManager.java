package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;


public class CommonBoardManager {

    private final Game game;

    //constructor
    public CommonBoardManager(Game game){
        this.game = game;
    }

    /**
     * Initializes the common board for the game.
     *<br><br>
     * This method sets up the common board, associates it with the game, and initializes
     * the point tracker manager.
     *<br><br>
     * Steps performed:
     * <ul>
     *   <li>Create the common board and associate it with the game.</li>
     *   <li>Create the point tracker manager to manage points separately from cards.</li>
     *   <li>Initialize the point tracker through the point tracker manager.</li>

     * </ul>
     */
    public void initializeCommonBoard(){
        //create the common board
        CommonBoard commonBoard = new CommonBoard(game);
        //call the association to the game
        associateCommonBoard(commonBoard);

        //creation of the point tracker manager to create the point tracker
        //And manage it with its own manager
        //Keeping separated cards and points this way
        //Creation
        PointTrackerManager pointTrackerManager = new PointTrackerManager(game);
        //Then we initialize the point tracker
        pointTrackerManager.initializePointTracker();

    }

    /**
     * Associate the common board to the game
     */
    public void associateCommonBoard(CommonBoard commonBoard){
        game.setCommonBoard(commonBoard);
    }



}
