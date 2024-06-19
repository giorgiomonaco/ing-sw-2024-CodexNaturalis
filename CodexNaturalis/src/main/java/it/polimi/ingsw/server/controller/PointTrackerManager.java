package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.PointTracker;
import it.polimi.ingsw.server.model.Token;

import java.util.ArrayList;

public class PointTrackerManager {
    /**
     * This class manages the point tracker:
     * Initializes it by creating the point tracker
     * Ita associates it to the Common board of the game
     * creates all the boxes and adds them to the point tracker
     * it manages the movement of a token from a box to another
     */
    private final Game game;

    //Constructor
    public PointTrackerManager(Game game){
        this.game = game;
    }


    /**
     * Initializes the point tracker for the game.
     * This method performs the following actions sequentially:
     * <ol>
     *     <li>Creates and associates the point tracker with the game.</li>
     *     <li>Populates the point tracker with initial data.</li>
     *     <li>Places all tokens at their starting positions on the point tracker.</li>
     * </ol>
     */
    public void initializePointTracker(){
        //Creation and Association
        createPointTracker();
        //population of the point tracker
        populationOfPointTracker();
        //We have now to put the tokens all in the starting position
        placeTokenAtStart();
    }

    //Creating it
    public void createPointTracker(){
        //creating the point tracker
        PointTracker pointTracker = new PointTracker();
        //Associate it to the common board of the game
        game.getCommonBoard().setPointTracker(pointTracker);
    }

    /**
     * Creates a point tracker for the game and associates it with the common board.
     * <p>
     * This method performs the following actions:
     * <ol>
     *     <li>Creates a new {@link PointTracker} instance.</li>
     *     <li>Associates the created point tracker with the common board of the game.</li>
     * </ol>
     */
    public void populationOfPointTracker(){
        //We want to make this process for 29 boxes
        for(int i = 0; i <29; i++) {
            ArrayList<Token> pointBox = new ArrayList<>();
            game.getCommonBoard().getPointTracker().addBoxToTracker(pointBox);
        }
    }

    //Put the tokens into starting position
    public void placeTokenAtStart(){
        //For every player into the list
        for (Player p : game.getPlayerList()) {
            //I take his token
            Token token = p.getPlayerToken();
            //I add it to the first box of the point tracker of the common board
            game.getCommonBoard().getPointTracker().getTokenBox(0).add(token);
        }
    }

    public void moveToken(Token token, int points){

        for(ArrayList<Token> boxes : game.getCommonBoard().getPointTracker().getPointBoxes()){
            if(boxes != null){
                for (Token t : boxes){
                    if(game.getCurrentPlayer().getPlayerToken().equals(t)){
                        //We remove the token from the current place
                        boxes.remove(t);
                    }
                }
            }
        }
        //then we use the updated points to move the token to right place
        int tokenIndex = game.getCurrentPlayer().getPlayerPoints();
        game.getCommonBoard().getPointTracker().getPointBoxes().get(tokenIndex).add(token);

    }

}
