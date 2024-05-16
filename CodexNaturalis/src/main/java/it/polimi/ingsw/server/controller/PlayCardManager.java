package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.client.view.ViewTry;
import it.polimi.ingsw.server.model.*;

import java.util.ArrayList;
import java.util.List;

public class PlayCardManager {
    //this class is used to manage the play of a card
    //used to shrink the code from the logic
    //it gets a card to be played and a player that played it,
    //proceeds to place the card if possible and asks the player
    //if the card is being played front or back side

    //we need the game we are referring to
    private final Game game;

    private final ViewTry view;

    private GameBoardManager boardManager;

    private PointManager pointManager;

    //variable to cycle on while checking if a card is actually playable or not
    boolean playableCard = true;

    //we prepare the variables for the placement of the card
    //not elegant but works, may review this later
    private int placeX;
    private int placeY;

    //constructor
    public PlayCardManager(Game game, ViewTry view){
        this.game = game;
    }

    /*
    Method for preparing the play of a card
    Asks the player which one he wants to play and then calls the method to play it
     */
    public void startPlayPhase(Player p){
        /*
        Structure of this:
        do:
            ask which card to play
            if front or back
            where to place it
            if placing not possible
         while: placing is not ok/ask at end if sure about it
         */

        /*
        We get the possible places to play it
        How?
        Cycle on the matrix and collect how many "1" (=available place)
        Look at the lowest and highest x and y
        So we print a matrix big just enough to see the places to place the cards.
         */
        boardManager = new GameBoardManager(game, view);
        boardManager.displaceAvailableBoxes();
        Card selectedCard = null;

        do {
            selectedCard = askForCard(p);
            sideChoice(selectedCard);
            placeCard(selectedCard, p);
        } while(!playableCard);
         /*
        We now have to:
        . Assign points
        . Assign resources
         */
        pointManager  = new PointManager(game, view);
        pointManager.givePoints(selectedCard);



    }

    /*
    Method that plays a resource card
     */
    private Card askForCard(Player p) {
        //We first collect the names of all the cards in hand (res and gold)
        List<String> resCardinHand = new ArrayList<>();
        for (ResourceCard card : p.getPlayerResourceCards()) {
            resCardinHand.add(String.valueOf(card.getCardID()));
        }
        //same with gold cards in hand
        List<String> goldCardinHand = new ArrayList<>();
        for (GoldCard card : p.getPlayerGoldCards()) {
            goldCardinHand.add(String.valueOf(card.getCardID()));
        }
        /*
        We collect the choice of the player
        represented by 2 strings:
        1. The type of card
        2. which of the ones of that type he wants to play
         */
        String[] playedCard = view.getPlayedCard(resCardinHand, goldCardinHand);

        /*
        Now we want to pick exactly that card from the players hand,
        so we check the type and the position,
        then we pick that form the hand of player
         */

        Card chosenCard = null;
        if (String.valueOf(playedCard[0]).equals("R")) {
            //we look at the position and select that one
            for (int i = 0; i < p.getPlayerResourceCards().size(); i++) {
                if (String.valueOf(i + 1).equals(playedCard[1])) {
                    /*
                    we found which card he wants to play,
                    we first save it here,
                    then we remove it from players hand
                     */
                    chosenCard = p.getPlayerResourceCards().get(i);
                    p.removeResourceCardFromHand((ResourceCard) chosenCard);
                    break;
                }
            }
        }
        for (int i = 0; i < p.getPlayerGoldCards().size(); i++) {
            if (String.valueOf(i + 1).equals(playedCard[1])) {
                /*
                we found which card he wants to play,
                we first save it here,
                then we remove it from players hand
                 */
                chosenCard = p.getPlayerGoldCards().get(i);
                p.removeGoldCardFromHand((GoldCard) chosenCard);
                break;
            }
        }
        return chosenCard;
    }

    //Ask the side of the card player wants to play
    private void sideChoice(Card c){
        /*
        We get the side selection from the view
        Then we use it to set the attribute of the card accordingly
         */
        String sideSelection = view.askForSideSelection();
        c.setFrontSide(sideSelection.equals("F"));
    }

    /*
    Method to place a card
     */

    public void placeCard(Card c, Player p){
        /*
        First we discriminate weather is gold or resource
        because they have different way to be played
        if resource card
            Place it
            Update everything
        if gold card
            Check if you have enough resources/items to play it
            Place it
            Get points
            Update everything
         */

        if(c instanceof ResourceCard){
            playResourceCard((ResourceCard) c);
        } else if(c instanceof GoldCard){
            playGoldCard((GoldCard) c);
        }
    }

    public void playResourceCard(ResourceCard card){
        /*
        We want the player to select one of the available boxes
         */
        int chosenBox = view.getPlayerBoxChoice();
        /*
        We send the choice to the manager to check if correct
        and if so to place the card
         */
        List<Symbol> sym = boardManager.playCard(card,chosenBox);
        /*
        Now we have to update the resources that are available
         */
        for (Symbol s: sym){
            game.getCurrentPlayer().resourceLowering(s);
        }
        card.addResources(game.getCurrentPlayer());
    }

    public void playGoldCard(GoldCard card){
        /*
        First thing first we have to check if the gold card is playable or not
        If it's not the case we need to inform the player and make him retry the card selection
         */


        /*
        We collect the available resources
         */
        int[] playerResources = game.getCurrentPlayer().getResourcesAvailable();
        //We collect the resources need from the card
        int[] neededResources = card.getNeededSymbols();
        for (int i = 0; i < neededResources.length; i++){
            if(neededResources[i] != playerResources[i]){
                /*
                If we don't have the needed resources
                We make the player remake the choice
                Informing about lack of resources
                 */
                playableCard = false;
                break;
            }
        }
        playableCard = true;
        /*
        If the player can actually play that card,
        we ask him where he wants to play it,
        then we place it there
         */
        int chosenBox = view.getPlayerBoxChoice();
        List<Symbol> sym = boardManager.playCard(card,chosenBox);
        for (Symbol s: sym){
            game.getCurrentPlayer().resourceLowering(s);
        }
        card.addResources(game.getCurrentPlayer());
    }

    

  /*  public int[] GetResources(Player P){



    }*/

}