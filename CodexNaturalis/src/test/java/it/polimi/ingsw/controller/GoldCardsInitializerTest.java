package it.polimi.ingsw.controller;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldCardsInitializerTest {

    @Test
    public void testAddCardToGame() {
        Game game = new Game(1);

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

        int[] array = new int[7];

        GoldCard card = new GoldCard(0, resCardFrontAngles, resCardBackAngles, symbols, 1, 3, array, null, null);
        game.addGoldCardToDeck(card);

        // Verifica che la carta sia stata aggiunta correttamente al gioco
        System.out.println(card);
        assertEquals(1, game.getGoldDeckSize(), "La carta non è stata aggiunta correttamente al gioco");

    }
}
