package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenManagerTest {

        Game game = new Game(2);
        TokenManager tokenManager = new TokenManager(game);


    @Test
    void initializeTokens() {
        tokenManager.initializeTokens();

        List<String> availableTokens = game.getAvailableTokens();
        assertNotNull(availableTokens);
        assertEquals(4, availableTokens.size()); // Assuming we expect 2 available tokens

        assertTrue(availableTokens.contains("red"));
        assertTrue(availableTokens.contains("blue"));
     //   assertTrue(availableTokens.contains("green"));
     //   assertTrue(availableTokens.contains("yellow"));

    }

    @Test
    void createTokens() {
        tokenManager.createTokens();

        List<Token> tokens = tokenManager.getTokens();
        assertNotNull(tokens);
        assertEquals(9, tokens.size());

        Token firstToken = tokens.get(0);
        assertEquals("black", firstToken.getTokenColor());

        Token redToken1 = tokens.get(1);
        assertEquals("red1", redToken1.getTokenColor());

    }

    @Test
    void assignToken() {
        Player player = new Player("TestPlayer");
        tokenManager.createTokens();

        // Assign tokens to the player
        for (int i = 0; i < 9; i++) {
            tokenManager.assignToken(player);
            assertEquals(tokenManager.getTokens().get(i), player.getPlayerToken());
        }

    }

    @Test
    void getTokens() {
        tokenManager.createTokens();
        assertEquals(9,tokenManager.getTokens().size());
    }
}