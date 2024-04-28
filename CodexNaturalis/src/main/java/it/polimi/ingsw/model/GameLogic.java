package it.polimi.ingsw.model;


import it.polimi.ingsw.controller.AvailableResourcesManager;
import it.polimi.ingsw.controller.DrawManager;
import it.polimi.ingsw.controller.PlayCardManager;

import java.util.Scanner;

public class GameLogic {
    //this class represents the game states while
    //the game is being played after setup phase
    //It cycle on the players to make them play their turn, and
    //it manages every aspect of the game
    //(manages calling the various managers of different phases)
    //-------------------!!!WILL BE A HUGE CLASS IMO!!!!!-------------------

    //First we need the game it is referring to
    private final Game game;

    //then we need the game states
    //play: turns keep going until ending conditions reached
    //ending: last turns for the players after reaching end conditions
    //end: end of everything, declare winner
    private static final int STATE_PLAY = 1;
    private static final int STATE_ENDING = 2;
    private static final int STATE_END = 3;
    //And we need the variable for the game state
    //default value at 0, so none of the states before we actually start playing
    private int gameState;


    //constructor
    public GameLogic(Game game){
        this.game = game;
    }


    //Start the game and let players play
    public void playGame(){
        //---------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!---------------------
        //I don't really like this
        //we have to create (another) draw manager to manage the drawing without rewriting all
        //don't like cus there is already another one
        //very, very ugly to create it in the main tho so better like this
        //I think the previous will be garbage collected after exiting setup
        DrawManager drawManager = new DrawManager(game);

        //We also need an object that manages the play of the cards
        //to make this class not 10k lines and so complex
        PlayCardManager playCardManager = new PlayCardManager(game);

        //we also need a manager for the resource available for each player to
        //play the cards and give points based on visible symbols
        AvailableResourcesManager availableResourcesManager = new AvailableResourcesManager(game);
        //we can decide if create a specific method to initialize the resource of the first card
        //instead of doing a cycle on the whole grid (very, very not efficient)



        //set the game in play state to start playing
        gameState = STATE_PLAY;

        //we cycle upon the list of players
        //we make them pass through all the game phases one after the other
        do{
            for(Player p : game.getPlayerList()){
                //first we set the current player the one that is actually playing
                game.setCurrentPlayer(p);
                //play phase is the first phase
                //we use console messages for simplicity
                System.out.println("turn of " + p.getPlayerName());
                System.out.println("Which card would you like to play?");
                System.out.println("card 1, card 2, card 3 ?");
                //we create a scanner to get the answer of the player
                //will be useless with graphics
                Scanner scan = new Scanner(System.in);
                String choice = scan.nextLine();
                if (!(choice.equals("card 1")) || !(choice.equals("card 2")) || !(choice.equals("card 3")) ) {
                    while (true ){
                        System.out.println("input errato, correggi");
                        scan = new Scanner(System.in);
                        choice = scan.nextLine();
                        if(choice.equals("card 1") || choice.equals("card 2") || choice.equals("card 3")) {
                            break;
                        }
                    }
                //we prepare a variable to contain the card selected by player to be played
                Card chosenCard;


                    //create a switch case to use the choice made
                    //--------------don't remember how to use default by now!!!--------------
                    switch (choice) {
                        case "card 1":
                            //we get that specific card
                            chosenCard = game.getCurrentPlayer().getPlayerResourceCards().get(0);
                            //we ask to play that card
                            playCardManager.playCard(chosenCard);
                        case "card 2":
                            //we get that specific card
                            //System.out.println("funziona 1");
                            chosenCard = game.getCurrentPlayer().getPlayerResourceCards().get(1);
                            //System.out.println("funziona 2");
                            //we ask to play that card
                            playCardManager.playCard(chosenCard);
                            //System.out.println("funziona 3");
                        case "card 3":
                            //we get that specific card
                            chosenCard = game.getCurrentPlayer().getPlayerGoldCards().get(0);
                            //we ask to play that card
                            playCardManager.playCard(chosenCard);
                    }

                    //draw phase of the players:
                    //by calling the draw manager
                }

            }
        }
        while(gameState == STATE_PLAY);

    }

    //getter of the game state
    public int getGameState() {
        return gameState;
    }
}
