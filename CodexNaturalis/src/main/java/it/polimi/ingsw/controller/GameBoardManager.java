package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.ViewTry;
import it.polimi.ingsw.model.Coordinates;

import java.util.List;
import java.util.ArrayList;

public class GameBoardManager {
    //This class create all the boards for the players
    private final Game game;
    private final ViewTry view;

    //Counter used to know how many boxes available
    private int boxCounter;
    /*
    Array to associate grid boxes with the counter,
    useful to retrieve the box obj from the player selection of the box
     */
    private List<Coordinates> listOfPlayableBoxes = new ArrayList<>();

    //Constructor
    public GameBoardManager(Game game, ViewTry view) {
        this.game = game;
        this.view = view;
    }

    //initialization of all the game boards needed
    public void initializeBoards() {
        //fore every player in the game
        for (Player p : game.getPlayerList()) {
            //create a new game board
            GameBoard gameBoard = new GameBoard();
            gameBoard.cleanCheckBoard();
            //Associate the game board to every player
            p.setGameBoard(gameBoard);
        }
    }

    public void playCard(Card card, int position){
        /*
        First we want to retrieve the location he chose from index to coordinates
        Then we want to place the card into the board and update the usedX and usedY
         */
        int cardX = listOfPlayableBoxes.get(position).getX();
        int cardY = listOfPlayableBoxes.get(position).getY();

        /*
        Then we want to place the card
         */
        game.getCurrentPlayer().getGameBoard().insertCard(card, cardX, cardY);
        /*
        After we inserted the card we want to update the visible angle used to play the card
        How?
        From the position we placed the card to,
        we can look around it, if there is a card around (1 box close)
        then we have used its corner, so we cover it
         */
        Coordinates usedCard = findUsedCard(cardX,cardY);
        /*
        Once we found the card,
        we want to cover the right angle
         */
        updatedUsedAngle(usedCard.getX(), usedCard.getY(), cardX, cardY);

    }

    /*
    Method to displace the partial grid to the player to choose where to place the card
     */
    public void displaceAvailableBoxes(){
        /*
        We access to the current player game board
        to find the used x and y to displace not the entire matrix
         */
        int[] usedX = game.getCurrentPlayer().getGameBoard().getUsedX();
        int[] usedY = game.getCurrentPlayer().getGameBoard().getUsedY();

        //counter for selecting the wanted space later on
        boxCounter = 0;
        /*
        We call the print of a box:
        if not playable : value in the matrix = nothing
        if playable : value of a counter in the box
        if a card present : that card (ID maybe by now)
         */
        //Print the top of the grid
        view.printTopOfGrid(usedX, usedY);
        for(int j = usedY[0]-1; j <= usedY[1]+1; j++){
            for(int i = usedX[0]-1; i <= usedX[1]+1; i++){
                switch(game.getCurrentPlayer().getGameBoard().getCheckBoxValue(i,j)) {

                    case -1:
                        view.printNonPlayableBox();
                    case 0:
                        view.printPlayableBox(boxCounter);
                        /*
                        Saves the coordinates of the available box
                         */
                        listOfPlayableBoxes.add(new Coordinates(i,j));
                        boxCounter++;
                    case 1:
                        view.printUsedBox();
                }
                view.printEndOfRaw();

            }
        }
        view.printTopOfGrid(usedX,usedY);
    }

    /*
    Method to find which card has been used to play
    visibleAngle used -> not visible anymore
    param : x and y from card just played
     */
    public Coordinates findUsedCard(int x, int y){
        //we look around the card
        Coordinates foundCard = new Coordinates(-1,-1);
        for(int i = x-1; i<= x+1; i++){
            for (int j = y-1; j <= y+1; j++){
                if (i != x && j != y){
                    if(game.getCurrentPlayer().getGameBoard().getCheckBoxValue(i,j) == 1){
                        foundCard.setX(i);
                        foundCard.setY(j);
                        break;
                    }
                }
            }
        }
        //if we found the card we "covered" we return it
        return foundCard;
    }

    /*
    Method to update all :
    Cover the covered angle by the new placed card
    Subtract the resources of that angle
     */
    public void updatedUsedAngle(int usedCardX, int usedCardY,int placedCardX, int placedCardY){
        if(usedCardX - placedCardX == 1 && usedCardY - placedCardY == -1){
            //Cover angle 3 of used card
            if(game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).isFrontSide())
                game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).coverAngle(3);

        } else if (usedCardX - placedCardX == -1 && usedCardY - placedCardY == -1){
            //cover angle 0
            if(game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).isFrontSide())
                game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).coverAngle(0);
        } else if (usedCardX - placedCardX == -1 && usedCardY - placedCardY == 1){
            //cover angle 1
            if(game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).isFrontSide())
                game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).coverAngle(1);
        } else if (usedCardX - placedCardX == 1 && usedCardY - placedCardY == 1){
            //cover angle 2
            if(game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).isFrontSide())
                game.getCurrentPlayer().getGameBoard().getSpecificCard(usedCardX, usedCardY).coverAngle(2);
        }

    }


}
