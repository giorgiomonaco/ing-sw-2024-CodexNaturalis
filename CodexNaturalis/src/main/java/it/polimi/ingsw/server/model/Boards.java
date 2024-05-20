package it.polimi.ingsw.server.model;

public class Boards {
    public Card[][] gameboard;
    public int[][] checkboard;
    private final int MAX_X = 100;
    private final int MAX_Y = 100;

    public int getMAX_X() {
        return MAX_X;
    }

    public int getMAX_Y() {
        return MAX_Y;
    }

    public Card[][] getGameboard() {
        return gameboard;
    }

    public int[][] getCheckboard() {
        return checkboard;
    }

    public void setCheckboard(int[][] checkboard) {
        this.checkboard = checkboard;
    }

    public void setboard(Card[][] gameboard) {
        this.gameboard = gameboard;
    }
}
