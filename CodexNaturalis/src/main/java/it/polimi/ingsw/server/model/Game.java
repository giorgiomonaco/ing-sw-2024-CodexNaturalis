package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.model.gameStateEnum.gameStateEnum;

import java.util.*;
public class Game {

    public gameStateEnum gameState;
    //List of the players
    List<Player> playerList = new ArrayList<>();
    private int playersNumber;

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

    //The player is at that moment playing the game
    private Player currentPlayer;


    //Add a card to the resource deck
    public void addResourceCardToDeck(ResourceCard card){
        resourceDeck.add(card);
    }

    //Get of the first card of the resource deck (aka draw)
    public ResourceCard drawResourceCard(){
        return resourceDeck.get(0);
    }

    //Get all the list of the resource cards
    public List<ResourceCard> getResourceDeck() {
        return resourceDeck;
    }

    //remove a card from the resource deck (passing the card to be removed)
    public void removeFormResourceDeck(ResourceCard card){
        resourceDeck.remove(card);
    }

    //Add a card to the gold deck
    public void addGoldCardToDeck(GoldCard card){
        goldDeck.add(card);
    }

    //Get of the first card of the resource deck (aka draw)
    public GoldCard drawGoldCard(){
        return goldDeck.get(0);
    }

    //Get all the list of the resource cards
    public List<GoldCard> getGoldDeck() {
        return goldDeck;
    }

    //remove a card from the resource deck (passing the card to be removed)
    public void removeFormGoldDeck(GoldCard card){
        goldDeck.remove(card);
    }

    //Add a card to the gold deck
    public void addObjectiveCardToDeck(ObjectiveCard card){
        objectiveDeck.add(card);
    }

    //Get of the first card of the resource deck (aka draw)
    public ObjectiveCard drawObjectiveCard(){
        return objectiveDeck.getFirst();
    }

    //Get all the list of the resource cards
    public List<ObjectiveCard> getObjectiveDeck() {
        return objectiveDeck;
    }

    //remove a card from the resource deck (passing the card to be removed)
    public void removeFormObjectiveDeck(ObjectiveCard card){
        objectiveDeck.remove(card);
    }


    //Add a card to the Initial deck
    public void addInitialCardToDeck(InitialCard card){
        initialCardsDeck.add(card);
    }

    //Get of the first card of the Initial deck (aka draw)
    public InitialCard drawInitialCard(){
        return initialCardsDeck.get(0);
    }

    //Get all the list of the Initial cards
    public List<InitialCard> getInitialDeck() {
        return initialCardsDeck;
    }

    //remove a card from the Initial deck (passing the card to be removed)
    public void removeFormInitialDeck(InitialCard card){
        initialCardsDeck.remove(card);
    }


    //Method to add a player to the game
    public void addPlayer(Player player) throws IllegalStateException {
        if (playerList.size() < playersNumber) {
            playerList.add(player);
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
    public Player getPlayerByUsername(String username) {
        for (Player player : playerList) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }
}
