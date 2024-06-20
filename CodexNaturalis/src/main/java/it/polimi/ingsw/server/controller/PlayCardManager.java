package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

public class PlayCardManager {

    private final Game game;
    private final Player player;
    private Boards board;

    public PlayCardManager(Game game) {
        this.game = game;
        player = game.getCurrentPlayer();
    }


    /**
     * Plays a card on the game board at the specified coordinates and with the specified side.
     * This method updates the game board, the check board, and the player's resources accordingly.
     *
     * @param card The card to be played.
     * @param x The x-coordinate where the card is to be placed.
     * @param y The y-coordinate where the card is to be placed.
     * @param side The side of the card to be displayed (true for front side, false for back side).
     */
    public void playCard(Card card, int x, int y, boolean side) {
        //assigns a value to the direction the card is facing
        card.setFrontSide(side);

        game.getCurrentPlayer().getGameBoards().getGameBoard()[x][y] = card;
        game.getCurrentPlayer().getGameBoards().getCheckBoard()[x][y] = 1;
        card.addResources(player);

        updateBoxes(card, x, y, side);
        updatePlayerResources(x, y, game.getCurrentPlayer().getGameBoards().getGameBoard());
    }


    /**
     *  Sets the player's checkboard to the specified checkboard.
     * @param checkBoard
     */
    public void setCheckBoard(int[][] checkBoard) {
        game.getCurrentPlayer().getGameBoards().setCheckBoard(checkBoard);
    }

    /**
     * Updates the check board based on the card placed at the specified coordinates and the side of the card.
     * This method updates the visibility and the playability of the adjacent boxes.
     *
     * @param card The card that has been placed.
     * @param x The x-coordinate of the placed card.
     * @param y The y-coordinate of the placed card.
     * @param side The side of the card that is displayed (true for front side, false for back side).
     */
    private void updateBoxes(Card card, int x, int y, boolean side) {
        int[][] checkBoard = game.getCurrentPlayer().getGameBoards().getCheckBoard();

        updateCheckBoard(checkBoard, x + 1, y + 1, side ? card.getFrontVisibleAngle(3) : null);
        updateCheckBoard(checkBoard, x + 1, y - 1, side ? card.getFrontVisibleAngle(1) : null);
        updateCheckBoard(checkBoard, x - 1, y + 1, side ? card.getFrontVisibleAngle(0) : null);
        updateCheckBoard(checkBoard, x - 1, y - 1, side ? card.getFrontVisibleAngle(2) : null);

        game.getCurrentPlayer().getGameBoards().setCheckBoard(checkBoard);  // Ensure the updated checkBoard is set back to the player
    }

    /**
     * Updates the check board at the specified coordinates based on the visibility angle of a card.
     * If the box is not already used (value 1) and the angle is not null, it sets the box as playable (value 0).
     *
     * @param checkBoard The check board matrix to be updated.
     * @param x The x-coordinate of the box to be updated.
     * @param y The y-coordinate of the box to be updated.
     * @param angle The visibility angle of the card affecting the box.
     */
    private void updateCheckBoard(int[][] checkBoard, int x, int y, VisibleAngle angle) {
        if (checkBoard[x][y] != 1 && angle != null) {
            checkBoard[x][y] = 0;
        }
    }



    /**
     * Updates the player's resources based on the visibility of covered angles of neighboring cards.
     * Checks the surrounding positions (diagonals) for cards and updates resources if the card's
     * visible angle covers the resource.
     *
     * @param x The x-coordinate of the current card.
     * @param y The y-coordinate of the current card.
     * @param cardBoard The game board containing the cards.
     */
    private void updatePlayerResources(int x, int y, Card[][] cardBoard) {

        int[] resources = game.getCurrentPlayer().getResourcesAvailable();
        VisibleAngle coveredAngle = null;

        if (cardBoard[x+1][y+1] != null) {
            boolean front = cardBoard[x+1][y+1].getSide();
            if (front) {
                coveredAngle = cardBoard[x+1][y+1].getFrontVisibleAngle(0);
            }
        }

        if (cardBoard[x+1][y-1] != null) {
            boolean front = cardBoard[x+1][y-1].getSide();
            if (front) {
                coveredAngle = cardBoard[x+1][y+1].getFrontVisibleAngle(2);
            }
        }

        if (cardBoard[x-1][y+1] != null) {
            boolean front = cardBoard[x-1][y+1].getSide();
            if (front) {
                coveredAngle = cardBoard[x-1][y+1].getFrontVisibleAngle(1);
            }
        }

        if (cardBoard[x-1][y-1] != null) {
            boolean front = cardBoard[x-1][y-1].getSide();
            if (front) {
                coveredAngle = cardBoard[x-1][y-1].getFrontVisibleAngle(3);
            }
        }

        if (coveredAngle != null) {
            game.getCurrentPlayer().resourceLowering(coveredAngle.getSymbol());
        }

    }
}