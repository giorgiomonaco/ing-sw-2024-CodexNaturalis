package it.polimi.ingsw.controller;

import it.polimi.ingsw.server.controller.TokenManager;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.Token;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TokenManagerTest {

    private Game game;
    private TokenManager tokenManager;

    @Before
    public void setUp() {
        // Create a real instance of the Game class
        game = new Game(2);

        // Initialize the TokenManager with the Game instance
        tokenManager = new TokenManager(game);
    }

    @Test
    public void testInitializeTokens() {
        // Call the method to initialize tokens
        tokenManager.initializeTokens();

        // Verify that the available tokens are set correctly in the game
        List<String> expectedTokens = List.of("red", "blue", "green", "yellow");
        assertEquals(expectedTokens, game.getAvailableTokens());
    }

    @Test
    public void testCreateTokens() {
        // Call the method to create tokens
        tokenManager.createTokens();

        // Using reflection to access the private field 'tokens'
        List<Token> tokens = getTokensFromTokenManager(tokenManager);

        // Verify that all tokens are created and added to the list
        assertEquals(9, tokens.size());
        assertTrue(tokens.stream().anyMatch(token -> "black".equals(token.getTokenColor())));
        assertTrue(tokens.stream().anyMatch(token -> "red1".equals(token.getTokenColor())));
        assertTrue(tokens.stream().anyMatch(token -> "blue1".equals(token.getTokenColor())));
        assertTrue(tokens.stream().anyMatch(token -> "green1".equals(token.getTokenColor())));
        assertTrue(tokens.stream().anyMatch(token -> "yellow1".equals(token.getTokenColor())));
     }

    @Test
    public void testAssignToken() {
        // Create a real instance of the Player class
        Player player = new Player("aldo");

        // Initialize tokens first
        tokenManager.createTokens();

        // Assign a token to the player
        tokenManager.assignToken(player);

        // Verify that the player's token is set correctly
        Token assignedToken = player.getPlayerToken();
        assertNotNull(assignedToken);
        assertEquals("black", assignedToken.getTokenColor());
    }

    // Helper method to access the private 'tokens' field using reflection

    private List<Token> getTokensFromTokenManager(TokenManager tokenManager) {
        try {
            var field = TokenManager.class.getDeclaredField("tokens");
            field.setAccessible(true);
            return (List<Token>) field.get(tokenManager);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
