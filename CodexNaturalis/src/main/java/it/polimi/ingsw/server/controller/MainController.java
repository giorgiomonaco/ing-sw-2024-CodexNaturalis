package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.server.ServerHandler;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * The main controller has to start the view as soon as the game is created
 * then has to process every response from the view and use the managers to
 * modify the state of the game and use the managers to modify everything
 */
public class MainController implements Serializable {
    private Game game;
    private final ServerHandler serverHandler;
    private GameSetUpper gameSetUpper;

    /**
     * Index of the current player
     */
    private int currPlayerIndex;

    /**
     * Index of the first player to reach 21 points
     */
    private int finalPlayerIndex;

    /**
     * If it's the first turn the players have to choose the token and the personal objective cards.
     */
    private int firstTurnIndex;
    private boolean firstTurn;


    /**
     * Constructor
     * @param serverHandler is assigned to the controller
     */
    public MainController(ServerHandler serverHandler){
        this.serverHandler =  serverHandler;
    }


    /**
     * Sets up the game after the first player logs in.
     *
     * @param username The name of the player.
     * @param numOfPlayers The number of players selected.
     *
     *
     *
     * @see GameSetUpper
     * Creation of game objects, such as cards, is handled in GameSetUpper
     */
    public void gameCreation(String username, int numOfPlayers){

        game = new Game(numOfPlayers);
        firstTurn = true;
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
                                game.getPlayerList().get(i).getInitialCard()));


            }

            beginFirstTurn();
        }
    }

    public void beginTurn() {

        if (game.getUserList().isEmpty()) {
            throw new IllegalStateException("User list is empty. Cannot begin turn.");
        }

        // Skip the turn of the disconnected players until I find a connected one.
        do {
            currPlayerIndex = (currPlayerIndex + 1) % game.getUserList().size();
            game.setCurrentPlayer(game.getPlayerList().get(currPlayerIndex));
        } while (!game.getCurrentPlayer().isConnected());

        if (game.getGameState().equals(gameStateEnum.FINAL_TURN) && currPlayerIndex == finalPlayerIndex) {
            endGame();
        } else {
            serverHandler.sendMessageToPlayer(
                    game.getCurrentPlayer().getPlayerName(),
                    new PlayCardReq(ServerHandler.HOSTNAME,
                            game.getCurrentPlayer().getPlayerHand(),
                            game.getCurrentPlayer().getGameBoards(),
                            game.getCurrentPlayer().getResourcesAvailable(),
                            game.getCurrentPlayer().getPlayerPoints())
            );
        }
    }

    public void beginFirstTurn(){

        List<String> availableToken = game.getAvailableTokens();

        // Skip the turn of the disconnected players until I find a connected one or the first turn is ended.
        do {
            firstTurnIndex = (firstTurnIndex + 1) % (game.getUserList().size());
            game.setCurrentPlayer(game.getPlayerList().get(firstTurnIndex));
        } while (!game.getCurrentPlayer().isConnected() &&
                !(firstTurnIndex == game.getPlayersNumber()-1));

        if(firstTurnIndex == 0){
            serverHandler.sendMessageToPlayer(game.getUserList().getFirst(),
                    new FirstTurn(
                            ServerHandler.HOSTNAME,
                            true,
                            availableToken,
                            getPlayerByUsername(game.getUserList().getFirst()).getSelObjectiveCard()));

        } else {
            serverHandler.sendMessageToPlayer(game.getUserList().get(firstTurnIndex),
                    new FirstTurn(
                            ServerHandler.HOSTNAME,
                            false,
                            availableToken,
                            getPlayerByUsername(game.getUserList().get(firstTurnIndex)).getSelObjectiveCard()));
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

        // inviare messaggio fine turno
        serverHandler.sendMessageToPlayer(game.getCurrentPlayer().getPlayerName(), new WaitTurnMsg(ServerHandler.HOSTNAME));


        beginTurn();
    }

    public void chatUpdate(String username, String destination, String chat){
        if(Objects.equals(destination, "all")) {
            for (Player p : game.getPlayerList()) {
                p.getChat().add(new Chat(username,chat,false, "all"));
                serverHandler.sendMessageToPlayer(p.getPlayerName(), new ChatResponse(ServerHandler.HOSTNAME,p.getChat()));
            }
        }
        else {
            for (Player p : game.getPlayerList()){
                if(Objects.equals(p.getPlayerName(), destination)){
                    p.getChat().add(new Chat(username,chat,true, destination));
                    getPlayerByUsername(username).getChat().add(new Chat(username, chat,true, destination));
                    serverHandler.sendMessageToPlayer(p.getPlayerName(), new ChatResponse(ServerHandler.HOSTNAME,p.getChat()));
                    serverHandler.sendMessageToPlayer(username, new ChatResponse(ServerHandler.HOSTNAME,getPlayerByUsername(username).getChat()));
                }
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
            System.out.println(Colors.greenColor + "THE WINNER IS " + player.getPlayerName() + Colors.resetColor);
            System.exit(1);
        }


    }

    public void gameStarting(){
        gameSetUpper.gameSetUp();

        // Initial and final player index is set at -1
        currPlayerIndex = -1;
        finalPlayerIndex = -1;
        firstTurnIndex = -1;

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




    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void selectionCard(Card card, int x, int y, boolean side){

        game.getCurrentPlayer().removeCardFromHand(card);
        playCard(card, x, y, side);
        if (card instanceof ResourceCard) {
            game.getCurrentPlayer().addPoints(((ResourceCard) card).getCardPoints());
        }
        else if (card instanceof GoldCard goldCard) {
            //checks all the possible conditions for getting points
            game.getCurrentPlayer().addGoldCardPoints(goldCard, x, y);
        }

    }


    public void middleTurn() {
        if(!game.getResourceDeck().isEmpty() && !game.getGoldDeck().isEmpty()) {
            serverHandler.sendMessageToPlayer(game.getCurrentPlayer().getPlayerName(),
                    new DrawCardRequest(ServerHandler.HOSTNAME, game.getVisibleGoldCards(), game.getVisibleResourceCards(), game.getGoldDeck().getFirst().getBackImage(), game.getResourceDeck().getFirst().getBackImage()));
        } else if(game.getResourceDeck().isEmpty()) {
            serverHandler.sendMessageToPlayer(game.getCurrentPlayer().getPlayerName(),
                    new DrawCardRequest(ServerHandler.HOSTNAME, game.getVisibleGoldCards(), game.getVisibleResourceCards(), game.getGoldDeck().getFirst().getBackImage(), null));
        } else {
            serverHandler.sendMessageToPlayer(game.getCurrentPlayer().getPlayerName(),
                    new DrawCardRequest(ServerHandler.HOSTNAME, game.getVisibleGoldCards(), game.getVisibleResourceCards(), null, game.getResourceDeck().getFirst().getBackImage()));
        }
    }


    //utilities
    public void playCard(Card card, int x, int y, boolean side) {
        //assigns a value to the direction the card is facing
        card.setFrontSide(side);

        game.getCurrentPlayer().getGameBoards().setGameBoardXY(x, y, card);
        game.getCurrentPlayer().getGameBoards().setCheckBoardXY(x, y, 1);
        card.addResources(game.getCurrentPlayer());

        updateBoxes(card, x, y, side);
        updatePlayerResources(x, y, game.getCurrentPlayer().getGameBoards().getGameBoard());
    }



    // Update the boxes on the checkBoard based on the card's position and visibility
    private void updateBoxes(Card card, int x, int y, boolean side) {
        // Clone the current player's checkBoard to avoid direct modification
        int[][] original = game.getCurrentPlayer().getGameBoards().getCheckBoard();
        int[][] checkBoard = original.clone();
        // Update the checkBoard positions based on the card's visible angles and the side flag
        if (side) {
            updateCheckBoard(checkBoard, x + 1, y + 1, card.getFrontVisibleAngle(3));
            updateCheckBoard(checkBoard, x + 1, y - 1, card.getFrontVisibleAngle(1));
            updateCheckBoard(checkBoard, x - 1, y + 1, card.getFrontVisibleAngle(2));
            updateCheckBoard(checkBoard, x - 1, y - 1, card.getFrontVisibleAngle(0));
        } else {
            updateCheckBoard(checkBoard, x + 1, y + 1, card.getBackVisibleAngle(3));
            updateCheckBoard(checkBoard, x + 1, y - 1, card.getBackVisibleAngle(1));
            updateCheckBoard(checkBoard, x - 1, y + 1, card.getBackVisibleAngle(2));
            updateCheckBoard(checkBoard, x - 1, y - 1, card.getBackVisibleAngle(0));
        }

        // Ensure the updated checkBoard is set back to the player
        game.getCurrentPlayer().getGameBoards().setCheckBoard(checkBoard);
    }

    private void updateCheckBoard(int[][] checkBoard, int x, int y, VisibleAngle angle) {
        // Ensure the coordinates are within bounds
        if (x >= 0 && x < checkBoard.length && y >= 0 && y < checkBoard[0].length) {
            // Update the checkBoard position based on the angle and existing value
            if (checkBoard[x][y] == -1 && angle != null) {
                checkBoard[x][y] = 0;
            }
            else if(checkBoard[x][y] != 1 && angle == null) {
                checkBoard[x][y] = -2;
            }
        }
    }

    /**
     * Updates the player's resources based on the visible angles of the cards on the checkBoard.
     * This function checks the front visible angles of neighboring cards and updates the player's resources accordingly.
     *
     * @param x The x-coordinate of the player's position.
     * @param y The y-coordinate of the player's position.
     * @param cardBoard The 2D array representing the checkBoard.
     */
    private void updatePlayerResources(int x, int y, Card[][] cardBoard) {

        // List to store the covered angles by neighboring cards
        List<VisibleAngle> coveredAngle = new ArrayList<>();

        // Check the front visible angle of the card at (x+1, y+1) if it exists
        if (cardBoard[x + 1][y + 1] != null) {
            boolean front = cardBoard[x + 1][y + 1].getSide();
            if (front) {
                if (cardBoard[x + 1][y + 1].getFrontVisibleAngle(0) != null) {
                    coveredAngle.add(cardBoard[x + 1][y + 1].getFrontVisibleAngle(0));
                }
            }
        }

        // Check the front visible angle of the card at (x+1, y-1) if it exists
        if (cardBoard[x + 1][y - 1] != null) {
            boolean front = cardBoard[x + 1][y - 1].getSide();
            if (front) {
                if (cardBoard[x + 1][y - 1].getFrontVisibleAngle(2) != null) {
                    coveredAngle.add(cardBoard[x + 1][y - 1].getFrontVisibleAngle(2));
                }
            }
        }

        // Check the front visible angle of the card at (x-1, y+1) if it exists
        if (cardBoard[x - 1][y + 1] != null) {
            boolean front = cardBoard[x - 1][y + 1].getSide();
            if (front) {
                if (cardBoard[x - 1][y + 1].getFrontVisibleAngle(1) != null) {
                    coveredAngle.add(cardBoard[x - 1][y + 1].getFrontVisibleAngle(1));
                }
            }
        }

        // Check the front visible angle of the card at (x-1, y-1) if it exists
        if (cardBoard[x - 1][y - 1] != null) {
            boolean front = cardBoard[x - 1][y - 1].getSide();
            if (front) {
                if (cardBoard[x - 1][y - 1].getFrontVisibleAngle(3) != null) {
                    coveredAngle.add(cardBoard[x - 1][y - 1].getFrontVisibleAngle(3));
                }
            }
        }

        // If there are covered angles, lower the player's resources based on the symbols
        if (!coveredAngle.isEmpty()) {
            for (VisibleAngle angle : coveredAngle) {
                game.getCurrentPlayer().resourceLowering(angle.getSymbol());
            }
        }
        coveredAngle.clear(); // Clear the list for future use
    }

    /**
     * Handles the disconnection of a player.
     *
     * @param username the username of the player to disconnect
     */
    public void playerDisconnect(String username) {
        if(!game.getUserList().contains(username)){
            System.out.println(Colors.redColor + "The player named " + username + " wasn't actually playing." + Colors.resetColor);
        } else if (!game.getGameState().equals(gameStateEnum.END)) {
            for(Player p: game.getPlayerList()){
                if(p.getPlayerName().equals(username)){
                    if(p.isConnected()){
                        p.setConnected(false);
                    } else {
                        System.out.println(Colors.redColor + "The player named " + username + " was already disconnected." + Colors.resetColor);
                    }
                    break;
                }
            }
        }
    }
    /**
     * Checks if the given username is the current player and if so, initiates the next turn.
     *
     * @param username The username of the player to check.
     */
    public void checkNextTurnForDisconnection(String username) {
        for(Player p: game.getPlayerList()){
            if(p.getPlayerName().equals(username)){
                if(game.getCurrentPlayer().equals(p)){
                    beginTurn();
                }
                break;
            }
        }
    }

    public boolean isLastPlayer(String username){
        return game.getUserList().indexOf(username) == game.getPlayersNumber() - 1;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }


    public void initialCardSideSelection(boolean b) {
        if (game == null || game.getCurrentPlayer() == null) {
            throw new IllegalArgumentException("Game or current player is null");
        }

        int y = game.getCurrentPlayer().getGameBoards().getMAX_Y();
        int x = game.getCurrentPlayer().getGameBoards().getMAX_X();

        if (game.getCurrentPlayer().getGameBoards().getGameBoard() == null ||
                game.getCurrentPlayer().getGameBoards().getGameBoard()[x / 2] == null ||
                game.getCurrentPlayer().getGameBoards().getGameBoard()[x / 2][y / 2] == null) {
            throw new IllegalArgumentException("Game board or game board cell is null");
        }

        game.getCurrentPlayer().getGameBoards().getGameBoard()[x / 2][y / 2].setFrontSide(b);
        if (game.getCurrentPlayer().getInitialCard() != null) {
            game.getCurrentPlayer().getInitialCard().addResourcesInitCard(game.getCurrentPlayer());
            updateBoxes(game.getCurrentPlayer().getInitialCard(), x / 2, y / 2, b);
        } else {
            throw new IllegalArgumentException("Initial card is null");
        }
    }
}
