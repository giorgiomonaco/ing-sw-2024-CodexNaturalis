package it.polimi.ingsw.model;


import java.util.Scanner;

public class PlayerManager {
    private final Game game;

    //Constructor
    public PlayerManager(Game game){
        this.game = game;
    }

    public void playersInitialization(){
        Scanner scanner = new Scanner(System.in);
        //Ask how many players
        System.out.println("How many Players?");
        //Read response
        int input = scanner.nextInt();
        for(int i = 0; i < input; i++){
            addPlayerToGame(i);
        }
    }
    public void addPlayerToGame(int playerNumber){
        //Creation of scanner for the insertion of the name of the player
        Scanner scanner = new Scanner(System.in);
        //Ask the name of the player
        System.out.println("Name of player number " + (playerNumber + 1));
        //scan answer
        String input = scanner.nextLine();
        //Now we create a new player
        Player p = new Player(game, input);
        game.addPlayer(p);
    }

}
