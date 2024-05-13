package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameBoard;
import it.polimi.ingsw.server.model.VisibleAngle;

public class AvailableResourcesManager {
    //Object of this class has to take the count of the amount
    //of resources a player has in that particular moment
    //it sees the board of the player and access that, then if checks which
    //card played in front side has visible angles not covered and with resource on it
    //and if a card has being played back side=> look at back symbol

    //game associated with
    private final Game game;

    //we can have an array of resources which are really int
    //index = type of symbol
    //take the list from "card manager"
    private int[] resourceStorage = new int[7];

    //constructor
    public AvailableResourcesManager(Game game){this.game = game;}

    //Getter of the resource list
    public int[] getResourceStorage() {
        return resourceStorage;
    }

    //make the count of the resources present
    //aka count visible symbols
    public void setResourceStorage(){
        //we first access the player board
        GameBoard currentBoard = game.getCurrentPlayer().getGameBoard();
        //We cycle on every box of the grid
        for (int i = 0; i < currentBoard.getMAX_X(); i++){
            for (int j = 0; j < currentBoard.getMAX_Y(); j++) {
                //We check if there is a card there
                if (currentBoard.getCardMatrix()[i][j] != null){
                    //Check first if played front or back side
                    if(currentBoard.getCardMatrix()[i][j].isFrontSide()){
                        //if it's front side we check all the angles
                        //if they are "visible angles"
                        //we check if they are covered
                        for (int a = 0; a < 4; a++){
                            //check class
                            if(currentBoard.getCardMatrix()[i][j].getFrontAngles()[a] instanceof VisibleAngle){
                                //Check coverage and presence of a symbol
                                if(((VisibleAngle) currentBoard.getCardMatrix()[i][j].getFrontAngles()[a]).isNotCovered() &&
                                        (((VisibleAngle) currentBoard.getCardMatrix()[i][j].getFrontAngles()[a]).getSymbol() != null)){
                                    //get the symbol inside it
                                    String symbolType = ((VisibleAngle) currentBoard.getCardMatrix()[i][j].getFrontAngles()[a]).getSymbol().getSymbolName();
                                    //call the adder
                                    symbolAdder(symbolType);

                                }
                            }
                        }
                    }
                    //If it's back side we check the back symbol
                    else {
                        //get the symbol
                        String currentBackSymbol = currentBoard.getCardMatrix()[i][j].getBackSymbol().getSymbolName();
                        //call the adder
                        symbolAdder(currentBackSymbol);
                    }
                }
            }
        }
    }


    //adder of the specific symbol we found "open"
    public void symbolAdder(String symbol){
        //switch case on the type
        switch(symbol) {
            //increment the corresponding "resource pool"
            case "mushroom":
                resourceStorage[0]++;
            case "leaf":
                resourceStorage[1]++;
            case "fox":
                resourceStorage[2]++;
            case "butterfly":
                resourceStorage[3]++;
            case "sword":
                resourceStorage[4]++;
            case "bottle":
                resourceStorage[5]++;
            case "scroll":
                resourceStorage[6]++;
        }
    }
}
