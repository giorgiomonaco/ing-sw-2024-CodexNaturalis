package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;

import java.util.*;
public class DrawManager {
    //This class manage all the drawing of the game
    private final Game game;

    //Constructor
    public DrawManager(Game game) {
        this.game = game;
    }

    /**
     * Initializes the hand of each player in the game.
     * This method sets up the initial hand for each player by drawing the necessary cards.
     * Steps performed for each player:
     * <ul>
     *   <li>Set the current player.</li>
     *   <li>Draw and add 2 initial resource cards.</li>
     *   <li>Draw and add 1 gold card.</li>
     *   <li>Draw and add 2 objective cards.</li>
     * </ul>
     */
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

    /**
     * Distributes the initial card to every player.
     * This method draws an initial card for each player and places it in the middle
     * of the player's board game. It also updates the checkboard accordingly.
     * Steps performed for each player:
     * <ul>
     *   <li>Draw an initial card.</li>
     *   <li>If the initial card is not null, set it as the player's initial card.</li>
     *   <li>Update the checkboard with the initial card.</li>
     * </ul>
     */
    public void distributeInitialCards() {
        List<Player> playerList = game.getPlayerList();
        for (Player player : playerList) {
            InitialCard initialCard = game.drawInitialCard();
            if (initialCard != null) {
                player.setInitialCard(initialCard);

                //  initialCard.setFrontSide(true);
                updateCheckboard(player, initialCard);
            }
        }
    }


    /**
     * Updates the checkboard for the given player with the specified initial card.<br>
     * This method places the initial card in the center of the player's game board
     * and updates the checkboard to reflect the card's placement.
     * @param player The player whose checkboard is being updated.
     * @param initialCard The initial card to place on the checkboard.
     */
    private void updateCheckboard(Player player, InitialCard initialCard) {
        Boards gameBoard = player.getGameBoards();
        if (gameBoard != null) {
            int[][] checkboard = gameBoard.getCheckBoard();
            if (checkboard != null) {
                checkboard[50][50] = 1;
                gameBoard.getGameBoard()[50][50] = initialCard;
            }
        }
    }


    /**
     * Draws a resource card from the resource deck and adds it to the current player's hand.
     */
    public void drawResourceCards() {

        ResourceCard card = game.drawResourceCard();
        //add the card to the player hand
        game.getCurrentPlayer().addResourceCard(card);
        //Remove that card from the resource deck
        // game.removeFormResourceDeck(card);
    }

    /**
     * Draws a gold card from the resource deck and adds it to the current player's hand.
     */
    public void drawGoldCards() {
        //Draw a gold card from the common deck
        GoldCard card = game.drawGoldCard();
        //Add it to the hand of the player
        game.getCurrentPlayer().addGoldCard(card);
        //remove it from the gold deck
        // game.removeFormGoldDeck(card);
    }

    /**
     * Draws an objective card from the resource deck and adds it to the current player's hand.
     */
    public void drawObjectiveCards() {
        //Draw a gold card from the common deck
        ObjectiveCard card = game.drawObjectiveCard();
        //Add it to the hand of the player
        game.getCurrentPlayer().addSelObjectiveCard(card);
        //remove it from the gold deck
    }

}