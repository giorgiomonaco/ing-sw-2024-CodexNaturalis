package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

import java.util.Scanner;

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
        board.getGameboard()[(board.getMAX_Y() / 2)][board.getMAX_Y() / 2] = card;
        updateBoxes(card,board.getMAX_X() / 2, board.getMAX_Y() / 2);
    }

    //---------------------------------- WHEN CARD PLAYED--------------------------------------

    public void WhenPlay(Card card, int x, int y){
        board.getCheckboard()[x][y]=0;
        board.getGameboard()[x][y]=card;
        updateBoxes(card,x,y);
    }

    //da spostare in TUI view
    public int[] AskWherePlay(Boards board){
        int counter = 0;
        int[][] checker = board.getCheckboard();

        for(int o=0;o<board.getMAX_X();o++){
            for(int p=0;p< board.getMAX_Y();p++) {
                checker[o][p]=0;
            }
            }

        for(int i=0;i<board.getMAX_X();i++){
            for(int j=0;j< board.getMAX_Y();j++) {
                if (board.getCheckboard()[i][j] == -1) System.out.print("0 ");
                if (board.getCheckboard()[i][j] == 0){
                    counter++;
                    checker[i][j]=counter;
                    System.out.print(counter + " ");
                }
                if (board.getCheckboard()[i][j] == 1) System.out.print("C ");
            }
            System.out.println();
            }
        System.out.println("which box you wanna place the Card?");

        Scanner t= new Scanner(System.in);
        int o = t.nextInt();

        int q[] = new int[2];
        for(int u=0;u<board.getMAX_X();u++){
            for(int y=0;y< board.getMAX_Y();y++) {
               if (checker[u][y]== o) {
                   q[0]=u;
                   q[1]=y;

               }
            }
        }
    return q;
    }







   // -------------------------------------FOR CHECKBOARD--------------------------------------

    // value -1 = empty box
    //value 0 = playable box
    // value 1 = used box


    // set tutte le caselle a -1
   private Boards cleanCheckBoard(Boards board){
    this.board= board;

       for(int i=0;i<board.getMAX_X();i++){
           for(int j=0;j< board.getMAX_Y();j++){
               board.getCheckboard()[i][j] = -1;
           }
       }

       return board;
   }

    public void updateBoxes(Card card, int x, int y){
        if(card.isFrontSide()){
            if((card.getFrontVisibleAngle(3)!=null) &&
                    (board.getCheckboard()[x-1][y+1] != 1)){
                board.getCheckboard()[(x)-1][(y)+1]=0;
            }
            if(card.getFrontVisibleAngle(0)!=null &&
                    (board.getCheckboard()[x+1][y+1] != 1)){
                board.getCheckboard()[(x)+1][(y)+1]=0;
            }
            if(card.getFrontVisibleAngle(1)!=null &&
                    (board.getCheckboard()[x+1][y-1] != 1)){
                board.getCheckboard()[(x)+1][(y)-1]=0;
            }
            if(card.getFrontVisibleAngle(2)!=null &&
                    (board.getCheckboard()[x-1][y-1] != 1)){
                board.getCheckboard()[(x)-1][(y)-1]=0;
            }
        } else {
            if (card.getBackVisibleAngle(3) != null &&
                    (board.getCheckboard()[x - 1][y + 1] != 1)) {
                board.getCheckboard()[(x) - 1][(y) + 1] = 0;
            }
            if (card.getBackVisibleAngle(0) != null &&
                    (board.getCheckboard()[x + 1][y + 1] != 1)) {
                board.getCheckboard()[(x) + 1][(y) + 1] = 0;
            }
            if (card.getBackVisibleAngle(1) != null &&
                    (board.getCheckboard()[x + 1][y - 1] != 1)) {
                board.getCheckboard()[(x) + 1][(y) - 1] = 0;
            }
            if (card.getBackVisibleAngle(2) != null &&
                    (board.getCheckboard()[x + 1][y - 1] != 1)) {
                board.getCheckboard()[(x) - 1][(y) - 1] = 0;
            }
        }
    }









}
