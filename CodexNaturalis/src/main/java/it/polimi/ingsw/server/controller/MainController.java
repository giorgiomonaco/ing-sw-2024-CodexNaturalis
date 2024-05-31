package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.network.message.allMessages.*;
import it.polimi.ingsw.server.ServerHandler;
import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

import java.util.ArrayList;
import java.util.List;

public class MainController {
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
                                    (game.getPlayersNumber()-game.getPlayerList().size()) +
                                    " player/s to join...",
                            username));
            serverHandler.sendMessageToPlayer(username,
                    new LobbyCreation(ServerHandler.HOSTNAME,
                            "You joined the lobby!\nWaiting for the other " +
                                    (game.getPlayersNumber()-game.getPlayerList().size()) +
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

    public void beginTurn(){
        currPlayerIndex = (currPlayerIndex+1)%(game.getUserList().size());
        Player p = game.getPlayerList().get(currPlayerIndex);
        game.setCurrentPlayer(p);

        if(game.getGameState().equals(gameStateEnum.FINAL_TURN) && currPlayerIndex == finalPlayerIndex){
            endGame();
        }
        else {
            game.setCurrentPlayer(p);
            serverHandler.sendMessageToPlayer(game.getUserList().get(currPlayerIndex),
                    new PlayCardReq(ServerHandler.HOSTNAME, p.getPlayerHand(),p.getGameboard(), p.getResourcesAvailable()));
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

    public void endGame(){

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
        Player p = game.getCurrentPlayer();
        Card card = cardSelector(cardIndex);
        if (card instanceof ResourceCard) {
            p.addResourceCard((ResourceCard) card);
        } else if (card instanceof GoldCard) {
            p.addGoldCard((GoldCard) card);
        }
    }
    public Card cardSelector(int cardIndex) {
        return switch (cardIndex -1) {
            case 0 -> game.drawVisibleGoldCard(0);
            case 1 -> game.drawVisibleGoldCard(1);
            case 2 -> game.drawVisibleResourceCard(0);
            case 3 -> game.drawVisibleResourceCard(1);
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

    public void playCard(Player p, Card card, int x, int y, boolean side){
        PlayCardManager playCardManager = new PlayCardManager(game, game.getCurrentPlayer());

        if (card instanceof ResourceCard) {
            p.removeResourceCardFromHand((ResourceCard) card);
            playCardManager.playCard(card, x, y, side);
            game.getCurrentPlayer().addPoints(((ResourceCard) card).getCardPoints());

        } else if (card instanceof GoldCard goldCard) {
            p.removeGoldCardFromHand(goldCard);
            playCardManager.playCard(card, x, y, side);
            game.getCurrentPlayer().addPoints(goldCard.getCardPoints());
        }
    }

    public void middleTurn() {
        Player p = game.getCurrentPlayer();
        serverHandler.sendMessageToPlayer(p.getPlayerName(),
                new DrawCardRequest(ServerHandler.HOSTNAME, game.getVisibleGoldCards(), game.getVisibleResourceCards()));
    }
}
