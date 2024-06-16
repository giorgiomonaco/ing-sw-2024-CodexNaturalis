package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.server.model.Symbol;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ResourceCardInitializerTest {
/*
    private Game game;
    private List<Symbol> symbols;
    private ResourceCardInitializer resourceCardInitializer;

    @Before
    public void setUp() {
        // Create a real instance of the Game class
        game = new Game(2);

        // Initialize symbols list
        symbols = new ArrayList<>();
        symbols.add(new Symbol("mushroom","resource"));

        // Initialize the ResourceCardInitializer with the Game and symbols
        resourceCardInitializer = new ResourceCardInitializer(game, symbols);
    }
    @Test
    void initializeResourceCards() {// Call the method to initialize resource cards
        resourceCardInitializer.initializeResourceCards();

        // Verify that the resource cards are loaded into the game deck
        List<ResourceCard> resourceDeck = game.getResourceDeck();
        assertNotNull(resourceDeck);
        assertFalse(resourceDeck.isEmpty());

        // Verify the properties of the first card in the deck (example)
        ResourceCard firstCard = resourceDeck.get(0);
        assertNotNull(firstCard);
        assertEquals(1, firstCard.getCardNumber());
        assertEquals(10, firstCard.getPoints());  // assuming the first card has 10 points in the JSON file
        assertEquals("src/main/resources/images/resCards/front/1.png", firstCard.getFrontImagePath());
        assertEquals("src/main/resources/images/resCards/back/orange.png", firstCard.getBackImagePath());
    }

    @Test
    void addCardToGame() {
    }
    */
}