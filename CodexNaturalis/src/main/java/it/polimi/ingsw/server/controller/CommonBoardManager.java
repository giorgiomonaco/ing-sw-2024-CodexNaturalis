package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.*;

public class CommonBoardManager {

    private final Game game;

    //constructor
    public CommonBoardManager(Game game){
        this.game = game;
    }

    //initialize the common board
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

        //Draw and discover initial common cards
        for(int i = 0; i < 2; i++){
            //Draw and discover the first 2 resource cards
            discoverResourceCard();
            //Draw and discover the first 2 Gold cards
            discoverGoldCard();
            //Draw and show the 2 common objectives
            discoverObjectiveCard();
        }
    }

    //associate the common board to the game
    public void associateCommonBoard(CommonBoard commonBoard){
        game.setCommonBoard(commonBoard);
    }

    //Draw from the game and place it to the common board
    public void discoverResourceCard(){
        //First we draw from the resource deck
        ResourceCard card = game.drawResourceCard();
        //Then we remove it from the deck
        game.removeFormResourceDeck(card);
        //we put it on the common board
        game.getCommonBoard().addDiscoveredSourceCard(card);
    }

    //Draw a card from the Gold deck and place it into the common space
    public void discoverGoldCard(){
        //first draw from the gold deck
        GoldCard card = game.drawGoldCard();
        //then we remove it from the deck
        game.removeFormGoldDeck(card);
        //then we put it into the common game board
        game.getCommonBoard().addDiscoveredGoldCard(card);
    }

    //draw a card from the objective deck and place it into the common board
    public void discoverObjectiveCard(){
        //We first draw a card from the deck
        ObjectiveCard card = game.drawObjectiveCard();
        //Then we remove it from the deck
        game.removeFormObjectiveDeck(card);
        //Finally we put it into the common board
        game.getCommonBoard().addCommonObjectiveDiscoveredCard(card);
    }

}
