package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.*;

import java.util.*;
public class DrawManager {
    //This class manage all the drawing of the game
    private final Game game;

    //Constructor
    public DrawManager(Game game){
        this.game = game;
    }

    //initialize players hand
    public void initializePlayersHand(){
        //for every player playing the game
        for(Player p : game.getPlayerList()) {
            //We call the initialization of every type of card present in the game
            //we need 2 initial resource cards
            drawResourceCards(p);
            drawResourceCards(p);
            //then one gold
            drawGoldCards(p);
            //then one objective (chosen between 2
            initializeObjectiveCards(p);
            //we distribute the initial card to every one
            //placing it into the right place (center) on the player board game
            distributeInitialCards(p);
        }
    }

    //Distribute the initial card to every player
    //placing it in the middle of the player board game
    public void distributeInitialCards(Player p){
        //We take the first card (aka draw) from the Initial cards deck
        InitialCard card = game.drawInitialCard();
        //then we place that card in the middle of the player board
        p.getGameBoard().placeInitialCard(card);
    }

    //initialize resource cards
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Same name than in the cards initializers - modify this thing
    public void drawResourceCards(Player p){
        //Draw a resource card from the resource deck of the game
        ResourceCard card = game.drawResourceCard();
        //add the card to the player hand
        p.addResourceCard(card);
        //Remove that card from the resource deck
        game.removeFormResourceDeck(card);
    }

    //Initialize gold cards
    public void drawGoldCards(Player p){
        //Draw a gold card from the common deck
        GoldCard card = game.drawGoldCard();
        //Add it to the hand of the player
        p.addGoldCard(card);
        //remove it from the gold deck
        game.removeFormGoldDeck(card);
    }


    //initialize objective cards
    public void initializeObjectiveCards(Player p){
        //Here we have a more difficult task
        //draw 2 cards and make player choose between them which one to keep
        //Start by drawing 2 cards
        for(int i = 0; i < 2; i++){
            ObjectiveCard card = game.drawObjectiveCard();
            //delete it from the deck
            game.removeFormObjectiveDeck(card);
            //add to player hand
            p.addObjectiveCard(card);
        }
        //Then we want to have the player make the choice
        playerObjectiveCardChoice(p);
    }

    //choice of the player on which objective card to keep
    public void playerObjectiveCardChoice(Player p){
        //Get the cards from the hand
        List<ObjectiveCard> playerObjHand = p.getPlayerObjectiveCards();
        //get the names of the cards he got (I don't like do it like that but still...)
        String card1 = playerObjHand.get(0).getCardName();
        String card2 = playerObjHand.get(1).getCardName();
        //Get the name of the player to be clear on who is choosing the card to keep
        String playerName = p.getPlayerName();
        //Make the player know which cards he has in hand
        System.out.println( playerName + " you have this Objective Cards in hand: "
                            + card1 + " , " + card2);
        System.out.println("which one you want to keep? first or second? ");

        //we create a var choice to loop on until a choice is actually made
        boolean choiceMade = false;
        //Create the scanner for the player answer
        Scanner scanner = new Scanner(System.in);
        //loop on the choice until choice made
        do {
            String input = scanner.nextLine();

            if (input.equals("first")) {
                //before removing the card from the hand
                //we place it at the bottom of the obj card deck
                game.addObjectiveCardToDeck(p.getObjectiveCardFromHand(1));
                //then we remove it from the hand
                p.removeObjectiveCardFromHand(playerObjHand.get(1));
                //the player made a valid choice seo we can exit the loop
                choiceMade = true;

            } else if (input.equals("second")) {
                //before removing the card from the hand
                //we place it at the bottom of the obj card deck
                game.addObjectiveCardToDeck(p.getObjectiveCardFromHand(0));
                //then we remove it from the hand
                p.removeObjectiveCardFromHand(playerObjHand.get(0));
                //the player made a valid choice seo we can exit the loop
                choiceMade = true;
            } else {
                //Error message if the choice was not valid, we stay into the loop
                System.out.println("Incorrect answer, retry");
            }
        } while(!choiceMade);

        //-------------------Check if choice worked----------------------------
        //Check if the right card is still there
        String cardKept = p.getPlayerObjectiveCards().get(0).getCardName();
        System.out.println("You kept " + cardKept);
        //Check if the hand has actually size 1
        int objHandSize = p.getPlayerObjectiveCards().size();
        System.out.println("size of the obj hand = " + objHandSize);
    }
}
