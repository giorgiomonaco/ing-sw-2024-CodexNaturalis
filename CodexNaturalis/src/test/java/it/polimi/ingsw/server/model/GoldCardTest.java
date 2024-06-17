package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldCardTest {

    @Test
    void getCardName() {
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
        assertEquals(card.getCardNumber(), 1);
    }

    @Test
    void getNeededSymbols() {
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
        assertArrayEquals (card.getNeededSymbols(), array);
    }

    @Test
    void getCardPoints() {
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
        assertEquals(card.getCardPoints(), 1);
    }

    @Test
    void getCondition() {
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
        assertEquals(card.getCondition(), 0);
    }
}