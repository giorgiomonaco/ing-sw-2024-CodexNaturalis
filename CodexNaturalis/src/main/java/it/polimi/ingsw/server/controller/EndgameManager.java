package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.*;

import java.util.List;
import java.util.Objects;

public class EndgameManager {
    //this class gets the game and the player, and calculates the points each player gets by completing his objective
    //every card has a different type of objective, hence the need for this class
    Game game;
    Player player;
    private final List<Symbol> symbols;


    //constructor
    public EndgameManager(Game game, Player player, List<Symbol> sym){
        this.player = player;
        this.game = game;
        this.symbols = sym;
    }

    public void objectivePointsCounter(){
        ObjectiveCard obj = this.player.getPlayerObjectiveCard();

        //getting the type of the objective (1. cards position -- 2. points for each resource)
        try {
            switch (obj.getType()){
                case "position":
                    objectiveCreator();
                    break;
                case "mushroom", "fox", "leaf", "butterfly", "feather", "bottle", "scroll", "special":
                    resourceCounter(obj.getType());
                    break;

            }
        }
        catch(NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }


    }

    // this function analyzes the objective description to find how many occurrences of a same layout happen
    private void objectiveCreator(){
        // stage one -- finding the first card basing on its color
        ObjectiveCard objectiveCard = this.player.getPlayerObjectiveCard();
        Boards gameBoard = this.player.getGameboard();

        Card[][] cardMatrix = gameBoard.getGameboard();


        for (int row = 0; row< gameBoard.getMAX_Y(); row++){
            for (int col = 0; col< gameBoard.getMAX_X(); col++){
                if (Objects.equals(cardMatrix[row][col].getBackSymbol().getFirst().getSymbolName(), objectiveCard.getCard1())){
                    findPattern(row, col, cardMatrix);
                }
            }
        }


        return;
    }
    private void resourceCounter(String type){
        int[] resources = player.getResourcesAvailable();
        ObjectiveCard objectiveCard = this.player.getPlayerObjectiveCard();

        switch (type) {
            case "mushroom":
                player.addPoints(objectiveCard.getPoints() * Math.floorDiv(resources[0],3));
                break;
            case "leaf":
                player.addPoints(objectiveCard.getPoints() * Math.floorDiv(resources[1],3));
                break;
            case "fox":
                player.addPoints(objectiveCard.getPoints() * Math.floorDiv(resources[2],3));
                break;
            case "butterfly":
                player.addPoints(objectiveCard.getPoints() * Math.floorDiv(resources[3],3));
                break;
            case "feather":
                player.addPoints(objectiveCard.getPoints() * Math.floorDiv(resources[4],3));
                break;
            case "bottle":
                player.addPoints(objectiveCard.getPoints() * Math.floorDiv(resources[5],3));
                break;
            case "scroll":
                player.addPoints(objectiveCard.getPoints() * Math.floorDiv(resources[6],3));
                break;
            case "special":
                specialCounter();

        }
    }
    private void specialCounter(){
        ObjectiveCard objectiveCard = this.player.getPlayerObjectiveCard();
        int[] resources = this.player.getResourcesAvailable();
        int a = Math.floorDiv(resources[4],3);
        int b = Math.floorDiv(resources[5],3);
        int c = Math.floorDiv(resources[6],3);
        this.player.addPoints(findMin(a,b,c));
    }
    private int findMin(int a, int b, int c){
        return Math.min(Math.min(a,b),c);
    }

    //this function verifies if the objective pattern is present and assigns points to the player accordingly
    private void findPattern(int row, int col, Card[][] cardMatrix){
        ObjectiveCard objectiveCard = this.player.getPlayerObjectiveCard();
        if (checkDirection(objectiveCard, cardMatrix, row, col, objectiveCard.getDirection1(), objectiveCard.getCard2())){
            switch (objectiveCard.getDirection1()) {
                case "down":
                    row--;
                case "down-right":
                    row--;
                    col++;
                case "down-left":
                    row--;
                    col--;
            }
            if (checkDirection(objectiveCard, cardMatrix, row, col, objectiveCard.getDirection2(), objectiveCard.getCard3())){
                player.addPoints(objectiveCard.getPoints());
            }
        }

    }


    //this function is called every for every direction findPattern() needs to verify.
    // Starting from the top card, every layout is either going down in a straight line, or on the diagonals
    private boolean checkDirection(ObjectiveCard card, Card[][] cardMatrix, int row, int col, String direction, String toCheck){

        Boards board = this.player.getGameboard();
        if (row == board.getMAX_Y() || col == 0 || col == board.getMAX_X()){
            return false;
        }
        return switch (direction) {
            case "down" -> Objects.equals(cardMatrix[row--][col].getBackSymbol().getFirst().getSymbolName(), toCheck);
            case "down-right" -> Objects.equals(cardMatrix[row--][col++].getBackSymbol().getFirst().getSymbolName(), toCheck);
            case "down-left" -> Objects.equals(cardMatrix[row--][col--].getBackSymbol().getFirst().getSymbolName(), toCheck);
            default -> false;
        };
    }



}
