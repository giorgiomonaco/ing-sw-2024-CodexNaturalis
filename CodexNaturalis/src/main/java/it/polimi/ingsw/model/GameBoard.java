package it.polimi.ingsw.model;


public class GameBoard {

    /*
    Grid representing the game board
    Implementing a grid of card were those can be placed and a grid of integer for easier checks
    Checkboard= -1 empty slot
    chechboard= 0 placeable slot
    checkboard= 1 there is a card
     */


    //dimensions of the matrix
    private final int MAX_X = 100;
    private final int MAX_Y = 100;
    
    /*
    2 important couples of indexes:
    highest and lowest X and Y used.
    Used to print the matrix not entirely but partially
     */
    private int[] usedX = {-1,-1};
    private int[] usedY = {-1,-1};
    
    private final Card[][] cardMatrix = new Card[MAX_X][MAX_Y];
    private int[][] checkBoard = new int [MAX_X][MAX_Y];

    //reset of checkBoard
    public void cleanCheckBoard(){
        for(int i=0;i<MAX_X;i++){
            for(int j=0;j<MAX_Y;j++){
                checkBoard[i][j] = -1;
            }
        }
    }

    //we insert a card
    public void insertCard(Card card, int x, int y) {
       /*
       If we are here we have already done checks on the possibility to place a card
       We simply place it
        */
        cardMatrix[x][y] = card;
        /*
        Then we update all the boxes that become playable
         */
        updateBoxes(card, x, y);
        
    }

    //insertion of the initial card in the middle of the board
    public void placeInitialCard(InitialCard card) {
        cardMatrix[(MAX_X / 2)][MAX_Y / 2] = card;
        updateBoxes(card,MAX_X / 2, MAX_Y / 2);
    }

    /*
    This method updates all the boxed that may become playable
    and the one in which we played a card into a used one
     */
    public void updateBoxes(Card card, int x, int y){
        if(card.isFrontSide()){
            if((card.getFrontVisibleAngle(3)!=null) &&
                    (checkBoard[x-1][y+1] != 1)){
                checkBoard[(x)-1][(y)+1]=0;
            }
            if(card.getFrontVisibleAngle(0)!=null &&
                    (checkBoard[x+1][y+1] != 1)){
                checkBoard[(x)+1][(y)+1]=0;
            }
            if(card.getFrontVisibleAngle(1)!=null &&
                    (checkBoard[x+1][y-1] != 1)){
                checkBoard[(x)+1][(y)-1]=0;
            }
            if(card.getFrontVisibleAngle(2)!=null &&
                    (checkBoard[x-1][y-1] != 1)){
                checkBoard[(x)-1][(y)-1]=0;
            }
        } else {
            if(card.getBackVisibleAngle(3)!=null &&
                    (checkBoard[x-1][y+1] != 1)){
                checkBoard[(x)-1][(y)+1]=0;
            }
            if(card.getBackVisibleAngle(0)!=null &&
                    (checkBoard[x+1][y+1] != 1)){
                checkBoard[(x)+1][(y)+1]=0;
            }
            if(card.getBackVisibleAngle(1)!=null &&
                    (checkBoard[x+1][y-1] != 1)){
                checkBoard[(x)+1][(y)-1]=0;
            }
            if(card.getBackVisibleAngle(2)!=null &&
                    (checkBoard[x+1][y-1] != 1)){
                checkBoard[(x)-1][(y)-1]=0;
            }
        }
        /*
        Finally we update the usedX and usedY
        To inform how much of the matrix we have used
        so to print only a portion and not the whole matrix
         */
        if(usedX[0] == -1 && usedX[1] == -1 && usedY[0] == -1 && usedY[1] == -1){
            usedX[0] = usedX[1] = x;
            usedY[0] = usedY[1] = y;
        } else {
            if(x < usedX[0])
                usedX[0] = x;
            if(x > usedX[1])
                usedX[1] = x;
            if(y < usedY[0])
                usedY[0] = y;
            if(y > usedY[1])
                usedY[1] = y;
        }
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

    public Card getSpecificCard(int x, int y){
        return cardMatrix[x][y];
    }

    //l'utente selezionerà a schermo le coordinate dove piazzare la carta che vuole giocare, a questo punto
    //possiamo o rendere empty l'angolo sottostante o linkarlo alla carta sopra

/*
    public void checkRightPlacement(Card placingCard, int x, int y){

        if (CheckBoard[x][y]==0){
            if (CheckBoard[x-1][y-1]==1 && cardMatrix[x-1][y-1]!=null && placingCard.getFrontVisibleAngle(0)!=null ){


            }
        }

    }
*/

    //getters and setters of used x and y
    public int[] getUsedX() {
        return usedX;
    }
    public int[] getUsedY() {
        return usedY;
    }
    public void setUsedX(int[] usedX) {
        this.usedX = usedX;
    }
    public void setUsedY(int[] usedY) {
        this.usedY = usedY;
    }

    //getters of Checkboard

    public int[][] getCheckBoard() {
        return checkBoard;
    }
    public int getCheckBoxValue(int x, int y){
        return checkBoard[x][y];
    }
}
