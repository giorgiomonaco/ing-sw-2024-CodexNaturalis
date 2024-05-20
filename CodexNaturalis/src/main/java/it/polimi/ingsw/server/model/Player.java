package it.polimi.ingsw.server.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player {
    //Class representing the player
    private Boards Gameboard;
    //Name of the player
    private final String playerName;

    //List of the resource cards the player has in hand
    private final List<ResourceCard> playerResourceCards = new ArrayList<>();

    //List of the gold cards the player got in hand
    private final List<GoldCard> playerGoldCards = new ArrayList<>();

    //List of the cards the player got in hand
    private final List<Card> playerHand = new ArrayList<>();

    //Personal objective card of the player
    private ObjectiveCard playerObjectiveCard;

    //List of the two objective cards from which the player can choose
    private List<ObjectiveCard> selObjectiveCard = new ArrayList<>();

    //Every player has a counter/token
    private Token playerToken;

    private String playerTokenS;

    //The personal game board of the player
    private GameBoard gameBoard;

    private int playerPoints = 0;
    private InitialCard initialCard;

    //List of resources available in this particular moment for this player
    //We may use an enum to rename the indexes, may be way better, will see
    //By now the list is the same of the "CardManager" class
    //Index = type of resource
    //value = how many available
    private final int[] resourcesAvailable = new int[7];


    //Constructor
    public Player(String name) {
        this.playerName = name;
    }

    //Getter of the list of the resource cards now in hand
    public List<ResourceCard> getPlayerResourceCards() {
        return playerResourceCards;
    }

    //Method to add a card to the list of resource cards
    public void addResourceCard(ResourceCard card) {
        playerResourceCards.add(card);
        playerHand.add(card);
    }

    //method to get a card from the list by index
    public ResourceCard getResourceCardFromHand(int index) {
        return playerResourceCards.get(index);
    }

    //method to remove a card from the player hand passing the object
    public void removeResourceCardFromHand(ResourceCard card) {
        playerResourceCards.remove(card);
    }

    //Getter of the list of the gold cards now in hand
    public List<GoldCard> getPlayerGoldCards() {
        return playerGoldCards;
    }


    //Method to add a card to the list of gold cards
    public void addGoldCard(GoldCard card) {
        playerGoldCards.add(card);
        playerHand.add(card);
    }

    //method to get a card from the list by index
    public GoldCard getGoldCardFromHand(int index) {
        return playerGoldCards.get(index);
    }

    //method to remove a card from the player hand passing the object
    public void removeGoldCardFromHand(GoldCard card) {
        playerGoldCards.remove(card);
    }


    //Getter of the list of the obj cards now in hand
    public ObjectiveCard getPlayerObjectiveCard() {
        return playerObjectiveCard;
    }

    //Method to add a card to the list of resource cards
    public void setObjectiveCard(ObjectiveCard card) {
        playerObjectiveCard = card;
    }

    //method to get a card from the list by index
    public ObjectiveCard getObjectiveCardFromHand() {
        return playerObjectiveCard;
    }

    //Method to assign the token to the player
    public void setPlayerToken(Token token) {
        this.playerToken = token;
        //notify the view
    }

    public void setPlayerTokenS(String token) {
        this.playerTokenS = token;
        //notify the view
    }

    //Method to get the color of the player token
    public Token getPlayerToken() {
        return playerToken;
    }

    //Get the player name
    public String getPlayerName() {
        return playerName;
    }

    //associate a game board to the player
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    //get the game board
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    //get the list of resources available
    public int[] getResourcesAvailable() {
        return resourcesAvailable;
    }

    //set the quantity of a specific type
    public void setResource(int index, int quantity) {
        resourcesAvailable[index] = quantity;
    }

    //adder, reset and getter of player points


    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void addPoints(int p) {
        playerPoints += p;
    }

    public void resourceLowering(Symbol s) {
        switch (s.getSymbolName()) {
            case "mushroom":
                this.resourcesAvailable[0]--;
                break;
            case "leaf":
                this.resourcesAvailable[1]--;
                break;
            case "fox":
                this.resourcesAvailable[2]--;
                break;
            case "butterfly":
                this.resourcesAvailable[3]--;
                break;
            case "feather":
                this.resourcesAvailable[4]--;
                break;
            case "bottle":
                this.resourcesAvailable[5]--;
                break;
            case "scroll":
                this.resourcesAvailable[6]--;
                break;
        }

    }

    public void resourceAdding(Symbol s) {
        switch (s.getSymbolName()) {
            case "mushroom":
                this.resourcesAvailable[0]++;
                break;
            case "leaf":
                this.resourcesAvailable[1]++;
                break;
            case "fox":
                this.resourcesAvailable[2]++;
                break;
            case "butterfly":
                this.resourcesAvailable[3]++;
                break;
            case "feather":
                this.resourcesAvailable[4]++;
                break;
            case "bottle":
                this.resourcesAvailable[5]++;
                break;
            case "scroll":
                this.resourcesAvailable[6]++;
                break;
        }
    }

    public InitialCard getInitialCard() {
        return initialCard;
    }

    public void setInitialCard(InitialCard initialCard) {
        this.initialCard = initialCard;
    }

    public Object getUsername() {
        return playerName;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<ObjectiveCard> getSelObjectiveCard() {
        return selObjectiveCard;
    }

    public void addSelObjectiveCard(ObjectiveCard selObjectiveCard) {
        this.selObjectiveCard.add(selObjectiveCard);
    }

    public Boards getGameboard() {
        return Gameboard;
    }

    public void setBoards(Boards gameboard) {
        this.Gameboard = gameboard;
    }
}