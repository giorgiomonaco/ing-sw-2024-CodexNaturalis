package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.server.ServerHandler;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController implements Serializable {
    private Game game;
    private final ServerHandler serverHandler;
    private GameSetUpper gameSetUpper;

    // Index of the current player
    private int currPlayerIndex;

    // Index of the first player to reach 21 points
    private int finalPlayerIndex;

    // If it's the first turn the players have to choose the token and the personal objective cards.
    private int firstTurnIndex;
    private boolean firstTurn;
    private Game game1;

    //Constructor, it only needs a game to control
    public MainController(ServerHandler serverHandler){
        this.serverHandler =  serverHandler;
    }

    //The main controller has to start the view as soon as the game is created
    //then has to process every response from the view and use the managers to
    //modify the state of the game and use the managers to modify everything


    //Here it plays the game
    public void gameCreation(String username, int numOfPlayers){

        game = new Game(numOfPlayers);
        //Then creates  the game set up
        gameSetUpper = new GameSetUpper(game);
        //Start the setup of the game
        gameSetUpper.CreateGame(username);

        serverHandler.sendMessageToPlayer(username,
                new LobbyCreation(ServerHandler.HOSTNAME,
                        "--Lobby created!--\nWaiting for the other " + (numOfPlayers-1) + " player/s to join...",
                        game.getUserList()));

    }


    public Game getGame() {
        return game;
    }


    public void joinPlayer(String username){
        if (!game.getGameState().equals(gameStateEnum.ACCEPT_PLAYER)) {
            serverHandler.sendMessageToPlayer(username,
                    new AlreadyStarted(ServerHandler.HOSTNAME));
            return;
        }
        try {
            game.addPlayer(new Player(username));
            List <String> userList = game.getUserList();
            serverHandler.sendMessageToAllExcept(username,
                    new NewPlayerJoin(ServerHandler.HOSTNAME,
                            "\nNew player logged!: " + username + "\nWaiting for the other " +
                                    (game.getPlayersNumber()- game.getPlayerList().size()) +
                                    " player/s to join...",
                            username));
            serverHandler.sendMessageToPlayer(username,
                    new LobbyCreation(ServerHandler.HOSTNAME,
                            "You joined the lobby!\nWaiting for the other " +
                                    (game.getPlayersNumber()- game.getPlayerList().size()) +
                                    " player/s to join...",
                            userList));

        } catch (IllegalStateException e) {
            System.err.println("Max number of player already reached.");
            serverHandler.sendMessageToPlayer(username,
                    new AlreadyStarted(ServerHandler.HOSTNAME));
        }

        if(game.getGameState().equals(gameStateEnum.START)){
            gameStarting();
            boolean admin;

            for(int i = 0; i < game.getUserList().size(); i++) {

                admin = i == 0;
                serverHandler.sendMessageToPlayer(game.getUserList().get(i),
                        new GameStarting(
                                ServerHandler.HOSTNAME,
                                admin,
                                game.getPlayerList().get(i).getPlayerHand(),
                                game.getCurrentPlayer().getInitialCard()));


            }

            beginFirstTurn();
        }
    }

    public void beginTurn() {

        if (game.getUserList().isEmpty()) {
            throw new IllegalStateException("User list is empty. Cannot begin turn.");
        }

        currPlayerIndex = (currPlayerIndex + 1) % game.getUserList().size();
        game.setCurrentPlayer(game.getPlayerList().get(currPlayerIndex));


        if (game.getGameState().equals(gameStateEnum.FINAL_TURN) && currPlayerIndex == finalPlayerIndex) {
            endGame();
        } else {
            serverHandler.sendMessageToPlayer(
                    game.getCurrentPlayer().getPlayerName(),
                    new PlayCardReq(ServerHandler.HOSTNAME,
                            game.getCurrentPlayer().getPlayerHand(),
                            game.getCurrentPlayer().getGameboard(),
                            game.getCurrentPlayer().getResourcesAvailable())
            );
        }
    }

    public void beginFirstTurn(){

        List<String> availableToken = game.getAvailableTokens();
        firstTurnIndex = (firstTurnIndex+1)%(game.getUserList().size());

        if(firstTurnIndex == 0 && !firstTurn){
            serverHandler.sendMessageToPlayer(game.getUserList().getFirst(),
                    new FirstTurn(
                            ServerHandler.HOSTNAME,
                            true,
                            availableToken,
                            getPlayerByUsername(game.getUserList().getFirst()).getSelObjectiveCard()));

            firstTurn = true;
        } else if (firstTurnIndex != 0){
            serverHandler.sendMessageToPlayer(game.getUserList().get(firstTurnIndex),
                    new FirstTurn(
                            ServerHandler.HOSTNAME,
                            false,
                            availableToken,
                            getPlayerByUsername(game.getUserList().get(firstTurnIndex)).getSelObjectiveCard()));

            if(firstTurnIndex == game.getPlayersNumber()-1){
                firstTurn = false;
            }
        }

    }

    public void endTurn(){
        Player currPlayer = game.getPlayerList().get(currPlayerIndex);

        // calcolare punti e vedere se deve iniziare l'ultimo turno
        if(game.getGameState().equals(gameStateEnum.START) &&
                (currPlayer.getPlayerPoints() >= 20 ||
                        game.getResourceDeck().isEmpty() ||
                        game.getGoldDeck().isEmpty())) {
            finalPlayerIndex = currPlayerIndex;
            game.setGameState(gameStateEnum.FINAL_TURN);
        }

        // salvare il gioco

        // inviare messaggio fine turno
        serverHandler.sendMessageToPlayer(game.getCurrentPlayer().getPlayerName(), new WaitTurnMsg(ServerHandler.HOSTNAME));


        beginTurn();
    }
    public void chatUpdate(String username, String destination, String chat){
        if(Objects.equals(destination, "all")) {
            for (Player p : game.getPlayerList()) {
                p.getChat().add(new Chat(username,chat));
                serverHandler.sendMessageToPlayer(p.getPlayerName(), new ChatResponse(serverHandler.HOSTNAME,p.getChat()));
            }
        }

    }



    public void endGame(){
        Player player = null;
        int maxPoints = 0;
        for (int i = 0; i < game.getUserList().size(); i++) {
            EndgameManager endgameManager = new EndgameManager(game, game.getPlayerList().get(i));
            game.getPlayerList().get(i).addPoints(endgameManager.objectivePointsCounter());

            if (game.getPlayerList().get(i).getPoints() > maxPoints) {
                maxPoints = game.getPlayerList().get(i).getPoints();
                player = game.getPlayerList().get(i);
            }
        }
        if (player != null) {
            serverHandler.sendMessageToPlayer(player.getPlayerName(), new ShowWinnerMessage(ServerHandler.HOSTNAME, true));
            serverHandler.sendMessageToAllExcept(player.getPlayerName(), new ShowWinnerMessage(ServerHandler.HOSTNAME, false));

        }


    }

    public void gameStarting(){
        gameSetUpper.gameSetUp();

        // Initial and final player index is set at -1
        currPlayerIndex = -1;
        finalPlayerIndex = -1;
        firstTurnIndex = -1;
        firstTurn = false;

    }


    public Player getPlayerByUsername(String username) {
        for (Player player : game.getPlayerList()) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }


    public void drawCard(int cardIndex) {

        Card card = cardSelector(cardIndex);
        if (card instanceof ResourceCard) {
            game.getCurrentPlayer().addResourceCard((ResourceCard) card);
        } else if (card instanceof GoldCard) {
            game.getCurrentPlayer().addGoldCard((GoldCard) card);
        }
    }
    public Card cardSelector(int cardIndex) {
        return switch (cardIndex - 1) {
            case 0 -> game.drawFromVisible(0, "gold");
            case 1 -> game.drawFromVisible(1, "gold");
            case 2 -> game.drawFromVisible(0, "resource");
            case 3 -> game.drawFromVisible(1, "resource");
            case 4 -> game.drawGoldCard();
            case 5 -> game.drawResourceCard();
            default -> null;
        };
    }

    public List<Card> getUncoveredCards(){
        List<Card> uncoveredCards = new ArrayList<>();
        uncoveredCards.addAll(game.getVisibleGoldCards());
        uncoveredCards.addAll(game.getVisibleResourceCards());
        return uncoveredCards;
    }


    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void selectionCard(Card card, int x, int y, boolean side){

        try {
            game.getCurrentPlayer().removeCardFromHand(card);
            playCard(card, x, y, side);
            if (card instanceof ResourceCard) {
                game.getCurrentPlayer().addPoints(((ResourceCard) card).getCardPoints());
            }
            else if (card instanceof GoldCard goldCard) {
                game.getCurrentPlayer().addPoints(goldCard.getCardPoints());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void middleTurn() {
        serverHandler.sendMessageToPlayer(game.getCurrentPlayer().getPlayerName(),
                new DrawCardRequest(ServerHandler.HOSTNAME, game.getVisibleGoldCards(), game.getVisibleResourceCards()));
    }


    //utilities
    public void playCard(Card card, int x, int y, boolean side) {
        //assigns a value to the direction the card is facing
        card.setFrontSide(side);

        game.getCurrentPlayer().getGameboard().setGameboardXY(x, y, card);
        game.getCurrentPlayer().getGameboard().setCheckboardXY(x, y, 1);
        card.addResources(game.getCurrentPlayer());

        updateBoxes(card, x, y, side);
        updatePlayerResources(x, y, game.getCurrentPlayer().getGameboard().getGameboard());
    }



    private void updateBoxes(Card card, int x, int y, boolean side) {
        int[][] checkBoard = game.getCurrentPlayer().getGameboard().getCheckboard().clone();

        updateCheckBoard(checkBoard, x + 1, y + 1, side ? card.getFrontVisibleAngle(3) : null);
        updateCheckBoard(checkBoard, x + 1, y - 1, side ? card.getFrontVisibleAngle(1) : null);
        updateCheckBoard(checkBoard, x - 1, y + 1, side ? card.getFrontVisibleAngle(0) : null);
        updateCheckBoard(checkBoard, x - 1, y - 1, side ? card.getFrontVisibleAngle(2) : null);

        game.getCurrentPlayer().getGameboard().setCheckboard(checkBoard);  // Ensure the updated checkBoard is set back to the player
    }

    private void updateCheckBoard(int[][] checkBoard, int x, int y, VisibleAngle angle) {
        if (checkBoard[x][y] != 1 && angle != null) {
            checkBoard[x][y] = 0;
        }
    }
    private void updatePlayerResources(int x, int y, Card[][] cardBoard) {

        int[] resources = game.getCurrentPlayer().getResourcesAvailable();
        VisibleAngle coveredAngle = null;

        if (cardBoard[x+1][y+1] != null) {
            boolean front = cardBoard[x+1][y+1].getSide();
            if (front) {
                coveredAngle = cardBoard[x+1][y+1].getFrontVisibleAngle(0);
            }
        }

        if (cardBoard[x+1][y-1] != null) {
            boolean front = cardBoard[x+1][y-1].getSide();
            if (front) {
                coveredAngle = cardBoard[x+1][y+1].getFrontVisibleAngle(2);
            }
        }

        if (cardBoard[x-1][y+1] != null) {
            boolean front = cardBoard[x-1][y+1].getSide();
            if (front) {
                coveredAngle = cardBoard[x-1][y+1].getFrontVisibleAngle(1);
            }
        }

        if (cardBoard[x-1][y-1] != null) {
            boolean front = cardBoard[x-1][y-1].getSide();
            if (front) {
                coveredAngle = cardBoard[x-1][y-1].getFrontVisibleAngle(3);
            }
        }

        if (coveredAngle != null) {
            game.getCurrentPlayer().resourceLowering(coveredAngle.getSymbol());
        }

    }
}
