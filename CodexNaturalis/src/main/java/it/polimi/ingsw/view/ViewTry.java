package it.polimi.ingsw.view;

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
        System.out.println("invalid selction, please retry");
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


}
