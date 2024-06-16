package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;
import it.polimi.ingsw.server.saveHandlig.SaveData;

import java.util.*;
public class Game {

    public gameStateEnum gameState;
    //List of the players
    List<Player> playerList = new ArrayList<>();
    List<String> userList = new ArrayList<>();
    private int playersNumber;

    private List<List<Chat>> playersChats = new ArrayList<>();
    //deck of resource cards (common to every player)
    private final List<ResourceCard> resourceDeck = new ArrayList<>();

    //Deck of gold cards (common to every player)
    private final List<GoldCard> goldDeck = new ArrayList<>();

    //deck of objective cards
    private final List<ObjectiveCard> objectiveDeck = new ArrayList<>();

    //deck of initial cards
    private final List<InitialCard> initialCardsDeck = new ArrayList<>();

    //The place where discovered cards are stored = common game boards
    private CommonBoard commonBoard;

    private List<String> availableTokens;

    //The player is at that moment playing the game
    private Player currentPlayer;
    public List<Card> visibleResourceCards = new ArrayList<>();
    public List<Card> visibleGoldCards = new ArrayList<>();

    private List<ObjectiveCard> commonObjectives = new ArrayList<>();

    /*
    private void loadGame(SaveData existingGame){
        gameState = existingGame.getState();
        playersNumber = existingGame.getPlayersNum();
        userList = existingGame.getUserList();
    }
     */

    // Constructor to create the game
    public Game(int playersNumber){
        this.playersNumber = playersNumber;
    }


    //Add a card to the resource deck
    public void addResourceCardToDeck(ResourceCard card){
        resourceDeck.add(card);
    }

    //Get of the first card of the resource deck (aka draw)
    public ResourceCard drawResourceCard(){
        return resourceDeck.removeFirst();
    }

    //Get all the list of the resource cards
    public List<ResourceCard> getResourceDeck() {
        return resourceDeck;
    }


    //Add a card to the gold deck
    public void addGoldCardToDeck(GoldCard card){
        goldDeck.add(card);
    }

    //Get of the first card of the resource deck (aka draw)
    public GoldCard drawGoldCard(){
        return goldDeck.removeFirst();
    }

    //Get all the list of the resource cards
    public List<GoldCard> getGoldDeck() {
        return goldDeck;
    }



    //Add a card to the gold deck
    public void addObjectiveCardToDeck(ObjectiveCard card){
        objectiveDeck.add(card);
    }

    //Get of the first card of the resource deck (aka draw)
    public ObjectiveCard drawObjectiveCard(){
        return objectiveDeck.removeFirst();
    }

    //Get all the list of the resource cards
    public List<ObjectiveCard> getObjectiveDeck() {
        return objectiveDeck;
    }



    //Add a card to the Initial deck
    public void addInitialCardToDeck(InitialCard card){
        initialCardsDeck.add(card);
    }

    //Get of the first card of the Initial deck (aka draw)
    public InitialCard drawInitialCard(){
        return initialCardsDeck.removeFirst();
    }

    //Get all the list of the Initial cards
    public List<InitialCard> getInitialDeck() {
        return initialCardsDeck;
    }



    //Method to add a player to the game
    public void addPlayer(Player player) throws IllegalStateException {
        if (playerList.size() < playersNumber) {
            playerList.add(player);
            userList.add(player.getPlayerName());
            playersChats.add(player.getChat());
            if (playerList.size() == playersNumber) {
                gameState = gameStateEnum.START;
            }

        }
        else throw new IllegalStateException("The game is full");
    }

    //Method to get the list of the players
    public List<Player> getPlayerList() {
        return playerList;
    }

    //set the common board
    public void setCommonBoard(CommonBoard commonBoard){
        this.commonBoard = commonBoard;
    }

    //get the common board
    public CommonBoard getCommonBoard(){
        return commonBoard;
    }

    //setter of current player
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    //getter of current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public Player getNextPlayer(){int index = playerList.indexOf(getCurrentPlayer());
    return playerList.get(index+1);
    }


    public int getResourceDeckSize() {
        return this.resourceDeck.size();
    }

    public int getGoldDeckSize() {
        return this.goldDeck.size();
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public void setGameState(gameStateEnum gameState) {
        this.gameState = gameState;
    }

    public gameStateEnum getGameState() {
        return gameState;
    }

    public void drawUncoveredCards() {
        for (int i = 0; i < 2; i++) {
            if(goldDeck.isEmpty() || resourceDeck.isEmpty() || objectiveDeck.isEmpty()){
                System.out.println("One of the decks is empty");
                return;
            }
            visibleGoldCards.add(drawGoldCard());
            visibleResourceCards.add(drawResourceCard());
            commonObjectives.add(drawObjectiveCard());


        }
    }

    public List<Card> getVisibleResourceCards() {
        return visibleResourceCards;
    }

    public List<Card> getVisibleGoldCards() {
        return visibleGoldCards;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public List<String> getUserList() {
        return userList;
    }

    public List<String> getAvailableTokens() {
        return availableTokens;
    }

    public void setAvailableTokens(List<String> availableTokens) {
        this.availableTokens = availableTokens;
    }

    public void removeAvailableTokens(String toRemove){
        this.availableTokens.remove(toRemove);
    }

    public Card drawFromVisible(int i, String card){
        if (Objects.equals(card, "gold")) {
            visibleGoldCards.addLast(drawGoldCard());
            return visibleGoldCards.remove(i);
        }
        if (Objects.equals(card, "resource")) {
            visibleResourceCards.addLast(drawResourceCard());
            return visibleResourceCards.remove(i);
        }
        return null;
    }

    public List<List<Chat>> getPlayersChats() {
        return playersChats;
    }

    public void setPlayersChats(List<List<Chat>> playersChats) {
        this.playersChats = playersChats;
    }

    public List<ObjectiveCard> getCommonObjectives() {
        return commonObjectives;
    }

    public void setCommonObjectives(List<ObjectiveCard> commonObjectives) {
        this.commonObjectives = commonObjectives;
    }
}
