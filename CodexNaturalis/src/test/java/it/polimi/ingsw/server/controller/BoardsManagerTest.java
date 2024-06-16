package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.server.model.Boards;

class BoardsManagerTest {

    private Game game;
    private BoardsManager boardsManager;


    @Test
    public void testInitializeBoards() {
        game = new Game(2);
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        game.getPlayerList().add(player1);
        game.getPlayerList().add(player2);

        boardsManager = new BoardsManager(game);

        boardsManager.initializeBoards();

        // Verifica che ogni giocatore abbia associato un Boards correttamente
        for (Player player : game.getPlayerList()) {
            assertNotNull(player.getGameboard()); // Assicura che il giocatore abbia un Boards associato
        }
    }

    @Test
    public void testCleanCheckBoard() {
        game = new Game(2);
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        game.getPlayerList().add(player1);
        game.getPlayerList().add(player2);

        boardsManager = new BoardsManager(game);
        // Creazione di un nuovo Boards
        Boards boards = new Boards();

        // Chiamata a cleanCheckBoard()
        boardsManager.cleanCheckBoard(boards);

        // Verifica che tutte le caselle del checkBoard siano inizializzate correttamente a -1
        for (int i = 0; i < boards.getMAX_X(); i++) {
            for (int j = 0; j < boards.getMAX_Y(); j++) {
                assertEquals(-1, boards.getCheckBoard()[i][j]);
            }


        }
    }
}