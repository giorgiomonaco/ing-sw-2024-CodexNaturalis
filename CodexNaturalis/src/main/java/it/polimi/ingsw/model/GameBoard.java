public class GameBoard {

    //Grid representing the game board
    //It may be a matrix of cards

    //dimensions of the matrix
    private final int MAX_X = 10;
    private final int MAX_Y = 10;
    private final Card[][] cardMatrix = new Card[MAX_X][MAX_Y];

    //we insert a card
    public void insertCard(Card card, int x, int y){
        cardMatrix[x][y] = card;
    }

    //insertion of the initial card in the middle of the board
    public void placeInitialCard(InitialCard card){
        cardMatrix[(MAX_X/2)][MAX_Y/2] = card;
    }

    //get max x dimension of the matrix
    public int getMAX_X(){
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
}
