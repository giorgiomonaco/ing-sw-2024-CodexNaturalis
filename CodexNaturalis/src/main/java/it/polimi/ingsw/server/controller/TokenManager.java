package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.Token;
import it.polimi.ingsw.client.view.ViewTry;

import java.util.ArrayList;
import java.util.List;

public class TokenManager {
    /**
     * This class manages the assignment of the tokens to the players at start of the game
     */
    private final Game game;

    private int tokenCounter = 0;

    //List of all the tokens available for the game
    private final List<Token> tokens = new ArrayList<>();
    private Player p;

    //Constructor of the class
    public TokenManager(Game game) {
        this.game = game;
    }

    /**
     * Initializes tokens for all players by creating and assigning tokens.
     * <p>
     * This method performs the following actions:
     * <ol>
     *     <li>Creates all tokens available in the game.</li>
     *     <li>Populates the list of available tokens with default colors.</li>
     * </ol>
     */
    public void initializeTokens() {
        //As first thing we create all the tokens and populate the list of tokens
        createTokens();

        List<String> availableTokens = new ArrayList<>();

        availableTokens.add("red");
        availableTokens.add("blue");
        availableTokens.add("green");
        availableTokens.add("yellow");

        game.setAvailableTokens(availableTokens);
    }

    /**
     * Creates and initializes all tokens for the game.
     * <p>
     * This method creates the following tokens:
     * <ul>
     *     <li>1 black token</li>
     *     <li>2 red tokens</li>
     *     <li>2 blue tokens</li>
     *     <li>2 green tokens</li>
     *     <li>2 yellow tokens</li>
     * </ul>
     * The created tokens are added to the {@code tokens} list.
     */
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

    /**
     * Assigns a token to a player at the start of the game automatically.
     * <p>
     * This method assigns the next available token from the {@code tokens} list to the given player.
     * It increments the {@code tokenCounter} to track the next available token.
     *
     * @param p The player to whom the token will be assigned.
     */
    public void assignToken(Player p) {
        this.p = p;
        //Assign the color of the token for each player
            p.setPlayerToken(tokens.get(tokenCounter));
            //And we communicate the assignment
            // view.ackPersonalToken(p.getPlayerName(), tokens.get(tokenCounter).getTokenColor());
            tokenCounter++;
        }

    public List<Token> getTokens() {
        return tokens;
    }
}
