package it.polimi.ingsw.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ViewTry {
    //This class has all methods that are called at the right time depending on the game state
    //Let's try starting from asking and interacting to know the name of the player, in this case we want to

    //We need a scanner for all the things (maybe better to have it in all the method to avoid having a not working
    //thing while we are not calling any method)

//-------------------------------- Player related --------------------------------
    //Ask the number of players cus we are playing from only one terminal by now
    public int getNumOfPlayers(){
        Scanner scan = new Scanner(System.in);
        System.out.println("How many players want to play ?");
        return scan.nextInt();
    }
    //Ask again if the number of player is incorrect
    public int askAgainNumOfPlayer(){
        System.out.println("Invalid number of players, min = 1; max = 4");
        return getNumOfPlayers();
    }
    //Ask the name of the current player willing to play
    public String getPlayerName(int i){
        Scanner scan = new Scanner(System.in);
        System.out.println("Name of player num " + i);
        return scan.nextLine();
    }


//-------------------------------- Token related --------------------------------
    //We start the announcement of the token assignment
    public void startInformingOfTokens(){
        System.out.println("Token assignment: ");
    }
    //Communication of the specific token assigned
    public void ackPersonalToken(String player, String token){
        System.out.println(player + ": " + token);
    }

    //Token choice
    public String askForTokenSelection(List<String> availableTokens ) {
        Scanner scan = new Scanner(System.in);
        System.out.println("which token color  do you want? These are the available ones:");
        for (String s : availableTokens) {
            System.out.println(s);
        }
        Boolean choiceMade = false;
        String choice;
        do {
            choice = scan.nextLine();
            for (String s : availableTokens) {
                if (choice.equals(s)) {
                    choiceMade = true;
                   break;
                }
            }
            //ask to insert a acceptable answer
            askAgainColor();
        } while (!choiceMade);

        return choice;
    }

    //ask again for color selection
    public void askAgainColor(){
        System.out.println("invalid selection, please retry");
    }

//-------------------------------- Choice on initial card  --------------------------------
    public String playerObjectiveCardChoice(String player, List<String> startingCards){
        System.out.println("This is your starting cards: ");
        for(String s : startingCards){
            System.out.println(s);
        }
        System.out.println("Which one do you want to keep: ");
        System.out.println("First or second? ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    //We have to ask again about the choice of the objective card if the answer is not acceptable
    public String askAgainObjectiveCards(){
        System.out.println("invalid answer, retry: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
//-------------------------------- Related to play a card  --------------------------------
    public String[] getPlayedCard(List<String> resCardsInHand, List<String> goldCardsInHand) {

        if (resCardsInHand.isEmpty()) {
            System.out.println("No resource cards in hand");
        } else {
            System.out.println("List of resourceCards in hand:");
            for(String c : resCardsInHand){
                System.out.println(c);
            }
        }

        if (goldCardsInHand.isEmpty()) {
            System.out.println("No gold cards in hand");
        } else {
            System.out.println("List of goldCards in hand:");
            for(String c : goldCardsInHand){
                System.out.println(c);
            }
        }

        //Asking about the choice
        System.out.println("Play a resource or a Gold card?" );
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if((choice.equals("Resource") || choice.equals("resource")) && !(resCardsInHand.isEmpty())){
            System.out.println("Which one you want to play?");
            int i = 1;
            for (String s : resCardsInHand){
                System.out.println("[" + i + "] " + s);
                i++;
            }
            /*
            Let's create an array of 2 strings:
            First one for the card type,
            Second one for the selection
             */
            String[] finalChoice = new String[2];
            finalChoice[0] = "R";
            finalChoice[1] = scan.nextLine();
            return finalChoice;

        } else if ((choice.equals("Gold") || choice.equals("gold")) && !(goldCardsInHand.isEmpty())){
            System.out.println("Which one you want to play?");
            int i = 1;
            for (String s : resCardsInHand){
                System.out.println("[" + i + "] " + s);
                i++;
            }
            /*
            Let's create an array of 2 strings:
            First one for the card type,
            Second one for the selection
             */
            String[] finalChoice = new String[2];
            finalChoice[0] = "G";
            finalChoice[1] = scan.nextLine();
            return finalChoice;
        }
        return null;
    }

    public String askForSideSelection(){
        Scanner scan = new Scanner(System.in);
        String selection;
        boolean correctChoiceMade = false;
        System.out.println("Which side do you want to play the card?");
        do {
            System.out.println("[1] : Front");
            System.out.println("[2] : Back");
            selection = scan.nextLine();
            if (!selection.equals("1") && !selection.equals("2")) {
                System.out.println("wrong selection, try again: ");
            } else
                correctChoiceMade = true;
        } while (!correctChoiceMade);
        return selection;
    }

    /*
    Method to ask and get the box in which the player wants to play the card
     */
    public int getPlayerBoxChoice(){
        Scanner scan = new Scanner(System.in);
        System.out.println("in which box do you want to play your card?");
        System.out.println("Insert the number of the box");
        return scan.nextInt();
    }


//-------------------------------- Related to displace the board  --------------------------------
    public void printTopOfGrid(int[] x, int[] y){
        for(int i = x[0]-1; i <= x[1]+1; i++) {
            //for every column, print space fot the box
            System.out.print("+---");
        }
        //Close the grid
        System.out.println("+");
    }
    public void printNonPlayableBox(){
        System.out.print("|   ");
    }
    public void printPlayableBox(int counter){
        System.out.print("| " + counter + " ");
    }

    public void printEndOfRaw(){
        System.out.println("|");
    }
    public void printUsedBox(){
        System.out.print("| c ");
    }

    //-------------------------------- Related to inform about points --------------------------------
    public void informAboutPoints(int points){
        System.out.println("You reached " + points +"!");
    }

    //-------------------------------- Related to choice of objective card  --------------------------------

    public String askforObjectiveSelection(String c1, String c2, boolean firstTry){
        if(!firstTry){
            System.out.println("incorrect selection, please retry");
        }
        System.out.println("These are the Objective between you can choose:");
        System.out.println("[1] : " + c1);
        System.out.println("[2] : " + c2);
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        if(choice.equals("1")){
            return "F";
        } else if(choice.equals("2")){
            return "S";
        } else {
            return "error";
        }
    }

    //-------------------------------- Related to initial card  --------------------------------

    public void displaceInitialCard (String cardName){
        System.out.println("This is your initial card:" + cardName);
    }
    
    //-------------------------------- Related to draw selection  --------------------------------
    public String drawSelection(boolean RDeckEmpty, boolean GDecEmpty, List<String> resCards, List<String> goldCards){
        Scanner scan = new Scanner(System.in);
        int choiceCounter = 0;
        int value = 0;
        List<String> choices = new ArrayList<>();
        System.out.println("Draw phase!");
        System.out.println("Where you want to draw from?");


        if(!resCards.isEmpty()){
            System.out.println("Visible resource cards:");
            System.out.println("["+ choiceCounter +"] : " + resCards.get(0));
            choices.add("R1");
            choiceCounter++;
            if(resCards.size() == 2){
                System.out.println("["+ choiceCounter +"] : " + resCards.get(1));
                choices.add("R2");
                choiceCounter++;
            }
        }
        if(!goldCards.isEmpty()){
            System.out.println("Visible gold cards:");
            System.out.println("["+ choiceCounter +"] : " + goldCards.get(0));
            choices.add("G1");
            choiceCounter++;
            if(goldCards.size() == 2){
                System.out.println("["+ choiceCounter +"] : " + goldCards.get(1));
                choices.add("G2");
                choiceCounter++;
            }
        }
        if (!RDeckEmpty) {
            System.out.println("[" + choiceCounter + "] : Resource deck");
            choices.add("RD");
            choiceCounter++;
        }
        if (!GDecEmpty) {
            System.out.println("[" + choiceCounter + "] : Gold deck");
            choices.add("GD");
            choiceCounter++;
        }
        try {
             value = scan.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Wrong value");
        }
        return choices.get(value);
    }

    
}
