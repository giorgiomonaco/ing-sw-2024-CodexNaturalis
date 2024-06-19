package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonBoardManagerTest {
    private CommonBoardManager manager;
    private Game game;
    private CommonBoard commonBoard;
    private final List<Symbol> symbols = new ArrayList<>();
    @Test
    void initializeCommonBoard() {
        game = new Game(2);
        commonBoard= new CommonBoard(game);
        manager = new CommonBoardManager(game);

        game.setCommonBoard(commonBoard);
        manager.initializeCommonBoard();

        // Verifica che il CommonBoard sia associato correttamente al gioco
        assertEquals(commonBoard, game.getCommonBoard());
 }

    @Test
    void associateCommonBoard() {
        game = new Game(2);
        commonBoard= new CommonBoard(game);
        manager = new CommonBoardManager(game);

        ResourceCardInitializer resourceCardInitializer = new ResourceCardInitializer(game, symbols);
        //initialize the resource cards
        resourceCardInitializer.initializeResourceCards();


        // Verifica che una carta di risorse sia stata disegnata e aggiunta al CommonBoard
        List<ResourceCard> discoveredSourceCards = game.getCommonBoard().getDiscoveredResourceCards();
        assertEquals(1, discoveredSourceCards.size());
        assertNotNull(discoveredSourceCards.get(0));
    }

    @Test
    void discoverResourceCard() {
    }

    @Test
    void discoverGoldCard() {
    }

    @Test
    void discoverObjectiveCard() {
    }
}