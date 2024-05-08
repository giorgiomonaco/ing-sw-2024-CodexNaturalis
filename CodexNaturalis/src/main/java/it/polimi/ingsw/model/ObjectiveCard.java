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
}
