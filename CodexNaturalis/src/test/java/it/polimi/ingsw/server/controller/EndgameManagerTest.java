package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EndgameManagerTest {

    private Game game = new Game(1);
    private Player player = new Player("tom");
    private final Boards board = new Boards();
    private EndgameManager endgameManager;

    @BeforeEach
    void setUp() {
        game = new Game(1);
        player = new Player("tom");
        endgameManager = new EndgameManager(game, player);

        List<ObjectiveCard> common = new ArrayList<>();

        ObjectiveCard objectiveCard1 = new ObjectiveCard( 1, 2, "pos", "green", "down-left", "green", "down-left", "green", null);
        ObjectiveCard objectiveCard2 = new ObjectiveCard( 1, 2, "pos", "green", "down-left", "green", "down-left", "green", null);

        common.add(objectiveCard1);
        common.add(objectiveCard2);
        game.setCommonObjectives(common);

        player.setBoards(board);

    }

    private static class Leaf extends Symbol {
        public Leaf() {
            super("leaf", "resource");
        }
    }
    private static class Butterfly extends Symbol {
        public Butterfly() {
            super("butterfly", "resource");
        }
    }

    //new ObjectiveCard(1, 2, "position", "purple", "down-left", "purple", "down-left", "purple", null)


    @Test
    void testObjectivePointsCounterWithNullValues() {
        // Test with player having no objective card
        player.setObjectiveCard(null);
        game.setCommonObjectives(null);

        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void testObjectiveCreatorWithNullGameBoard() {
        player.setBoards(null);
        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void testResourceCounterWithNullResources() {
        for (int i = 0; i < 7; i++) {
            player.setResource(i, 0);
        }
        ObjectiveCard objectiveCard = new ObjectiveCard(1, 2, "position", "purple", "down-left", "purple", "down-left", "purple", null);
        player.setObjectiveCard(objectiveCard);

        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void testSpecialCounterWithNullResources() {
        for (int i = 0; i < 7; i++) {
            player.setResource(i, 0);
        }
        ObjectiveCard objectiveCard = new ObjectiveCard(1, 2, "position", "purple", "down-left", "purple", "down-left", "purple", null);
        player.setObjectiveCard(objectiveCard);

        assertEquals(0, endgameManager.objectivePointsCounter());
    }

    @Test
    void objectivePointsCounter() {

    }


    @Test
    void testCheckDirectionDown() {
        player.setBoards(board);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[1][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);
        cardMatrix[1][5] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertTrue(manager.checkDirection( cardMatrix, 1, 1, "down", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 1, 2, "down", "green"));
        assertTrue(manager.checkDirection( cardMatrix, 1, 3, "down", "green"));
    }


    @Test
    void testCheckDirectionDownRight() {
        player.setBoards(board);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[1][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertTrue(manager.checkDirection( cardMatrix, 1, 1, "down-right", "green"));
        assertTrue(manager.checkDirection( cardMatrix, 2, 2, "down-right", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 3, 3, "down-right", "green"));
    }

    @Test
    void testCheckDirectionDownLeft() {
        player.setBoards(board);
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertTrue(manager.checkDirection( cardMatrix, 5, 1, "down-left", "green"));
        assertTrue(manager.checkDirection( cardMatrix, 4, 2, "down-left", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 3, 3, "down-left", "green"));
    }

    @Test
    void testCheckDirectionOut() {
        Card[][] cardMatrix = new Card[6][6]; // Example matrix size

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);


        // Populate the matrix
        cardMatrix[1][5] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][4] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        assertFalse(manager.checkDirection( cardMatrix, 1, 99, "down", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 99, 2, "down-right", "green"));
        assertFalse(manager.checkDirection( cardMatrix, 0, 3, "down-left", "green"));
    }


    /**
     *  Test the full sequence of diagonal objectives checking directions, and assigning points
     */
    @Test
    void testDiagonalObj() {
        player.setBoards(board);
        Card [][] cardMatrix = player.getGameBoards().getGameBoard();
        List<ObjectiveCard> common = new ArrayList<>();

        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 2, "position", "green", "down-left", "green", "down-left", "green", null);
        ObjectiveCard objectiveCard1 = new ObjectiveCard( 1, 2, "pos", "green", "down-left", "green", "down-left", "green", null);
        ObjectiveCard objectiveCard2 = new ObjectiveCard( 1, 2, "pos", "green", "down-left", "green", "down-left", "green", null);

        common.add(objectiveCard1);
        common.add(objectiveCard2);

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);
        player.setObjectiveCard(objectiveCard);
        game.setCommonObjectives(common);


        // Populate the matrix with four green cards and one purple card in diagonal : 2 points expected
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][4] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][5] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        EndgameManager manager = new EndgameManager(game,player);

        // Test that he recognize only one pattern
        assertEquals(2, manager.objectivePointsCounter());

        // Populate the matrix with five green cards in diagonal : 2 points expected
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][4] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][5] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);

        // Test that he recognize only one pattern
        assertEquals(2, manager.objectivePointsCounter());

        // Populate the matrix with six green cards in diagonal : 4 points expected
        cardMatrix[5][1] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[4][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][3] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][4] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[1][5] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[0][6] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);

        // Test that he recognize two patterns
        assertEquals(4, manager.objectivePointsCounter());
    }


    /**
     *  Test the full sequence of L objectives checking directions, and assigning points
     */
    @Test
    void testLObj() {
        player.setBoards(board);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 3, "position", "green", "down", "green", "down-left", "purple", null);
        ObjectiveCard objectiveCard1 = new ObjectiveCard( 1, 2, "pos", "green", "down-left", "green", "down-left", "green", null);
        ObjectiveCard objectiveCard2 = new ObjectiveCard( 1, 2, "pos", "green", "down-left", "green", "down-left", "green", null);

        Card [][] cardMatrix = player.getGameBoards().getGameBoard();
        List<ObjectiveCard> common = new ArrayList<>();
        common.add(objectiveCard1);
        common.add(objectiveCard2);

        Symbol symbol1 = new Leaf();
        Symbol symbol2 = new Butterfly();
        VisibleAngle[] frontAngles = new VisibleAngle[4];
        VisibleAngle[] backAngles = new VisibleAngle[4];
        for (int i = 0; i <4 ; i++) {
            frontAngles[i] = new VisibleAngle(symbol1);
            backAngles[i] = new VisibleAngle(null);
        }

        List<Symbol> myList = new ArrayList<>();
        myList.add(symbol1);

        List<Symbol> secondList = new ArrayList<>();
        secondList.add(symbol2);
        player.setObjectiveCard(objectiveCard);
        game.setCommonObjectives(common);
        EndgameManager manager = new EndgameManager(game,player);

        // Populate the matrix with four green cards and one purple card in diagonal : 3 points expected
        cardMatrix[3][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][4] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][5] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        // Test that he recognize only one pattern
        assertEquals(3, manager.objectivePointsCounter());

        // Populate the matrix with five green cards in diagonal : 3 points expected
        cardMatrix[3][0] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][4] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][5] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        // Test that he recognize only one pattern
        assertEquals(3, manager.objectivePointsCounter());

        // Populate the matrix with six green cards in diagonal : 3 points expected
        cardMatrix[3][0] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][3] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);
        cardMatrix[3][4] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][5] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        // Test that he recognize only one pattern
        assertEquals(3, manager.objectivePointsCounter());

        // Populate the matrix with six green cards in diagonal : 6 points expected
        cardMatrix[3][0] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][2] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][3] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);
        cardMatrix[3][4] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[3][6] = new ResourceCard(1, 2, frontAngles, backAngles, myList, null, null);
        cardMatrix[2][7] = new ResourceCard(1, 2, frontAngles, backAngles, secondList, null, null);

        // Test that he recognize two patterns
        assertEquals(6, manager.objectivePointsCounter());
    }


    /**
     *  Test for the resource objective
     */
    @Test
    void testResourceObjectives(){
        player.setResource(1,3);
        EndgameManager manager = new EndgameManager(game,player);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 2, "leaf", "green", "down-left", "green", "down-left", "green", null);

        player.setObjectiveCard(objectiveCard);
        player.setBoards(board);

        assertEquals(2, manager.objectivePointsCounter());

    }


    /**
     *  Test for the special objective
     */
    @Test
    void testSpecialObjectives(){
        player.setResource(4,3);
        player.setResource(5,6);
        player.setResource(6,6);

        EndgameManager manager = new EndgameManager(game,player);
        ObjectiveCard objectiveCard = new ObjectiveCard( 1, 3, "special", "green", "down-left", "green", "down-left", "green", null);


        player.setObjectiveCard(objectiveCard);
        player.setBoards(board);

        assertEquals(9, manager.objectivePointsCounter());

    }

}


