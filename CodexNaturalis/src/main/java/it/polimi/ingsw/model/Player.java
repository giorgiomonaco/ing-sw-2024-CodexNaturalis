package it.polimi.ingsw.model;

import java.util.List;

public class Player {
    public String colour;
    public int points;
    public boolean beginner;
    public int[] elements;
    private List<Card> availableCards;


    public String getColour() {
        return colour;
    }

    public int getPoints() {
        return points;
    }

    public int[] getElements() {
        return elements;
    }

    public List<Card> getAvailableCards() {
        return availableCards;
    }

    public void setBeginner(boolean beginner) {
        this.beginner = beginner;
    }

    public List<Card> drawCard(Card drawed){
        availableCards = getAvailableCards();
        availableCards.add(drawed);
        return availableCards;
    }

    public List<Card> removeCard(Card removed){
        availableCards = getAvailableCards();
        availableCards.remove(removed);
        return availableCards;
    }


}
