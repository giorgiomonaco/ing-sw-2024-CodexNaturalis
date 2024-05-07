package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.ViewTry;

import java.util.*;
public class DrawManager {
    //This class manage all the drawing of the game
    private final Game game;
    private final ViewTry view;

    //Constructor
    public DrawManager(Game game, ViewTry view){
        this.game = game;
        this.view = view;
    }

    //initialize players hand
    public void initializePlayersHand(){
        //for every player playing the game
        for(Player p : game.getPlayerList()) {
            game.setCurrentPlayer(p);
            //We call the initialization of every type of card present in the game
            //we need 2 initial resource cards
            drawResourceCards();
            drawResourceCards();
            //then one gold
            drawGoldCards();
            //then one objective (chosen between 2
            initializeObjectiveCards();
            //we distribute the initial card to every one
            //placing it into the right place (center) on the player board game
            distributeInitialCards();
        }
    }

    //Distribute the initial card to every player
    //placing it in the middle of the player board game
    public void distributeInitialCards(){
        //We take the first card (aka draw) from the Initial cards deck
        InitialCard card = game.drawInitialCard();
        //we inform about which card he draws
        view.displaceInitialCard(card.getCardName());
        //Now we ask about side selection
        if(view.askForSideSelection() == "1"){
            card.setFrontSide(true);
        } else{
            card.setFrontSide(false);
        }
        //then we place that card in the middle of the player board
        game.getCurrentPlayer().getGameBoard().placeInitialCard(card);
    }

    //initialize resource cards
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Same name than in the cards initializers - modify this thing
    public void drawResourceCards(){
        //Draw a resource card from the resource deck of the game
        ResourceCard card = game.drawResourceCard();
        //add the card to the player hand
        game.getCurrentPlayer().addResourceCard(card);
        //Remove that card from the resource deck
        game.removeFormResourceDeck(card);
    }

    //Initialize gold cards
    public void drawGoldCards(){
        //Draw a gold card from the common deck
        GoldCard card = game.drawGoldCard();
        //Add it to the hand of the player
        game.getCurrentPlayer().addGoldCard(card);
        //remove it from the gold deck
        game.removeFormGoldDeck(card);
    }


    //initialize objective cards
    public void initializeObjectiveCards(){
        //Here we have a more difficult task
        //draw 2 cards and make player choose between them which one to keep
        //Start by drawing 2 cards
        ObjectiveCard[] choices = new ObjectiveCard[2];
        for(int i = 0; i < 2; i++){
            choices[i] = game.drawObjectiveCard();
            //delete it from the deck
            game.removeFormObjectiveDeck(choices[i]);
        }
        //Then we want to have the player make the choice
        boolean choiceOk = true;
        String choiceMade = view.askforObjectiveSelection(choices[0].getCardName(), choices[1].getCardName(), choiceOk);
        do {
            switch (choiceMade){
                case "F":
                    //If he chose the first card we assign it to him
                    game.getCurrentPlayer().setObjectiveCard(choices[0]);
                    choiceOk = true;

                case "S":
                    //If he chose the second card we assign it to him
                    game.getCurrentPlayer().setObjectiveCard(choices[1]);
                    choiceOk = true;

                case"error":
                    //We make him redo the choice
                    choiceOk = false;
                    view.askforObjectiveSelection(choices[0].getCardName(), choices[1].getCardName(), choiceOk);
            }
        } while (!choiceOk);
    }

}
