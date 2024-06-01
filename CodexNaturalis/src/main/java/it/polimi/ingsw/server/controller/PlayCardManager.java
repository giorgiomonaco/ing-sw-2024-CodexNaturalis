package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

public class PlayCardManager {

    private final Game game;
    private final Player player;
    private Boards board;

    public PlayCardManager(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public void playCard(Card card, int x, int y, boolean side) {
        //assigns a value to the direction the card is facing
        card.setFrontSide(side);

        game.getCurrentPlayer().getGameboard().getGameboard()[x][y] = card;
        game.getCurrentPlayer().getGameboard().getCheckboard()[x][y] = 1;
        card.addResources(player);

        updateBoxes(card, x, y, side);
        updatePlayerResources(x, y, game.getCurrentPlayer().getGameboard().getGameboard());
    }

    public void setCheckBoard(int[][] checkBoard) {
        game.getCurrentPlayer().getGameboard().setCheckboard(checkBoard);
    }

    private void updateBoxes(Card card, int x, int y, boolean side) {
        int[][] checkBoard = game.getCurrentPlayer().getGameboard().getCheckboard();

        updateCheckBoard(checkBoard, x + 1, y + 1, side ? card.getFrontVisibleAngle(3) : null);
        updateCheckBoard(checkBoard, x + 1, y - 1, side ? card.getFrontVisibleAngle(1) : null);
        updateCheckBoard(checkBoard, x - 1, y + 1, side ? card.getFrontVisibleAngle(0) : null);
        updateCheckBoard(checkBoard, x - 1, y - 1, side ? card.getFrontVisibleAngle(2) : null);

        game.getCurrentPlayer().getGameboard().setCheckboard(checkBoard);  // Ensure the updated checkBoard is set back to the player
    }

    private void updateCheckBoard(int[][] checkBoard, int x, int y, VisibleAngle angle) {
        if (checkBoard[x][y] != 1 && angle != null) {
            checkBoard[x][y] = 0;
        }
    }    private void updatePlayerResources(int x, int y, Card[][] cardBoard) {

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