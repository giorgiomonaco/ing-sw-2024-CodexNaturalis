package it.polimi.ingsw.server.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    //Class representing the player
    private Boards Gameboard;
    //Name of the player
    private final String playerName;
    //Used to check if the player is connected
    private boolean connected;

    //List of the resource cards the player has in hand
    private final List<ResourceCard> playerResourceCards = new ArrayList<>();

    //List of the gold cards the player got in hand
    private final List<GoldCard> playerGoldCards = new ArrayList<>();

    //List of the cards the player got in hand
    private final List<Card> playerHand = new ArrayList<>();

    //Personal objective card of the player
    private ObjectiveCard playerObjectiveCard;

    //List of the two objective cards from which the player can choose
    private final List<ObjectiveCard> selObjectiveCard = new ArrayList<>();


    private String playerTokenS;

    private List<Chat> chat = new ArrayList<>();

    private int playerPoints = 0;
    private InitialCard initialCard;

    //List of resources available in this particular moment for this player
    //We may use an enum to rename the indexes, may be way better, will see
    //By now the list is the same of the "CardManager" class
    //Index = type of resource
    //value = how many available
    private final int[] resourcesAvailable = new int[7];

    // parameter only needed to stamp correctly the card in gui,
    // we need to save it in the server model for eventual reconnections
    private int turn;


    /**
     * Constructs a Player object with the specified name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.playerName = name;
        this.connected = true;
        this.turn = 2;
    }

    /**
     * Getter of the list of the resource cards now in hand
     */
    public List<ResourceCard> getPlayerResourceCards() {
        return playerResourceCards;
    }

    /**
     * Method to add a card to the list of resource cards
     */
    public void addResourceCard(ResourceCard card) {
        playerResourceCards.add(card);
        playerHand.add(card);
    }



    /**
     * Getter of the list of the gold cards now in hand
     */
    public List<GoldCard> getPlayerGoldCards() {
        return playerGoldCards;
    }


    /**
     * Method to add a card to the list of gold cards
     */
    public void addGoldCard(GoldCard card) {
        playerGoldCards.add(card);
        playerHand.add(card);
    }

    /**
     * method to get a card from the list by index
     */
    public GoldCard getGoldCardFromHand(int index) {
        return playerGoldCards.get(index);
    }

    /**
     * Removes a specified card from the player's hand.
     *
     * @param card the card to be removed from the hand
     */
    public void removeCardFromHand(Card card) {

        if(card instanceof ResourceCard) {
            for (Card c : playerHand) {
                if(c instanceof ResourceCard && ((ResourceCard) c).getCardNumber() == ((ResourceCard) card).getCardNumber()) {
                    playerResourceCards.remove((ResourceCard) c);
                    playerHand.remove(c);
                    return;
                }
            }
        } else {
            for (Card c : playerHand) {
                if(c instanceof GoldCard && ((GoldCard) c).getCardNumber() == ((GoldCard) card).getCardNumber()) {
                    playerGoldCards.remove((GoldCard) c);
                    playerHand.remove(c);
                    return;
                }
            }
        }

        System.out.println("Card not found");

    }

    /**
     * Getter of the list of the obj cards now in hand
     */
    public ObjectiveCard getPlayerObjectiveCard() {
        return playerObjectiveCard;
    }

    /**
     * Method to add a card to the list of resource cards
     */
    public void setObjectiveCard(ObjectiveCard card) {
        playerObjectiveCard = card;
    }




    public void setPlayerTokenS(String token) {
        this.playerTokenS = token;
        //notify the view
    }

    public String getPlayerTokenS() {
        return playerTokenS;
    }



    /**
     * Get the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * get the list of resources available
     */
    public int[] getResourcesAvailable() {
        return resourcesAvailable;
    }

    /**
     * set the quantity of a specific type
     */
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

    /**
     * Decreases the count of a specific resource symbol available to the player.
     *
     * @param s the Symbol object representing the resource symbol to lower
     */
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
            default:
                break;
        }

    }

    /**
     * Increases the count of a specific resource symbol available to the player.
     *
     * @param s the Symbol object representing the resource symbol to increase
     */
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

    public Boards getGameBoards() {
        return Gameboard;
    }

    public void setBoards(Boards gameboard) {
        this.Gameboard = gameboard;
    }

    public int getPoints() {return playerPoints;}

    public List<Chat> getChat() {
        return chat;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }

    /**
     * Adds points to the player based on the conditions of a GoldCard and player resources.
     *
     * @param goldCard the GoldCard object for which points are added
     * @param x the x-coordinate (used for specific conditions)
     * @param y the y-coordinate (used for specific conditions)
     */
    public void addGoldCardPoints(GoldCard goldCard, int x, int y) {
        switch(goldCard.getCondition()){
            case 0:
                addPoints(goldCard.getCardPoints());
                break;
            case 1:
                pointsForCoveredAngle(goldCard, x, y);
                break;
            case 2:
                addPoints(goldCard.getCardPoints() * resourcesAvailable[4]);
            case 3:
                addPoints(goldCard.getCardPoints() * resourcesAvailable[5]);
            case 4:
                addPoints(goldCard.getCardPoints() * resourcesAvailable[6]);
            default:
                System.out.println("Impossible to get card points");


        }
    }


    /**
     * Calculates points based on covered angles around a specified position on the board for a GoldCard.
     *
     * @param goldCard the GoldCard object for which points are calculated
     * @param x the x-coordinate of the position on the board
     * @param y the y-coordinate of the position on the board
     */
    private void pointsForCoveredAngle(GoldCard goldCard, int x, int y) {
        Card[][] board = Gameboard.getGameBoard();
        int count = 0;

        //Check covered angles and keep count
        if(board[x-1][y-1] != null){
            if(board[x-1][y-1].getSide() && board[x-1][y-1].getFrontVisibleAngle(3) != null){
                count++;
            }
            else if(!board[x-1][y-1].getSide()){
                count++;
            }
        }
        if(board[x+1][y-1] != null){
            if(board[x+1][y-1].getSide() && board[x+1][y-1].getFrontVisibleAngle(2) != null){
                count++;
            }
            else if(!board[x+1][y-1].getSide()){
                count++;
            }
        }
        if(board[x-1][y+1] != null){
            if(board[x-1][y+1].getSide() && board[x-1][y+1].getFrontVisibleAngle(1) != null){
                count++;
            }
            else if(!board[x-1][y+1].getSide()){
                count++;
            }
        }
        if(board[x+1][y+1] != null){
            if(board[x+1][y+1].getSide() && board[x+1][y+1].getFrontVisibleAngle(0) != null){
                count++;
            }
            else if(!board[x+1][y+1].getSide()){
                count++;
            }
        }

        addPoints(goldCard.getCardPoints()*count);
    }

    public Boards getGameboard() {
        return Gameboard;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}