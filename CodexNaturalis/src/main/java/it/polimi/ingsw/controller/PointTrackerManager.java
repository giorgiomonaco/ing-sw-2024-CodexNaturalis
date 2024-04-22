package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Token;

import java.util.ArrayList;

public class PointTrackerManager {
    //This class manages the point tracker:
    //Initializes it by creating the point tracker
    // Ita associates it to the Common board of the game
    // creates all the boxes and adds them to the point tracker
    //it manages the movement of a token from a box to another
    private final Game game;

    //Constructor
    public PointTrackerManager(Game game){
        this.game = game;
    }

    //Initialization of the point tracker:
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

    //populating with empty boxes
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
}
