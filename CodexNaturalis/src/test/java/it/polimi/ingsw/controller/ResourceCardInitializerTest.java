package it.polimi.ingsw.controller;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceCardInitializerTest {

    @Test
    public void testAddCardToGame() {
        // Creazione del gioco
        GameState gameState = new GameState();
        gameState.setGameState(1);
        Game game = new Game(gameState);

        // Creazione di una lista di simboli di esempio
        List<Symbol> symbols = new ArrayList<>();
        symbols.add(new ResourceSymbol("mushroom", "resource"));
        symbols.add(new ResourceSymbol("leaf", "resource"));
        symbols.add(new ResourceSymbol("fox", "resource"));
        symbols.add(new ResourceSymbol("butterfly", "resource"));
        symbols.add(new ObjectSymbol("feather", "resource"));
        symbols.add(new ObjectSymbol("bottle", "resource"));
        symbols.add(new ObjectSymbol("scroll", "resource"));

        // Creazione del ResourceCardInitializer
        VisibleAngle[] resCardBackAngles = new VisibleAngle[4];
        for (int i = 0; i < 4; i++) {
            resCardBackAngles[i] = new VisibleAngle(null);
        }

        VisibleAngle[] resCardFrontAngles = new VisibleAngle[4];
        resCardFrontAngles[0] = new VisibleAngle(symbols.get(1));
        resCardFrontAngles[1] = new VisibleAngle(null);
        resCardFrontAngles[2] = new VisibleAngle(null);
        resCardFrontAngles[3] = new VisibleAngle(null);

        // Aggiunta di una carta al gioco
        ResourceCard card = new ResourceCard(0, 3, resCardFrontAngles, resCardBackAngles, symbols.get(0));
        game.addResourceCardToDeck(card);

        // Verifica che la carta sia stata aggiunta correttamente al gioco
        System.out.println(card);
        assertEquals(1, game.getResourceDeckSize(), "La carta non è stata aggiunta correttamente al gioco");

    }
}
