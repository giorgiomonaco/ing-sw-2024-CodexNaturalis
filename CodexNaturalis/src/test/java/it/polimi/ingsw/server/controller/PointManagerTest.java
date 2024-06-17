package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointManagerTest {
    private Game game;


    PointManager pointManager;

    @Test
    void givePoints_ResourceCard() {
        game = new Game(2);
        pointManager = new PointManager(game);

        Player p1 = new Player("Player 1");
        game.getPlayerList().add(p1);

        game.setCurrentPlayer(p1);
        game.setCommonBoard(new CommonBoard(game));


        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        // Crea una ResourceCard con 5 punti
        ResourceCard resourceCard = new ResourceCard(1, 5, angles, angles, null, "", "");

        // Chiamata a givePoints() con la ResourceCard
        pointManager.givePoints(resourceCard);

        // Verifica che il giocatore corrente abbia ricevuto i punti corretti
        assertEquals(5, game.getCurrentPlayer().getPlayerPoints());
    }

    @Test
    public void testGivePoints_GoldCard() {
        game = new Game(2);
        pointManager = new PointManager(game);

        Player p1 = new Player("Player 1");
        game.getPlayerList().add(p1);

        game.setCurrentPlayer(p1);
        game.setCommonBoard(new CommonBoard(game));

        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);

        // Chiamata a givePoints() con la GoldCard
        pointManager.givePoints(card);

        // Verifica che il giocatore corrente abbia ricevuto i punti corretti
        assertEquals(1, game.getCurrentPlayer().getPlayerPoints());
    }


    @Test
    void assignPoints() {  // Assegna 8 punti al giocatore corrente
        game = new Game(2);
        pointManager = new PointManager(game);

        Player p1 = new Player("Player 1");
        game.getPlayerList().add(p1);

        game.setCurrentPlayer(p1);
        game.setCommonBoard(new CommonBoard(game));

        pointManager.assignPoints(8);

        // Verifica che il giocatore corrente abbia ricevuto i punti corretti
        assertEquals(8, game.getCurrentPlayer().getPlayerPoints());
    }
}
