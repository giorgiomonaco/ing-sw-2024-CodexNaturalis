package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EndgameManager {
    /*
    this class gets the game and the player, and calculates the points each player gets by completing his objective
    every card has a different type of objective, hence the need for this class
     */
    Game game;
    Player player;


    //constructor
    public EndgameManager(Game game, Player player) {
        this.game = game;
        this.player = player;
    }


    /**
     * Calculates the total objective points for the current player.
     * This method evaluates both the player's personal objective card and the common objective cards
     * to calculate the total points based on the type of objectives.<br><br>
     * The types of objectives can be:
     * <ul>
     *   <li>"position" - Points based on card positions.</li>
     *   <li>"mushroom", "fox", "leaf", "butterfly", "feather", "bottle", "scroll", "special" - Points for each resource type.</li>
     * </ul>
     *
     * @return the total objective points for the current player.
     */
    public int objectivePointsCounter() {

        if(player.getPlayerObjectiveCard() == null || game.getCommonObjectives() == null) {
            System.err.println("Objectives not found!");
            return 0;
        }

        int result = 0;
        List<ObjectiveCard> obj = new ArrayList<>();

        obj.add(player.getPlayerObjectiveCard());
        obj.add(game.getCommonObjectives().get(0));
        obj.add(game.getCommonObjectives().get(1));

        //getting the type of the objective (1. cards position -- 2. points for each resource)
        for (ObjectiveCard o: obj) {
            result += checkCondition(o);
        }

        return result;
    }

    private int checkCondition(ObjectiveCard o) {
        System.out.println("Objective type and number: " + o.getType() + " " + o.getCardName());

        return switch (o.getType()) {
            case "position" -> objectiveCreator(o);
            case "mushroom", "fox", "leaf", "butterfly", "feather", "bottle", "scroll", "special" -> resourceCounter(o);
            default -> 0;
        };
    }


    // this function analyzes the objective description to find how many occurrences of a same layout happen
    private int objectiveCreator(ObjectiveCard objectiveCard){
        // stage one -- finding the first card basing on its color
        Boards gameBoard = this.player.getGameBoards();

        Card[][] cardMatrix = player.getGameBoards().getGameBoard();

        int result = 0;


        for (int y = 0; y < gameBoard.getMAX_Y(); y++){
            for (int x = 0; x < gameBoard.getMAX_X(); x++){
                if(cardMatrix[x][y] != null && !(cardMatrix[x][y] instanceof InitialCard)) {
                    if (Objects.equals(cardMatrix[x][y].getBackSymbol().getFirst().getSymbolColor(), objectiveCard.getCard1())) {
                        result += findPattern(x, y, cardMatrix, objectiveCard);
                    }
                }
            }
        }


        return result;
    }


    /**
     * This method calculates the points based on the type of resource specified and the player's
     * available resources. <br>It multiplies the points of the player's objective card by  3.
     * <br><br> The "special" resource type is calculated differently than the other resource types.
     *
     * @param objectiveCard The objective card to check.
     * @return The calculated points based on the specified resource type.
     */
    private int resourceCounter(ObjectiveCard objectiveCard){
        int[] resources = player.getResourcesAvailable();

        return switch (objectiveCard.getType()) {
            case "mushroom" -> (objectiveCard.getPoints() * Math.floorDiv(resources[0], 3));
            case "leaf" -> (objectiveCard.getPoints() * Math.floorDiv(resources[1], 3));
            case "fox" -> (objectiveCard.getPoints() * Math.floorDiv(resources[2], 3));
            case "butterfly" -> (objectiveCard.getPoints() * Math.floorDiv(resources[3], 3));
            case "feather" -> (objectiveCard.getPoints() * Math.floorDiv(resources[4], 2));
            case "bottle" -> (objectiveCard.getPoints() * Math.floorDiv(resources[5], 2));
            case "scroll" -> (objectiveCard.getPoints() * Math.floorDiv(resources[6], 2));
            case "special" -> (objectiveCard.getPoints() * specialCounter());
            default -> 0;
        };

    }

    /**
     * Calculates points based on special conditions for the current player.
     *
     * @return The calculated points based on special conditions.
     */
    private int specialCounter(){
        int[] resources = player.getResourcesAvailable();
        int a = resources[4];
        int b = resources[5];
        int c = resources[6];
        return (findMin(a,b,c));
    }

    /**
     * finds the minimum between 3 numbers: a, b, c<br>
     * @param a first number
     * @param b second number
     * @param c third number
     * @return the minimum
     */
    private int findMin(int a, int b, int c){
        return Math.min(Math.min(a,b),c);
    }

    /**
     * Verifies if the objective pattern is present in the specified position and assigns points to the player accordingly.<br><br>
     * This method checks if the specified objective pattern, defined by the player's objective card,
     * exists starting from the given coordinates (x, y) in the card matrix. It evaluates two directions
     * to determine if both patterns are present and assigns points based on the objective card's configuration.
     *
     * @param x The starting x-coordinate in the card matrix.
     * @param y The starting y-coordinate in the card matrix.
     * @param cardMatrix The matrix of cards where the pattern is checked.
     * @return The points assigned to the player if the objective pattern is found; otherwise, returns 0.
     */
    int findPattern(int x, int y, Card[][] cardMatrix, ObjectiveCard objectiveCard){

        if(cardMatrix[x][y].getCheckedBy().contains(objectiveCard.getCardName())) return 0;

        boolean first = checkDirection( cardMatrix, x, y, objectiveCard.getDirection1(), objectiveCard.getCard2());

        int newX = x;
        int newY = y;

        if (first){
            switch (objectiveCard.getDirection1()) {
                case "down":
                    newY = y + 2;
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
        } else {
            return 0;
        }

        if(cardMatrix[newX][newY].getCheckedBy().contains(objectiveCard.getCardName())) return 0;

        if (checkDirection( cardMatrix, newX, newY, objectiveCard.getDirection2(), objectiveCard.getCard3())) {
            updateCheckedByValue(cardMatrix, newX, newY, objectiveCard);
            return (objectiveCard.getPoints());
        } else {
            return 0;
        }

    }



    /**
     * Checks the specified direction in the card matrix starting from the given position (x, y).<br>
     * This method evaluates a direction specified by the `direction` parameter starting from the position
     * (x, y) in the `cardMatrix`. It checks if the symbol at the new position matches the `toCheck` symbol
     * and ensures the new position is within the bounds of the game board.
     *
     * @param cardMatrix The matrix of cards where the direction is checked.
     * @param x The starting x-coordinate in the card matrix.
     * @param y The starting y-coordinate in the card matrix.
     * @param direction The direction to check ("down", "down-right", or "down-left").
     * @param toCheck The symbol color to check against at the new position.
     * @return true if the symbol at the new position matches `toCheck` and the position is valid; false otherwise.
     * @throws IllegalArgumentException if the new position is outside the bounds of the game board.
     */
    boolean checkDirection(Card[][] cardMatrix, int x, int y, String direction, String toCheck) {

        Boards board = player.getGameBoards();


        // Determine the new row and col values based on the direction
        int newX = x;
        int newY = y;


        switch (direction) {
            case "down" :
                newY = y + 2;
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
        if (newX >= board.getMAX_X() || newX < 0 || newY >= board.getMAX_Y() || newY < 0 || cardMatrix[newX][newY] == null) {
            return false;
        }

        // Check if the symbol matches the one to check
        return Objects.equals(cardMatrix[newX][newY].getBackSymbol().getFirst().getSymbolColor(), toCheck) ;
    }

    private void updateCheckedByValue(Card[][] cardMatrix, int x, int y, ObjectiveCard objectiveCard) {

        cardMatrix[x][y].addCheckedBy(objectiveCard.getCardName());

        int newX = x;
        int newY = y;

        switch (objectiveCard.getDirection2()) {
            case "down":
                newY = y + 2;
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

        cardMatrix[newX][newY].addCheckedBy(objectiveCard.getCardName());

    }






}
