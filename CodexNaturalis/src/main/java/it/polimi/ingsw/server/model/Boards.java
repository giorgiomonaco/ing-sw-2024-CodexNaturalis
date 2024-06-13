package it.polimi.ingsw.server.model;

import java.io.Serializable;

public class Boards implements Serializable {
    public Card[][] gameBoard;
    public int[][] checkBoard;
    private final int MAX_X = 100;
    private final int MAX_Y = 100;

    public Boards(){
        this.gameBoard = new Card[MAX_X][MAX_Y];
        this.checkBoard = new int[MAX_X][MAX_Y];
    }

    public int getMAX_X() {
        return MAX_X;
    }

    public int getMAX_Y() {
        return MAX_Y;
    }

    public Card[][] getGameBoard() {
        return gameBoard;
    }

    public int[][] getCheckBoard() {
        return checkBoard;
    }

    public void setCheckBoard(int[][] checkBoard) {
        this.checkBoard = checkBoard;
    }

    public void setGameBoard(Card[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCheckBoardXY(int x, int y, int value) {
        this.checkBoard[x][y] = value;
    }

    public void setGameBoardXY(int x, int y, Card card) {
        this.gameBoard[x][y] = card;
    }

}
