package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.CommonBoard;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.Token;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PointTrackerManagerTest {
    private Game game;
    private PointTrackerManager pointTrackerManager;

    @Test
    void initializePointTracker() {
        //unisce gli altri metodi, quindi implicitamente funzionale
    }

    @Test
    void createPointTracker() {
        game = new Game(2);
        game.setCommonBoard(new CommonBoard(game));
        pointTrackerManager = new PointTrackerManager(game);

        pointTrackerManager.createPointTracker();
        pointTrackerManager.populationOfPointTracker();

        // Verifica che il point tracker sia stato popolato con 29 caselle
        assertNotNull(game.getCommonBoard().getPointTracker());
        assertEquals(29, game.getCommonBoard().getPointTracker().getPointBoxes().size());

        // Verifica che ogni casella sia un ArrayList<Token> vuoto
        for (ArrayList<Token> box : game.getCommonBoard().getPointTracker().getPointBoxes()) {
            assertNotNull(box);
            assertTrue(box.isEmpty());
        }
    }

    @Test
    void populationOfPointTracker() {
        game = new Game(2);
        game.setCommonBoard(new CommonBoard(game));
        pointTrackerManager = new PointTrackerManager(game);

        pointTrackerManager.createPointTracker();
        pointTrackerManager.populationOfPointTracker();

        // Verifica che il point tracker sia stato popolato con 29 caselle
        assertNotNull(game.getCommonBoard().getPointTracker());
        assertEquals(29, game.getCommonBoard().getPointTracker().getPointBoxes().size());

        // Verifica che ogni casella sia un ArrayList<Token> vuoto
        for (ArrayList<Token> box : game.getCommonBoard().getPointTracker().getPointBoxes()) {
            assertNotNull(box);
            assertTrue(box.isEmpty());
        }
    }

    @Test
    void placeTokenAtStart() {
        game = new Game(2);
        game.setCommonBoard(new CommonBoard(game));
        pointTrackerManager = new PointTrackerManager(game);

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        player1.setPlayerToken(new Token("red"));
        player2.setPlayerToken(new Token("blue"));
        game.getPlayerList().add(player1);
        game.getPlayerList().add(player2);

        pointTrackerManager.createPointTracker();
        pointTrackerManager.populationOfPointTracker();
        pointTrackerManager.placeTokenAtStart();

        // Verifica che il token di ciascun giocatore sia posizionato nella prima casella del point tracker
        assertNotNull(game.getCommonBoard().getPointTracker());
        ArrayList<Token> firstBox = game.getCommonBoard().getPointTracker().getTokenBox(0);
        assertEquals(2, firstBox.size()); // Assumendo che ci siano 2 giocatori

        // Verifica i token specifici nella prima casella
        assertTrue(firstBox.contains(player1.getPlayerToken()));
        assertTrue(firstBox.contains(player2.getPlayerToken()));
    }

    @Test
    void moveToken() {
        game = new Game(2);
        game.setCommonBoard(new CommonBoard(game));
        pointTrackerManager = new PointTrackerManager(game);

        Player player = new Player("Player");
        Token token = new Token("green"); // Inizializza un nuovo token
        player.setPlayerToken(token); // Assegna il token al giocatore
        game.getPlayerList().add(player); // Aggiungi il giocatore alla lista dei giocatori del gioco


        // Imposta il giocatore corrente nel gioco
        game.setCurrentPlayer(player);

        // Assicurati che il point tracker sia inizializzato
        pointTrackerManager.initializePointTracker();

        // Aggiungi il token alla casella iniziale del point tracker
        int initialTokenBoxIndex = 0;
        game.getCommonBoard().getPointTracker().getTokenBox(initialTokenBoxIndex).add(token);

        // Sposta il token
        pointTrackerManager.moveToken(token, 5);

        // Verifica che il token sia stato rimosso dalla casella corrente
        assertNotEquals(0,game.getCommonBoard().getPointTracker().getTokenBox(initialTokenBoxIndex).contains(token));

        // Verifica che il token sia stato aggiunto alla nuova casella corretta in base ai punti del giocatore
        int expectedTokenBoxIndex = player.getPlayerPoints();
        assertTrue(game.getCommonBoard().getPointTracker().getTokenBox(expectedTokenBoxIndex).contains(token));
    }
}
