package it.polimi.ingsw.model;


import it.polimi.ingsw.model.*;

import java.util.Scanner;

public class PlayCardManager {
    //this class is used to manage the play of a card
    //used to shrink the code from the logic
    //it gets a card to be played and a player that played it,
    //proceeds to place the card if possible and asks the player
    //if the card is being played front or back side

    //we need the game we are referring to
    private final Game game;

    //we prepare the variables for the placement of the card
    //not elegant but works, may review this later
    private int placeX;
    private int placeY;

    //constructor
    public PlayCardManager(Game game){
        this.game = game;
    }

    //play a card
    public void playCard(Card card){
        //We start by taking in consideration the game board of the player playing
        GameBoard playerBoard = game.getCurrentPlayer().getGameBoard();

        if(card instanceof ResourceCard){
            //Ask the player where he wants to put the card
            getAvailableBoxes(playerBoard);

            //Ask if Player wants to play front or back of the card
            System.out.println("Do you want to play the front or the back of the card?");

            //Create the scanner for response
            Scanner scan = new Scanner(System.in);
            String playerChoice = scan.nextLine();
            String choice = scan.nextLine();
            //set the side
            card.setFrontSide(choice == "front");
            //then we place the card
            //calling the board method that does so

            GameBoard one = new GameBoard();
            getAvailableBoxes(one);
            playerBoard.insertCard(card, placeX, placeY);

        } else if (card instanceof GoldCard){
            //here things get more difficult
        }
    }

    //method that gets the boxes where Player can put the card
    public void getAvailableBoxes(GameBoard playerBoard){
        //cycles the game board of the player to find where the cards are currently played
        //for each card it finds, checks if there are any visible corner
        //if so => card playable there
        for(int i = 0; i < playerBoard.getMAX_X(); i++){
            for (int j = 0; j < playerBoard.getMAX_Y(); j++){
                //if in that place there is a card
                if(playerBoard.getCardMatrix()[i][j] != null){

                    //we want to know if that card has been played front or back side
                    //to know if check availability of which side of the card
                    if(playerBoard.getCardMatrix()[i][j].isFrontSide()) {
                        //check the angles of the card
                        for (int a = 0; a < 4; a++) {
                            //We want to check if the angle is visible:
                            //first check if instance of visibleAngle
                            //then check the attribute notCovered

                            //Check if is a visible angle
                            if (playerBoard.getCardMatrix()[i][j].getFrontAngles()[a] instanceof VisibleAngle) {
                                //if so, we check if it is covered or not by another card
                                if (((VisibleAngle) playerBoard.getCardMatrix()[i][j].getFrontAngles()[a]).isNotCovered()) {

                                    //we create the temporary x and y representing the free box
                                    //given by the corner and then adding or subtracting from the card box the right amount
                                    //remember checking if out of the matrix!!

                                    System.out.println("Utilizable box! is box " + i + ";" + j);
                                    System.out.println("place here the card?");
                                    Scanner scan = new Scanner(System.in);
                                    String choice = scan.nextLine();
                                    if (choice == "yes") {
                                        //we set the place variables to place the card later
                                        placeX = i;
                                        placeY = j;
                                        //we exit the cycle
                                        break;
                                    }
                                }
                            }

                        }
                    } else {
                        //Same ut with back angles
                        for (int a = 0; a < 4; a++) {
                            //We want to check if the angle is visible:
                            //first check if instance of visibleAngle
                            //then check the attribute notCovered

                            //Check if is a visible angle
                            if (playerBoard.getCardMatrix()[i][j].getBackAngles()[a] instanceof VisibleAngle) {
                                //if so, we check if it is covered or not by another card
                                if (((VisibleAngle) playerBoard.getCardMatrix()[i][j].getBackAngles()[a]).isNotCovered()) {
                                    //if it is not covered we can take this in consideration as indicator
                                    //of an empty box that may be used to play our card
                                    System.out.println("Utilizable box! is box " + i + ";" + j);
                                    System.out.println("place here the card?");
                                    Scanner scan = new Scanner(System.in);
                                    String choice = scan.nextLine();
                                    if (choice == "yes") {
                                        //we set the place variables to place the card later
                                        placeX = i;
                                        placeY = j;
                                        //we exit the cycle
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        //If we reach this point there are 2 possible situations:
        //no spaces available
        //None of the spaces have been selected
        //Considering there will be a sort of graphic
        //we assume no spaces available
        //we have to set a flag etc... we don't consider the case cus
        //it's not likely to happen
    }


}
