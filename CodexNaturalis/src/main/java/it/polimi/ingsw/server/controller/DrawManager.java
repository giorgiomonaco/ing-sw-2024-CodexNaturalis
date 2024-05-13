package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.client.view.ViewTry;
import it.polimi.ingsw.server.model.*;

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

    /*
    Method to draw a card
     */
    public void startDrawPhase(){
        /*
        Draw phase:
        . asking the deck the player wants to draw from
        . draw from it
        . add to player hand
         */
        //We ensure that the decks are not empty
        boolean resDeckEmpty = game.getResourceDeck().isEmpty();
        boolean goldDeckEmpty = game.getGoldDeck().isEmpty();
        //We want to retrieve the names of the discovered cards we got
        List<String> discoveredResCards = new ArrayList<>();
        for(ResourceCard c : game.getCommonBoard().getDiscoveredResourceCards()){
            discoveredResCards.add(String.valueOf(c.getCardID()));
        }
        List<String> discoveredGoldCards = new ArrayList<>();
        for(GoldCard c : game.getCommonBoard().getDiscoveredGoldCards()){
            discoveredGoldCards.add(String.valueOf(c.getCardID()));
        }
        //we ask the player which card he wants to draw
        String selectedCard = view.drawSelection(resDeckEmpty, goldDeckEmpty,discoveredResCards, discoveredGoldCards);
        //now we act accordingly
        switch (selectedCard) {
            case "R1" -> drawDiscoveredCard("R", 0);
            case "R2" -> drawDiscoveredCard("R", 1);
            case "G1" -> drawDiscoveredCard("G", 0);
            case "G2" -> drawDiscoveredCard("G", 1);
            case "RD" -> drawResourceCards();
            case "GD" -> drawGoldCards();
        }
    }

    /*
    Manage draw from discovered cards pools
     */
    public void drawDiscoveredCard(String type, int position){
        Card drawnCard = null;
        if(type.equals("R")){
            drawnCard = game.getCommonBoard().getDiscoveredResourceCards().get(position);
            //we remove it from the discovered list
            game.getCommonBoard().getDiscoveredResourceCards().remove(position);
            //We add to players hand
            game.getCurrentPlayer().addResourceCard((ResourceCard) drawnCard);
            //Now we want to discover another card

        } else {
            drawnCard = game.getCommonBoard().getDiscoveredGoldCards().get(position);
            //we remove it from the discovered list
            game.getCommonBoard().getDiscoveredGoldCards().remove(position);
            //We add to players hand
            game.getCurrentPlayer().addGoldCard((GoldCard) drawnCard);
            //Now we want to discover another card
        }
    }
}
