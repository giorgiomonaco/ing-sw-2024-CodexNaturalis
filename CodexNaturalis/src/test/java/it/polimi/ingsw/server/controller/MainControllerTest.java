package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.LobbyCreation;
import it.polimi.ingsw.network.message.allMessages.SelectObjCard;
import it.polimi.ingsw.server.ServerHandler;
import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {
    private Game game;
    private ServerHandler serverHandler;
    private GameSetUpper gameSetUpper;
    private ServerConfigNetwork serverConfigNetwork;
    private int currPlayerIndex;
    private int finalPlayerIndex;
    private int firstTurnIndex;
    private boolean firstTurn;
    private Message msg;
    private MainController mainController;
    private VisibleAngle visibleAngle;
    private Symbol symbol;
    private Boards boards;
    private GoldCard card;
    private VisibleAngle[] frontvisibleAngles;
    private VisibleAngle[] backvisibleAngles;
    private List<Symbol> symbols;


    @Test
    void gameCreation() {
        String username1 = "feb";
        Player p1 = new Player(username1);
        String username2 = "gio";
        Player p2 = new Player(username2);
        List<String> list = new ArrayList<>();
        list.add(username1);list.add(username2);
        int numPlayer = 2;
        game = new Game(numPlayer);
        gameSetUpper= new GameSetUpper(game);
        gameSetUpper.CreateGame(username1);

        msg = new LobbyCreation(username1,"",list);

        assertNotNull(game);
        game.addPlayer(p2);
        assertEquals(numPlayer, game.getPlayerList().size());

        serverConfigNetwork = new ServerConfigNetwork();
        serverHandler = new ServerHandler(serverConfigNetwork);

        // Verifica che il messaggio sia stato inviato correttamente
        serverHandler.sendMessageToPlayer(username1, new LobbyCreation(ServerHandler.HOSTNAME,
                "--Lobby created!--\nWaiting for the other " + (numPlayer-1) + " player/s to join...",
                game.getUserList()));

        assertEquals(2,game.getUserList().size());


    }

    @Test
    void getGame() {
        String username1 = "feb";
        Player p1 = new Player(username1);
        String username2 = "gio";
        Player p2 = new Player(username2);
        List<String> list = new ArrayList<>();
        list.add(username1);list.add(username2);
        int numPlayer = 2;
        game = new Game(numPlayer);


        serverConfigNetwork = new ServerConfigNetwork();
        serverHandler = new ServerHandler(serverConfigNetwork);
        mainController = new MainController(serverHandler);
        mainController.gameCreation(username1,numPlayer);
        mainController.getGame().addPlayer(p2);

        assertNotNull(mainController.getGame());
        assertEquals(2,mainController.getGame().getUserList().size());
    }

    @Test
    void joinPlayer() {
    }

    @Test
    void beginTurn() {
    }

    @Test
    void beginFirstTurn() {
    }

    @Test
    void endTurn() {
    }

    @Test
    void updateCheckBoard() {
        symbol = new Symbol( "fox", "blue") {
            @Override
            public String getSymbolName() {
                return super.getSymbolName();
            }

            @Override
            public String getSymbolType() {
                return super.getSymbolType();
            }
        };
        visibleAngle = new VisibleAngle(symbol);
        String username1 = "feb";
        Player p1 = new Player(username1);
        String username2 = "gio";
        Player p2 = new Player(username2);
        List<String> list = new ArrayList<>();
        list.add(username1);list.add(username2);
        int numPlayer = 2;
        game = new Game(numPlayer);


        serverConfigNetwork = new ServerConfigNetwork();
        serverHandler = new ServerHandler(serverConfigNetwork);
        mainController = new MainController(serverHandler);
        mainController.gameCreation(username1,numPlayer);
        mainController.getGame().addPlayer(p2);

        boards = new Boards();
        mainController.getPlayerByUsername(p1.getPlayerName()).setBoards(boards);
        p1.setBoards(boards);
        int x= 50;
        int y=50;

        assertNotNull(mainController.getPlayerByUsername(p1.getPlayerName()).getGameboard().getCheckBoard());

        assertEquals(0,p1.getGameBoards().getCheckBoard()[x][y]);
    }

    @Test
    void endGame() {
    }


    @Test
    void updateBoxes() {
        symbol = new Symbol("fox", "blue") {
            @Override
            public String getSymbolName() {
                return super.getSymbolName();
            }

            @Override
            public String getSymbolType() {
                return super.getSymbolType();
            }
        };
        visibleAngle = new VisibleAngle(symbol);
        String username1 = "feb";
        Player p1 = new Player(username1);
        String username2 = "gio";
        Player p2 = new Player(username2);
        List<String> list = new ArrayList<>();
        list.add(username1);
        list.add(username2);
        int numPlayer = 2;
        game = new Game(numPlayer);


        serverConfigNetwork = new ServerConfigNetwork();
        serverHandler = new ServerHandler(serverConfigNetwork);
        mainController = new MainController(serverHandler);
        mainController.gameCreation(username1, numPlayer);
        mainController.getGame().addPlayer(p2);

        boards = new Boards();
        mainController.getPlayerByUsername(p1.getPlayerName()).setBoards(boards);
        p1.setBoards(boards);
        int x = 50;
        int y = 50;
    }

    @Test
    void gameStarting() {
    }

    @Test
    void getPlayerByUsername() {



    }

    @Test
    void drawCard() {

    }

    @Test
    void cardSelector() {
        symbol = new Symbol("fox", "blue") {
        @Override
        public String getSymbolName() {
            return super.getSymbolName();
        }

        @Override
        public String getSymbolType() {
            return super.getSymbolType();
        }
    };
        visibleAngle = new VisibleAngle(symbol);
        frontvisibleAngles = new VisibleAngle[4];
        backvisibleAngles = new VisibleAngle[4];
        symbols = new ArrayList<>();
        String username1 = "feb";
        Player p1 = new Player(username1);
        String username2 = "gio";
        Player p2 = new Player(username2);
        List<String> list = new ArrayList<>();
        list.add(username1);
        list.add(username2);
        int numPlayer = 2;
        game = new Game(numPlayer);
        game.addPlayer(p1);
        game.addPlayer(p2);

        card = new GoldCard(1,frontvisibleAngles,backvisibleAngles,symbols,0,0,null,null,null);


        //TEST AGGIUNTA CARTE
        game.getPlayerList().get(0).addGoldCard(card);
        assertNotNull(game.getPlayerList().get(0).getPlayerGoldCards());



        //TEST RIMOZIONE CARTE
        List<Card> temp = new ArrayList<>();

        game.getPlayerList().get(0).removeCardFromHand(card);
        assertEquals(temp,game.getPlayerList().get(0).getPlayerGoldCards());

        //RIMOZIONE INESISTENTE -> "CARD NOT FOUND" corretto

        game.getPlayerList().get(0).removeCardFromHand(card);
        assertEquals(temp,game.getPlayerList().get(0).getPlayerGoldCards());


        //TEST AGGIUNTA PIU' CARTE

        game.getPlayerList().get(0).addGoldCard(card);
        game.getPlayerList().get(0).addGoldCard(card);
        game.getPlayerList().get(0).addGoldCard(card);
        game.getPlayerList().get(0).addGoldCard(card);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            p1.addGoldCard(card);
        });

        assertEquals("too much cards! ERROR", exception.getMessage());
    }

    @Test
    void getUncoveredCards() {
    }

    @Test
    void isFirstTurn() {
    }

    @Test
    void selectionCard() {
    }

    @Test
    void middleTurn() {
    }

    @Test
    void playCard() {
            visibleAngle = new VisibleAngle(symbol);
            frontvisibleAngles = new VisibleAngle[4];
            backvisibleAngles = new VisibleAngle[4];
            symbols = new ArrayList<>();
            String username1 = "feb";
            Player p1 = new Player(username1);
            String username2 = "gio";
            Player p2 = new Player(username2);
            List<String> list = new ArrayList<>();
        list.add(username1);
        list.add(username2);
            int numPlayer = 2;
            game = new Game(numPlayer);
        game.addPlayer(p1);
        game.addPlayer(p2);

            card = new GoldCard(1,frontvisibleAngles,backvisibleAngles,symbols,0,0,null,null,null);
        Boolean side = true;

        //TEST SIDE CORRETTO
        card.setFrontSide(side);
        assertTrue(card.getSide());




    }

    @Test
    void playerDisconnect() {
    }

    @Test
    void checkNextTurnForDisconnection() {
    }

    @Test
    void isLastPlayer() {
    }

    @Test
    void setFirstTurn() {
        String username1 = "feb";
        Player p1 = new Player(username1);
        String username2 = "gio";
        Player p2 = new Player(username2);
        List<String> list = new ArrayList<>();
        list.add(username1);list.add(username2);
        int numPlayer = 2;
        game = new Game(numPlayer);


        serverConfigNetwork = new ServerConfigNetwork();
        serverHandler = new ServerHandler(serverConfigNetwork);
        mainController = new MainController(serverHandler);
        mainController.gameCreation(username1,numPlayer);
        mainController.getGame().addPlayer(p2);

        firstTurn = true;
        mainController.setFirstTurn(firstTurn);

        assertTrue(mainController.isFirstTurn());

        firstTurn = false;
        mainController.setFirstTurn(firstTurn);

        assertFalse(mainController.isFirstTurn());
    }
}