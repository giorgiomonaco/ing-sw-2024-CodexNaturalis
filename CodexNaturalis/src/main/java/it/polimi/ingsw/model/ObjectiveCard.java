package it.polimi.ingsw.model;


import java.util.List;

//manca check durante piazzamento della carta con risorse necessarie e del player
public class ObjectiveCard {
    private final int cardNumber;
    private final int points;
    private final String type;
    private final String card1;
    private final String direction1;
    private final String card2;
    private final String direction2;
    private final String card3;



    public ObjectiveCard(int num, int points, String type, String card1, String direction1, String card2, String direction2, String card3) {
        this.type = type;
        this.card1 = card1;
        this.direction1 = direction1;
        this.card2 = card2;
        this.direction2 = direction2;
        this.card3 = card3;
        this.points = points;
        this.cardNumber = num;
    }

    public int getPoints() {
        return points;
    }

    public String getCard1() {
        return card1;
    }

    public String getCard2() {
        return card2;
    }

    public String getCard3() {
        return card3;
    }

    public String getDirection1() {
        return direction1;
    }

    public String getDirection2() {
        return direction2;
    }

    public String getType() {
        return type;
    }
    public int getCardName(){
        return this.cardNumber;
    }
}
