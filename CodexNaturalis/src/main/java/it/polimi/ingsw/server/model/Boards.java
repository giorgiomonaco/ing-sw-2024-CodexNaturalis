package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * This class represents the boards of the game.
 */
public class Boards implements Serializable {
    public Card[][] gameBoard;
    public int[][] checkBoard;
    private final int MAX_X = 100;
    private final int MAX_Y = 100;

    /**
     *  Constructor
     */
    public Boards(){
        this.gameBoard = new Card[MAX_X][MAX_Y];
        this.checkBoard = new int[MAX_X][MAX_Y];
    }

    /**
     *  Getter MAX_X
     * @return MAX_X
     */
    public int getMAX_X() {
        return MAX_X;
    }

    /**
     *  Getter MAX_Y
     * @return MAX_Y
     */

    public int getMAX_Y() {
        return MAX_Y;
    }

    /**
     * Getter gameBoard
     * @return gameBoard
     */
    public Card[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * Getter checkBoard
     * @return checkBoard
     */
    public int[][] getCheckBoard() {
        return checkBoard;
    }

    /**
     * Setter checkBoard
     * @param checkBoard
     */
    public void setCheckBoard(int[][] checkBoard) {
        this.checkBoard = checkBoard;
    }

    /**
     * Setter gameBoard
     * @param gameBoard
     */
    public void setGameBoard(Card[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Sets the value at the specified coordinates on the board.
     *
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @param value the value to set at the specified coordinates. The value can be:
     *              <ul>
     *                <li>-2: unplayable box</li>
     *                <li>-1: empty box</li>
     *                <li> 0: playable box</li>
     *                <li> 1: used box</li>
     *              </ul>
     */
    public void setCheckBoardXY(int x, int y, int value) {
        this.checkBoard[x][y] = value;
    }


    /**
     * Sets the value at the specified coordinates on the board.
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @param card  the value to set at the specified coordinates
     */
    public void setGameBoardXY(int x, int y, Card card) {
        this.gameBoard[x][y] = card;
    }

}
