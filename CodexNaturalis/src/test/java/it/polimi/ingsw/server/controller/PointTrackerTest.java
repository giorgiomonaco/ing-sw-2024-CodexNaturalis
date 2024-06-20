package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.PointTracker;
import it.polimi.ingsw.server.model.Token;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointTrackerTest {
    private PointTracker pointTracker = new PointTracker();
    @Test
    void getTokenBox() {
        ArrayList<Token> box = new ArrayList<>();
        pointTracker.addBoxToTracker(box);

        // Verifica che il metodo getTokenBox funzioni correttamente
        assertEquals(box, pointTracker.getTokenBox(0));
    }

    @Test
    void addBoxToTracker() {
        // Crea una nuova casella di token
        ArrayList<Token> box = new ArrayList<>();
        Token token = new Token("red");
        box.add(token);

        // Aggiungi la casella al point tracker
        pointTracker.addBoxToTracker(box);

        // Verifica che la casella sia stata aggiunta correttamente
        assertEquals(1, pointTracker.getPointBoxes().size());
        assertTrue(pointTracker.getPointBoxes().contains(box));
    }

    @Test
    void getPointBoxes() {
        List<ArrayList<Token>> boxes = pointTracker.getPointBoxes();
        assertNotNull(boxes);
        assertEquals(0, boxes.size());
    }
}