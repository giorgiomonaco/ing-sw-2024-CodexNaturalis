package it.polimi.ingsw.model;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    void getPlayerResourceCards() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        ResourceCard card = new ResourceCard(1,2, angles, angles, null);
        player.addResourceCard(card);
        assertEquals(player.getPlayerResourceCards().getFirst(), card);
    }

    @Test
    void addResourceCard() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        ResourceCard card = new ResourceCard(1,2, angles, angles, null);
        player.addResourceCard(card);
        assertEquals(player.getPlayerResourceCards().getFirst(), card);
    }

    @Test
    void getResourceCardFromHand() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        ResourceCard card = new ResourceCard(1,2, angles, angles, null);
        player.getPlayerHand().add(card);

        assertEquals(player.getPlayerHand().getFirst(), card);
    }

    @Test
    void getPlayerGoldCards() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array);
        player.addGoldCard(card);
        assertEquals(player.getPlayerGoldCards().getFirst(), card);
    }

    @Test
    void addGoldCard() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array);
        player.addGoldCard(card);
        assertEquals(player.getPlayerGoldCards().getFirst(), card);
    }

    @Test
    void getGoldCardFromHand() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array);
        player.getPlayerGoldCards().add(card);
        assertEquals(player.getGoldCardFromHand(0), card);
    }

    @Test
    void removeCardFromHand() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array);
        player.getPlayerHand().add(card);
        player.removeCardFromHand(card);
        assertEquals(player.getPlayerHand().size(), 0);
    }

    @Test
    void getPlayerObjectiveCard() {
        Player player = new Player("test");
        ObjectiveCard card = new ObjectiveCard(1, 1, "leaf", null, null ,null, null, null);
        player.setObjectiveCard(card);
        assertEquals(player.getPlayerObjectiveCard(), card);
    }

    @Test
    void setObjectiveCard() {
        Player player = new Player("test");
        ObjectiveCard card = new ObjectiveCard(1, 1, "leaf", null, null ,null, null, null);
        player.setObjectiveCard(card);
        assertEquals(player.getPlayerObjectiveCard(), card);
    }


    @Test
    void setPlayerToken() {
        Player player = new Player("test");
        Token token = new Token("red");
        player.setPlayerToken(token);
        assertEquals(player.getPlayerToken(), token);
    }

    @Test
    void setPlayerTokenS() {
        Player player = new Player("test");
        Token token = new Token("red");
        player.setPlayerToken(token);
        assertEquals(player.getPlayerToken(), token);
    }

    @Test
    void getPlayerToken() {
        Player player = new Player("test");
        Token token = new Token("red");
        player.setPlayerToken(token);
        assertEquals(player.getPlayerToken(), token);
    }

    @Test
    void getPlayerName() {
        Player player = new Player("test");
        assertEquals(player.getPlayerName(), "test");
    }

    @Test
    void getResourcesAvailable() {
        Player player = new Player("test");
        int[] array = new int[7];
        for(int i = 0; i<7; i++){
            player.setResource(i, 0);
        }
        assertArrayEquals(player.getResourcesAvailable(), array);

    }

    @Test
    void setResource() {
        Player player = new Player("test");
        int[] array = new int[7];
        for(int i = 0; i<7; i++){
            player.setResource(i, 0);
        }
        assertArrayEquals(player.getResourcesAvailable(), array);
    }

    @Test
    void setPlayerPoints() {
        Player player = new Player("test");
        int points = 2;
        player.setPlayerPoints(points);
        assertEquals(player.getPlayerPoints(), points);
    }

    @Test
    void getPlayerPoints() {
        Player player = new Player("test");
        int points = 2;
        player.setPlayerPoints(points);
        assertEquals(player.getPlayerPoints(), points);
    }

    @Test
    void addPoints() {
        Player player = new Player("test");
        int points = 2;
        int added = 3;
        player.setPlayerPoints(points);
        player.addPoints(added);
        assertEquals(player.getPlayerPoints(), points+added);
    }

    @Test
    void resourceLowering() {
        Player player = new Player("test");
        ResourceSymbol symbol = new ResourceSymbol("leaf", "res");
        player.setResource(1, 1);
        player.resourceLowering(symbol);
        assertEquals(player.getResourcesAvailable()[1], 0);
    }

    @Test
    void resourceAdding() {
        Player player = new Player("test");
        ResourceSymbol symbol = new ResourceSymbol("leaf", "res");
        player.resourceAdding(symbol);
        assertEquals(player.getResourcesAvailable()[1], 1);
    }

    @Test
    void getInitialCard() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        ResourceSymbol symbol = new ResourceSymbol("leaf", "res");
        List<Symbol> list =new ArrayList<>();
        list.add(symbol);
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        InitialCard card = new InitialCard(1, angles,  angles, list);
        player.setInitialCard(card);
        assertEquals(player.getInitialCard(), card);
    }

    @Test
    void setInitialCard() {
    }

    @Test
    void getUsername() {
    }

    @Test
    void getPlayerHand() {
    }

    @Test
    void getSelObjectiveCard() {
    }

    @Test
    void addSelObjectiveCard() {
    }

    @Test
    void getGameBoards() {
    }

    @Test
    void setBoards() {
    }

    @Test
    void getPoints() {
    }

    @Test
    void getChat() {
    }

    @Test
    void isConnected() {
    }

    @Test
    void setConnected() {
    }

    @Test
    void setChat() {
    }

    @Test
    void addGoldCardPoints() {
    }
}