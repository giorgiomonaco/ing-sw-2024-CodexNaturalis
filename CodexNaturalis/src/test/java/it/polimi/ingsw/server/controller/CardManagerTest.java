package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.Symbol;
import org.junit.jupiter.api.Test;

import java.util.List;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;

class CardManagerTest {

    private Game game;
    private CardManager cardManager;

    @Test
    void initializeAllCards() {
        game = new Game(2);
        Player player1 = new Player("Player 1");
        game.getPlayerList().add(player1);
        cardManager = new CardManager(game);

        cardManager.initializeAllCards();

        // Verifica che i mazzi nel gioco siano stati inizializzati correttamente
        assertFalse(game.getResourceDeck().isEmpty());
        assertFalse(game.getGoldDeck().isEmpty());
        assertFalse(game.getObjectiveDeck().isEmpty());
        assertFalse(game.getInitialDeck().isEmpty());
    }

    @Test
    void getSymbols() {

        game = new Game(2);
        Player player1 = new Player("Player 1");
        game.getPlayerList().add(player1);
        cardManager = new CardManager(game);

        cardManager.createAllSymbols();

        // Ottieni la lista di simboli
        List<Symbol> symbols = cardManager.getSymbols();

        // Verifica che la lista non sia nulla
        assertNotNull(symbols);

        // Verifica che la lista contenga esattamente 7 simboli
        assertEquals(7, symbols.size());

        // Verifica che i primi quattro simboli siano istanze di ResourceSymbol
        for (int i = 0; i < 4; i++) {
            Symbol symbol = symbols.get(i);
            assertNotNull(symbol);
            assertEquals("resource", symbol.getSymbolType());
        }

        // Verifica che i successivi tre simboli siano istanze di ObjectSymbol
        for (int i = 4; i < 7; i++) {
            Symbol symbol = symbols.get(i);
            assertNotNull(symbol);
            assertEquals("object", symbol.getSymbolType());
        }
    }


    @Test
    void createAllSymbols() {
        game = new Game(2);
        Player player1 = new Player("Player 1");
        game.getPlayerList().add(player1);
        cardManager = new CardManager(game);

        cardManager.createAllSymbols();

        // Verifica che la lista di simboli contenga tutti i simboli creati
        assertEquals(7, cardManager.getSymbols().size()); // Assumendo che vengano creati 7 simboli come indicato nel commento della classe

    }

}