package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;

import java.util.*;
public class DrawManager {
    //This class manage all the drawing of the game
    private final Game game;
    boolean frontSide;

    //Constructor
    public DrawManager(Game game) {
        this.game = game;
    }

    //initialize players hand
    public void initializePlayersHand() {
        //for every player playing the game
        for (Player p : game.getPlayerList()) {
            game.setCurrentPlayer(p);
            //We call the initialization of every type of card present in the game
            //we need 2 initial resource cards
            drawResourceCards();
            drawResourceCards();
            //then one gold
            drawGoldCards();
            //then we need to add the 2 objective cards
            drawObjectiveCards();
            drawObjectiveCards();
        }

        //we distribute the initial card to every one
        //placing it into the right place (center) on the player board game
        distributeInitialCards();
    }

    // Distribute the initial card to every player
// Placing it in the middle of the player board game
    public void distributeInitialCards() {
        List<Player> playerList = game.getPlayerList();
        for (Player player : playerList) {
            InitialCard initialCard = game.drawInitialCard();
            if (initialCard != null) {
                player.setInitialCard(initialCard);
                initialCard.addResources(player);
             //  initialCard.setFrontSide(true);
                updateCheckboard(player, initialCard);
            }
        }
    }

    private void updateCheckboard(Player player, InitialCard initialCard) {
        Boards gameBoard = player.getGameBoards();
        if (gameBoard != null) {
            int[][] checkboard = gameBoard.getCheckBoard();
            if (checkboard != null) {
                checkboard[50][50] = 1;
                gameBoard.getGameBoard()[50][50] = initialCard;
                updateCheckboardBasedOnCard(checkboard, initialCard);
            }
        }
    }

    private void updateCheckboardBasedOnCard(int[][] checkboard, InitialCard initialCard) {
        if (initialCard.isFrontSide()) {
            updateCheckboardForAngles(checkboard, initialCard.getFrontAngles());
        } else {
            updateCheckboardForAngles(checkboard, initialCard.getBackAngles());
        }
    }

    private void updateCheckboardForAngles(int[][] checkboard, VisibleAngle[] angles) {
        if (angles != null) {
            if (angles[0] != null) {
                checkboard[49][49] = 0;
            }
            if (angles[1] != null) {
                checkboard[51][49] = 0;
            }
            if (angles[2] != null) {
                checkboard[49][51] = 0;
            }
            if (angles[3] != null) {
                checkboard[51][51] = 0;
            }
        }
    }

    //Distribute the initial card to every player
    //placing it in the middle of the player board game


    //initialize resource cards
    public void drawResourceCards() {
        //Draw a resource card from the resource deck of the game
        ResourceCard card = game.drawResourceCard();
        //add the card to the player hand
        game.getCurrentPlayer().addResourceCard(card);
        //Remove that card from the resource deck
        // game.removeFormResourceDeck(card);
    }

    //initialize gold cards
    public void drawGoldCards() {
        //Draw a gold card from the common deck
        GoldCard card = game.drawGoldCard();
        //Add it to the hand of the player
        game.getCurrentPlayer().addGoldCard(card);
        //remove it from the gold deck
        // game.removeFormGoldDeck(card);
    }

    //initialize obj cards
    public void drawObjectiveCards() {
        //Draw a gold card from the common deck
        ObjectiveCard card = game.drawObjectiveCard();
        //Add it to the hand of the player
        game.getCurrentPlayer().addSelObjectiveCard(card);
        //remove it from the gold deck
        // game.removeFormGoldDeck(card);
    }


    //initialize objective cards
    public ObjectiveCard[] sendChoice() {
        ObjectiveCard[] choice = new ObjectiveCard[2];
        for (int i = 0; i < 2; i++) {
            choice[i] = game.drawObjectiveCard();
        }
        return choice;
    }

    public void setObjectiveCards(ObjectiveCard choice) {
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

    }
}