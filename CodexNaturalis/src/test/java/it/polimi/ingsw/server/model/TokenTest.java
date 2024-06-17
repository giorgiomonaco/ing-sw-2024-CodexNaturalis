package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {


    @Test
    void getTokenColor() {
        Token token = new Token("green");

        // Verify the color returned by getTokenColor
        assertEquals("green", token.getTokenColor());
    }
}