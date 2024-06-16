package it.polimi.ingsw.server.model;

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
        ResourceCard card = new ResourceCard(1,2, angles, angles, null, null, null);
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
        ResourceCard card = new ResourceCard(1,2, angles, angles, null, null, null);
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
        ResourceCard card = new ResourceCard(1,2, angles, angles, null, null, null);
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
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
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
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
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
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
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
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
        player.getPlayerHand().add(card);
        player.removeCardFromHand(card);
        assertEquals(player.getPlayerHand().size(), 0);
    }

    @Test
    void getPlayerObjectiveCard() {
        Player player = new Player("test");
        ObjectiveCard card = new ObjectiveCard(1, 1, "leaf", null, null ,null, null, null, null);
        player.setObjectiveCard(card);
        assertEquals(player.getPlayerObjectiveCard(), card);
    }

    @Test
    void setObjectiveCard() {
        Player player = new Player("test");
        ObjectiveCard card = new ObjectiveCard(1, 1, "leaf", null, null ,null, null, null, null);
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

        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        InitialCard card = new InitialCard(1, angles,  angles, list, null, null);
        player.setInitialCard(card);
        assertEquals(player.getInitialCard(), card);
    }

    @Test
    void setInitialCard() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        ResourceSymbol symbol = new ResourceSymbol("leaf", "res");
        List<Symbol> list =new ArrayList<>();
        list.add(symbol);

        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        InitialCard card = new InitialCard(1, angles,  angles, list, null, null);
        player.setInitialCard(card);
        assertEquals(player.getInitialCard(), card);

    }

    @Test
    void getUsername() {
        Player player = new Player("test");
        assertEquals(player.getUsername(), "test");
    }

    @Test
    void getPlayerHand() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
        for(int i =0; i<3; i++){
            player.getPlayerHand().add(card);
        }
        assertEquals(player.getPlayerHand().getFirst(), card);

    }

    @Test
    void getSelObjectiveCard() {
        Player player = new Player("test");
        ObjectiveCard card = new ObjectiveCard(1, 1, "leaf", null, null ,null, null, null, null);
        player.setObjectiveCard(card);
        assertEquals(player.getPlayerObjectiveCard(), card);
    }

    @Test
    void addSelObjectiveCard() {
        Player player = new Player("test");
        ObjectiveCard card = new ObjectiveCard(1, 1, "leaf", null, null ,null, null, null,  null);
        player.addSelObjectiveCard(card);
        assertEquals(player.getSelObjectiveCard().getFirst(), card);
    }

    @Test
    void getGameBoards() {
        Player player = new Player("test");
        Boards boards = new Boards();
        player.setBoards(boards);
        assertEquals(player.getGameBoards(), boards);
    }

    @Test
    void setBoards() {
        Player player = new Player("test");
        Boards boards = new Boards();
        player.setBoards(boards);
        assertEquals(player.getGameBoards(), boards);
    }

    @Test
    void getPoints() {
        Player player = new Player("test");
        int points = 2;
        player.setPlayerPoints(points);
        assertEquals(player.getPoints(), points);

    }

    @Test
    void getChat() {
        Player player = new Player("test");
        List<Chat> chats= new ArrayList<>();
        Chat prova = new Chat("tom", "ciao");
        chats.add(prova);
        chats.add(prova);
        player.setChat(chats);
        assertEquals(player.getChat().getFirst(), prova);
    }

    @Test
    void isConnected() {
        Player player = new Player("test");
        player.setConnected(true);
        assertTrue(player.isConnected());


    }

    @Test
    void setConnected() {
        Player player = new Player("test");
        player.setConnected(true);
        assertTrue(player.isConnected());
    }

    @Test
    void setChat() {
        Player player = new Player("test");
        List<Chat> chats= new ArrayList<>();
        Chat prova = new Chat("tom", "ciao");
        chats.add(prova);
        chats.add(prova);
        player.setChat(chats);
        assertEquals(player.getChat().getFirst(), prova);
    }

    @Test
    void addGoldCardPoints() {
        Player player = new Player("test");
        VisibleAngle[] angles = new VisibleAngle[4];
        int[] array = new int[7];
        for(int i = 0; i<4; i++){
            angles[i] = new VisibleAngle(null);
        }
        GoldCard card = new GoldCard(1, angles, angles, null, 0 ,1, array, null,null);
        player.addGoldCard(card);
        player.addGoldCardPoints(card, 49,49);
        assertEquals(player.getPoints(), 1);
    }
}