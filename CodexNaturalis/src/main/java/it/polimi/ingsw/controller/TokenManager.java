package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenManager {
    //This class manages the assignment of the tokens to the players at start of the game
    private final Game game;

    //counter used to determine the token we will assign until everyone used
    private int tokenCounter = 0;

    //List of all the tokens available for the game
    private final List<Token> tokens = new ArrayList<>();

    //Constructor of the class
    public TokenManager(Game game) {
        this.game = game;
    }

    //Initialize tokens, aka assign all tokens to all players
    public void initializeTokens() {
        //As first thing we create all the tokens and populate the list of tokens
        createTokens();
        //for each player in the players game list
        for (Player p : game.getPlayerList()) {
            //we call assign token to give each a different token until available
            assignToken(p);
        }
    }

    public void createTokens() {
        Token blackToken = new Token("black");
        Token redToken1 = new Token("red1");
        Token blueToken1 = new Token("blue1");
        Token greenToken1 = new Token("green1");
        Token yellowToken1 = new Token("yellow1");
        Token redToken2 = new Token("red2");
        Token blueToken2 = new Token("blue2");
        Token greenToken2 = new Token("green2");
        Token yellowToken2 = new Token("yellow2");

        //We populate the list of all tokens
        tokens.add(blackToken);
        tokens.add(redToken1);
        tokens.add(blueToken1);
        tokens.add(greenToken1);
        tokens.add(yellowToken1);
        tokens.add(redToken2);
        tokens.add(blueToken2);
        tokens.add(greenToken2);
        tokens.add(yellowToken2);
    }

    //Method that assign a token to the player at start of game
    //In an automatic way
    public void assignToken(Player p) {
        //check if we have tokens left to assign
        if (tokenCounter >= tokens.size()) {
            System.out.println("ERROR, all tokens already assigned");
        } else {
            //We take the color we have to assign next and assign it to the player
            p.setPlayerToken(tokens.get(tokenCounter));
            //then we increment the counter to have the next token for next iteration
            tokenCounter++;
        }
    }

}
