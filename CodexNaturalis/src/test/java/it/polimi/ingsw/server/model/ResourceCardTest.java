package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceCardTest {
    @Test
    void getCardPoints() {
        VisibleAngle[] angles = new VisibleAngle[4];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        ResourceCard card = new ResourceCard(1,2, angles, angles, null, null, null);
        assertEquals(card.getCardPoints(), 2);
    }


    @Test
    void getCardNumber() {
        VisibleAngle[] angles = new VisibleAngle[4];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        ResourceCard card = new ResourceCard(1,2, angles, angles, null, null, null);
        assertEquals(card.getCardNumber(), 1);
    }
}
