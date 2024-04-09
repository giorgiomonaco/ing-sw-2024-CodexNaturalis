import java.util.*;
public class Game {

    //Class with all the elements necessary to the game

    //List of the players
    List<Player> playerList = new ArrayList<>();

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
        return objectiveDeck.get(0);
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
    public void addPlayer(Player player){
        playerList.add(player);
    }

    //Method to get the list of the players
    public List<Player> getPlayerList() {
        return playerList;
    }

    //set the common board
    public void setCommonBoard(CommonBoard commonBoard){
        this.commonBoard = commonBoard;
    }

    //get f the common board
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
}
