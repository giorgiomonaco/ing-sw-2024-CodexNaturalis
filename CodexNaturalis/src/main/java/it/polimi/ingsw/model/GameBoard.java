package it.polimi.ingsw.model;

public class GameBoard {
    private Card[][] boardMatrix;
    private static final int sideLenght = 115;
    private Player player;


    public GameBoard() {
        this.boardMatrix = new Card[sideLenght][sideLenght];
    }

//methods
    public Card getCard(int x, int y){
        if ((x < 0 || x > sideLenght) || (y <0 || y > sideLenght) || boardMatrix[x][y] == null){
            System.out.println("Operazione Fallita");
            return null;
        }
        else{
            System.out.println("Operazione avvenuta con successo");
            return boardMatrix[x][y];
        }
    }
    public boolean placeCard(int x, int y, Card card){
        if ((x < 0 || x > sideLenght) || (y <0 || y > sideLenght) || boardMatrix[x][y] == null){
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
