package it.polimi.ingsw.model;


public class GameBoard {

    //Grid representing the game board
    //It may be a matrix of cards

    //dimensions of the matrix
    private final int MAX_X = 100;
    private final int MAX_Y = 100;
    private final Card[][] cardMatrix = new Card[MAX_X][MAX_Y];
    private int[][] CheckBoard =new int [MAX_X][MAX_Y];

    //resetter of checkBoard
    public int[][] cleanCheckBoard(int[][] currentCheckBoard){
        currentCheckBoard = this.CheckBoard;
        for(int i=0;i<MAX_X;i++){
            for(int j=0;j<MAX_Y;j++){
                currentCheckBoard[i][j] = 0;
            }
        }
        return currentCheckBoard;
    }

    //we insert a card
    public void insertCard(Card card, int x, int y) {
       // checkRightPlacement(card, x, y);
        cardMatrix[x][y] = card;
        CheckBoard[x][y] = 1;
    }

    //insertion of the initial card in the middle of the board
    public void placeInitialCard(InitialCard card) {
        cardMatrix[(MAX_X / 2)][MAX_Y / 2] = card;
        CheckBoard[(MAX_X / 2)][MAX_Y / 2] = 1;
    }

    //get max x dimension of the matrix
    public int getMAX_X() {
        return MAX_X;
    }

    //get max y dimension of the matrix
    public int getMAX_Y() {
        return MAX_Y;
    }

    //getter of the matrix
    public Card[][] getCardMatrix() {
        return cardMatrix;
    }

    //l'utente selezionerà a schermo le coordinate dove piazzare la carta che vuole giocare, a questo punto
    //possiamo o rendere empty l'angolo sottostante o linkarlo alla carta sopra
    /*
    public void checkRightPlacement(Card placingCard, int x, int y){

        Card upperLeft = cardMatrix[x-1][y-1];
        String topLeft = upperLeft.get("TopLeft").getAsString();
        if (CheckBoard[x][y]==0){
            if (CheckBoard[x-1][y-1]==1 && cardMatrix[x-1][y-1]!=null && upperLeft. )
        }
            SERVE CAPIRE COME LEGGERE NON ASSENZA DI CORNER DELLA CARTA SELEZIONATA DAL FILE JSON
    }
    */
}
