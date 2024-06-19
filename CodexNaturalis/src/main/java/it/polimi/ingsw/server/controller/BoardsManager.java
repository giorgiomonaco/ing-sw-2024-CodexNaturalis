package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

public class BoardsManager {

    private final Game game;
    Boards board;

    //-----------------INIZIALIZZAZIONI----------------
    public BoardsManager(Game game) {
        this.game = game;
    }


    /**
     * Initializes the game boards for every player in the game.
     *<br>
     * This method creates a new game board for each player and associates
     * it with the player.
     * <br>It also cleans the game board by calling the
     * cleanCheckBoard method.
     */
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


   // -------------------------------------FOR CHECKBOARD--------------------------------------

    /**
     * Cleans the check board by setting all cells to -1.
     * <p>
     * This method iterates through the check board and sets each cell to -1,
     * indicating that it is an empty box.
     *
     * <br><br>The value assignments for the check board are:
     * <ul>
     *   <li>-1 = empty box</li>
     *   <li>0 = playable box</li>
     *   <li>1 = used box</li>
     * </ul>
     *
     * @param board The game board to be cleaned.
     */
   public void cleanCheckBoard(Boards board){
    this.board= board;

       for(int i=0;i<board.getMAX_X();i++){
           for(int j=0;j< board.getMAX_Y();j++){
               board.getCheckBoard()[i][j] = -1;
           }
       }
   }
}
