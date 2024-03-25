package it.polimi.ingsw.model;

import java.util.List;
import java.util.ArrayList;
public class Player {
    private String colour;
    private int points;
    private List<Card> availableCards;
    private boolean beginner;
    private int[] elements;

    public Player(String colour, boolean beginner, List<Card> cards ) {
        this.elements = new int[7];
        this.points = 0;
        this.colour = colour;
        this.beginner = beginner;
        this.availableCards = cards;
    }

    //methods

    public int getPoints(){
        return this.points;
    }

    public List<Card> getAvailableCards() {
        return availableCards;
    }

    public int[] getElements() {
        return elements;
    }

    public void setBeginner() {
        this.beginner = true;
    }

    public void playCard(Card card){
        availableCards.remove(card);

    }
    public void drawCard(){
        Card tempCard = Deck.drawDeck();
        availableCards.add(tempCard);
    }
}
