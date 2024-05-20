package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;

import java.util.*;
public class DrawManager {
    //This class manage all the drawing of the game
    private final Game game;
    boolean frontSide;

    //Constructor
    public DrawManager(Game game){
        this.game = game;
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
            sendChoice();
            //we distribute the initial card to every one
            //placing it into the right place (center) on the player board game
            distributeInitialCards();
        }
    }

    //Distribute the initial card to every player
    //placing it in the middle of the player board game
    public void distributeInitialCards(){
        game.getPlayerList().forEach(p -> p.setInitialCard(game.drawInitialCard()));
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
        // game.removeFormResourceDeck(card);
    }

    //Initialize gold cards
    public void drawGoldCards(){
        //Draw a gold card from the common deck
        GoldCard card = game.drawGoldCard();
        //Add it to the hand of the player
        game.getCurrentPlayer().addGoldCard(card);
        //remove it from the gold deck
        // game.removeFormGoldDeck(card);
    }


    //initialize objective cards
    public ObjectiveCard[] sendChoice(){
        ObjectiveCard[] choice = new ObjectiveCard[2];
        for (int i = 0; i < 2; i++) {
            choice[i] = game.drawObjectiveCard();
        }
        return choice;
    }
    public void setObjectiveCards(ObjectiveCard choice){
        game.getCurrentPlayer().setObjectiveCard(choice);
        game.getObjectiveDeck().remove(choice);
    }

    /*
    Method to draw a card
     */
    public void startDrawPhase() {
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
        for (ResourceCard c : game.getCommonBoard().getDiscoveredResourceCards()) {
            discoveredResCards.add(String.valueOf(c.getCardID()));
        }
        List<String> discoveredGoldCards = new ArrayList<>();
        for (GoldCard c : game.getCommonBoard().getDiscoveredGoldCards()) {
            discoveredGoldCards.add(String.valueOf(c.getCardID()));
        }

        /* commento perchè da errore
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
         */

    /*
    Manage draw from discovered cards pools

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
    */

    }}
