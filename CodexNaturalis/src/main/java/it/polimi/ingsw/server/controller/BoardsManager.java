package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

public class BoardsManager {

    private Game game;
    Boards board;

    //-----------------INIZIALIZZAZIONI----------------
    public BoardsManager(Game game) {
        this.game = game;
    }

    public void initializeBoards() {
        //fore every player in the game
        for (Player p : game.getPlayerList()) {
            //create a new game board
            Boards gameBoard = new Boards();
            this.board = gameBoard;
            cleanCheckBoard(gameBoard);
            //Associate the game board to every player
            p.setBoards(gameBoard);
        }
    }

    public void placeInitialCard(InitialCard card) {
        board.getGameBoard()[(board.getMAX_Y() / 2)][board.getMAX_Y() / 2] = card;
        updateBoxes(card,board.getMAX_X() / 2, board.getMAX_Y() / 2);
    }

    //---------------------------------- WHEN CARD PLAYED--------------------------------------

    public void WhenPlay(Card card, int x, int y){
        board.getCheckBoard()[x][y]=0;
        board.getGameBoard()[x][y]=card;
        updateBoxes(card,x,y);
    }

    //da spostare in TUI view








   // -------------------------------------FOR CHECKBOARD--------------------------------------

    // value -1 = empty box
    //value 0 = playable box
    // value 1 = used box


    // set tutte le caselle a -1
   private Boards cleanCheckBoard(Boards board){
    this.board= board;

       for(int i=0;i<board.getMAX_X();i++){
           for(int j=0;j< board.getMAX_Y();j++){
               board.getCheckBoard()[i][j] = -1;
           }
       }

       return board;
   }

    public void updateBoxes(Card card, int x, int y){
        if(card.isFrontSide()){
            if((card.getFrontVisibleAngle(3)!=null) &&
                    (board.getCheckBoard()[x-1][y+1] != 1)){
                board.getCheckBoard()[(x)-1][(y)+1]=0;
            }
            if(card.getFrontVisibleAngle(0)!=null &&
                    (board.getCheckBoard()[x+1][y+1] != 1)){
                board.getCheckBoard()[(x)+1][(y)+1]=0;
            }
            if(card.getFrontVisibleAngle(1)!=null &&
                    (board.getCheckBoard()[x+1][y-1] != 1)){
                board.getCheckBoard()[(x)+1][(y)-1]=0;
            }
            if(card.getFrontVisibleAngle(2)!=null &&
                    (board.getCheckBoard()[x-1][y-1] != 1)){
                board.getCheckBoard()[(x)-1][(y)-1]=0;
            }
        } else {
            if (card.getBackVisibleAngle(3) != null &&
                    (board.getCheckBoard()[x - 1][y + 1] != 1)) {
                board.getCheckBoard()[(x) - 1][(y) + 1] = 0;
            }
            if (card.getBackVisibleAngle(0) != null &&
                    (board.getCheckBoard()[x + 1][y + 1] != 1)) {
                board.getCheckBoard()[(x) + 1][(y) + 1] = 0;
            }
            if (card.getBackVisibleAngle(1) != null &&
                    (board.getCheckBoard()[x + 1][y - 1] != 1)) {
                board.getCheckBoard()[(x) + 1][(y) - 1] = 0;
            }
            if (card.getBackVisibleAngle(2) != null &&
                    (board.getCheckBoard()[x + 1][y - 1] != 1)) {
                board.getCheckBoard()[(x) - 1][(y) - 1] = 0;
            }
        }
    }









}
