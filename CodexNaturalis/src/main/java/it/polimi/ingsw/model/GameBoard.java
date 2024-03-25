package it.polimi.ingsw.model;

public class GameBoard {
    private Card[][] boardMatrix;
    private static final int sideLength = 115;
    private Player player;

    //constructor
    public GameBoard() {
        this.boardMatrix = new Card[sideLength][sideLength];
    }

    //methods
    public Card getCard(int x, int y){
        if ((x < 0 || x > sideLength) || (y <0 || y > sideLength) || boardMatrix[x][y] == null){
            System.out.println("Operazione Fallita");
            return null;
        }
        else{
            System.out.println("Operazione avvenuta con successo");
            return boardMatrix[x][y];
        }
    }
    public boolean placeCard(int x, int y, Card card){
        if ((x < 0 || x > sideLength) || (y <0 || y > sideLength) || boardMatrix[x][y] == null){
            System.out.println("Operazione Fallita");
            return false;
        }
        else{
            System.out.println("Operazione avvenuta con successo");
            this.boardMatrix[x][y] = card;
            return true;
        }

    }
}
