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
        this.board = player.getGameboard();
        Card[][] cardBoard = board.getGameboard();
        int[][] checkBoard = board.getCheckboard();

        card.setFrontSide(side);

        cardBoard[x][y] = card;
        checkBoard[x][y] = 1;
        card.addResources(player);

        updateBoxes(card, x, y, checkBoard, side);
        updatePlayerResources(x, y, cardBoard);
    }

    private void updateBoxes(Card card, int x, int y, int[][] checkBoard, boolean side) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        try {
            if (side) {
                if (checkBoard[x + 1][y + 1] != 1 && card.getFrontVisibleAngle(3) != null) {
                    checkBoard[x + 1][y + 1] = 0;
                }
                if (checkBoard[x + 1][y - 1] != 1 && card.getFrontVisibleAngle(1) != null) {
                    checkBoard[x + 1][y - 1] = 0;
                }
                if (checkBoard[x - 1][y + 1] != 1 && card.getFrontVisibleAngle(0) != null) {
                    checkBoard[x - 1][y + 1] = 0;
                }
                if (checkBoard[x - 1][y - 1] != 1 && card.getFrontVisibleAngle(2) != null) {
                    checkBoard[x - 1][y - 1] = 0;
                }
            } else {
                if (checkBoard[x + 1][y + 1] != 1) {
                    checkBoard[x + 1][y + 1] = 0;
                }
                if (checkBoard[x + 1][y - 1] != 1) {
                    checkBoard[x + 1][y - 1] = 0;
                }
                if (checkBoard[x - 1][y + 1] != 1) {
                    checkBoard[x - 1][y + 1] = 0;
                }
                if (checkBoard[x - 1][y - 1] != 1) {
                    checkBoard[x - 1][y - 1] = 0;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid coordinates", e);
        }
    }

    private void updatePlayerResources(int x, int y, Card[][] cardBoard) {

        int[] resources = player.getResourcesAvailable();
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
            player.resourceLowering(coveredAngle.getSymbol());
        }

    }
}