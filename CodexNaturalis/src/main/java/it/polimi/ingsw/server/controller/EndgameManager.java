package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EndgameManager {
    //this class gets the game and the player, and calculates the points each player gets by completing his objective
    //every card has a different type of objective, hence the need for this class
    Game game;
    Player player;


    //constructor
    public EndgameManager(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public int objectivePointsCounter() {
        List<ObjectiveCard> obj = new ArrayList<>();
        if(player.getPlayerObjectiveCard() == null || game.getCommonObjectives() == null) return 0;
        obj.add(player.getPlayerObjectiveCard());
        obj.addAll(game.getCommonObjectives());

        //getting the type of the objective (1. cards position -- 2. points for each resource)
        for (ObjectiveCard o : obj) {
            try {
                return switch (o.getType()) {
                    case "position" -> objectiveCreator();
                    case "mushroom", "fox", "leaf", "butterfly", "feather", "bottle", "scroll", "special" ->
                            resourceCounter(o.getType());
                    default -> 0;
                };
            } catch (NullPointerException e) {
                System.err.println("NullPointerException: " + e.getMessage());
            }

        }
        return 0;
    }


    // this function analyzes the objective description to find how many occurrences of a same layout happen
    private int objectiveCreator(){
        // stage one -- finding the first card basing on its color
        ObjectiveCard objectiveCard = this.player.getPlayerObjectiveCard();
        Boards gameBoard = this.player.getGameBoards();

        Card[][] cardMatrix = gameBoard.getGameBoard();


        for (int x = 0; x< gameBoard.getMAX_Y(); x++){
            for (int y = 0; y< gameBoard.getMAX_X(); y++){
                if (Objects.equals(cardMatrix[x][y].getBackSymbol().getFirst().getSymbolName(), objectiveCard.getCard1())){
                    return findPattern(x, y, cardMatrix);
                }
            }
        }


        return 0;
    }
    private int resourceCounter(String type){
        int[] resources = player.getResourcesAvailable();
        ObjectiveCard objectiveCard = this.player.getPlayerObjectiveCard();

        return switch (type) {
            case "mushroom" -> (objectiveCard.getPoints() * Math.floorDiv(resources[0], 3));
            case "leaf" -> (objectiveCard.getPoints() * Math.floorDiv(resources[1], 3));
            case "fox" -> (objectiveCard.getPoints() * Math.floorDiv(resources[2], 3));
            case "butterfly" -> (objectiveCard.getPoints() * Math.floorDiv(resources[3], 3));
            case "feather" -> (objectiveCard.getPoints() * Math.floorDiv(resources[4], 3));
            case "bottle" -> (objectiveCard.getPoints() * Math.floorDiv(resources[5], 3));
            case "scroll" -> (objectiveCard.getPoints() * Math.floorDiv(resources[6], 3));
            case "special" -> specialCounter();
            default -> 0;
        };
    }
    private int specialCounter(){
        int[] resources = this.player.getResourcesAvailable();
        int a = Math.floorDiv(resources[4],3);
        int b = Math.floorDiv(resources[5],3);
        int c = Math.floorDiv(resources[6],3);
        return (findMin(a,b,c));
    }
    private int findMin(int a, int b, int c){
        return Math.min(Math.min(a,b),c);
    }

    //this function verifies if the objective pattern is present and assigns points to the player accordingly
    int findPattern(int x, int y, Card[][] cardMatrix){
        ObjectiveCard objectiveCard = this.player.getPlayerObjectiveCard();
        int newX = x;
        int newY = y;
        boolean first = checkDirection( cardMatrix, x, y, objectiveCard.getDirection1(), objectiveCard.getCard2());
        if (first){
            switch (objectiveCard.getDirection1()) {
                case "down":
                    newY = y + 1;
                    break;
                case "down-right":
                    newY = y + 1;
                    newX = x + 1;
                    break;
                case "down-left":
                    newY = y + 1;
                    newX = x - 1;
                    break;
            }
        }
        if (checkDirection( cardMatrix, newX, newY, objectiveCard.getDirection2(), objectiveCard.getCard3()) && first) {
            return (objectiveCard.getPoints());
        }


        return 0;
    }


    //this function is called every for every direction findPattern() needs to verify.
    // Starting from the top card, every layout is either going down in a straight line, or on the diagonals
    boolean checkDirection(Card[][] cardMatrix, int x, int y, String direction, String toCheck) {
        Boards board = this.player.getGameBoards();


        // Determine the new row and col values based on the direction
        int newX = x;
        int newY = y;


        switch (direction) {
            case "down" :
                newY = y + 1;
                break;
            case "down-right":
                newX = x + 1;
                newY = y + 1;
                break;
            case "down-left":
                newY = y + 1;
                newX = x - 1;
                break;

        }

        // Check if the new position is within the bounds of the board (use values 5 for MAX_X and MAX_Y if testing)
        if (newX >= board.getMAX_X() || newX < 0 || newY >= board.getMAX_X() || newY < 0) {
            throw new IllegalArgumentException("Invalid new position");
        }

        // Check if the symbol matches the one to check
        return Objects.equals(cardMatrix[newX][newY].getBackSymbol().getFirst().getSymbolColor(), toCheck);
    }





}
