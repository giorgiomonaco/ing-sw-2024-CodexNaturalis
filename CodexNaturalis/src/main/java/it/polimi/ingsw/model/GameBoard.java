package it.polimi.ingsw.model;


public class GameBoard {

    //Grid representing the game board
    //Implementing a grid of card were those can be placed and a grid of integer for easier checks
    //Checkboard= -1 empty slot
    //chechboard= 0 placeable slot
    //checkboard= 1 there is a card


    //dimensions of the matrix
    private final int MAX_X = 100;
    private final int MAX_Y = 100;
    private final Card[][] cardMatrix = new Card[MAX_X][MAX_Y];
    private int[][] CheckBoard = new int [MAX_X][MAX_Y];

    //resetter of checkBoard
    public int[][] cleanCheckBoard(int[][] currentCheckBoard){
        currentCheckBoard = this.CheckBoard;
        for(int i=0;i<MAX_X;i++){
            for(int j=0;j<MAX_Y;j++){
                currentCheckBoard[i][j] = -1;
            }
        }

        return currentCheckBoard;
    }

    //we insert a card
    public void insertCard(Card card, int x, int y) {
       /* checkRightPlacement(card, x, y);
        cardMatrix[x][y] = card;
        CheckBoard[x][y] = 1; */
        if(CheckBoard[x][y]==0){
            if(card.isFrontSide()){
                if(true){
                    //se ha angolo è piazzabile
            }
            }

        }
        
    }

    //insertion of the initial card in the middle of the board
    public void placeInitialCard(InitialCard card) {
        cardMatrix[(MAX_X / 2)][MAX_Y / 2] = card;
        CheckBoard[(MAX_X / 2)][MAX_Y / 2] = 1;
        if(card.isFrontSide()){
        if(card.getFrontVisibleAngle(3)!=null){
                CheckBoard[(MAX_X / 2)-1][(MAX_Y / 2)-1]=0;
        }
        if(card.getFrontVisibleAngle(0)!=null){
            CheckBoard[(MAX_X / 2)+1][(MAX_Y / 2)+1]=0;
        }
        if(card.getFrontVisibleAngle(1)!=null){
            CheckBoard[(MAX_X / 2)+1][(MAX_Y / 2)-1]=0;
        }
        if(card.getFrontVisibleAngle(2)!=null){
            CheckBoard[(MAX_X / 2)-1][(MAX_Y / 2)+1]=0;
        }
        }
        else {if(card.getBackVisibleAngle(3)!=null){
            CheckBoard[(MAX_X / 2)-1][(MAX_Y / 2)-1]=0;
        }
            if(card.getBackVisibleAngle(0)!=null){
                CheckBoard[(MAX_X / 2)+1][(MAX_Y / 2)+1]=0;
            }
            if(card.getBackVisibleAngle(1)!=null){
                CheckBoard[(MAX_X / 2)+1][(MAX_Y / 2)-1]=0;
            }
            if(card.getBackVisibleAngle(2)!=null){
                CheckBoard[(MAX_X / 2)-1][(MAX_Y / 2)+1]=0;
            }
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

    //l'utente selezionerà a schermo le coordinate dove piazzare la carta che vuole giocare, a questo punto
    //possiamo o rendere empty l'angolo sottostante o linkarlo alla carta sopra


    public void checkRightPlacement(Card placingCard, int x, int y){

        if (CheckBoard[x][y]==0){
            if (CheckBoard[x-1][y-1]==1 && cardMatrix[x-1][y-1]!=null && placingCard.getFrontVisibleAngle(0)!=null ){


            }
        }

    }

}
