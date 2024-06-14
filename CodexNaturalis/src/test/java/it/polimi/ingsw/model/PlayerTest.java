package it.polimi.ingsw.model;

import it.polimi.ingsw.server.model.GoldCard;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.server.model.VisibleAngle;
import org.junit.jupiter.api.Test;



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
    }

    @Test
    void getPlayerObjectiveCard() {
    }

    @Test
    void setObjectiveCard() {
    }

    @Test
    void getObjectiveCardFromHand() {
    }

    @Test
    void setPlayerToken() {
    }

    @Test
    void setPlayerTokenS() {
    }

    @Test
    void getPlayerToken() {
    }

    @Test
    void getPlayerName() {
    }

    @Test
    void getResourcesAvailable() {
    }

    @Test
    void setResource() {
    }

    @Test
    void setPlayerPoints() {
    }

    @Test
    void getPlayerPoints() {
    }

    @Test
    void addPoints() {
    }

    @Test
    void resourceLowering() {
    }

    @Test
    void resourceAdding() {
    }

    @Test
    void getInitialCard() {
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