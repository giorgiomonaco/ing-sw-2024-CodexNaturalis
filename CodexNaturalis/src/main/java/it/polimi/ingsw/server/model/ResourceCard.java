package it.polimi.ingsw.server.model;


public class ResourceCard extends Card{
    //class representing the resource cards by now onl with a name
    private final int cardNumber;

    //points the card gives when played
    private final int cardPoints;

    //Constructor
    public ResourceCard(int number, int points, VisibleAngle[] frontAngles, VisibleAngle[] backAngles, Symbol backSymbol){
        super(frontAngles, backAngles, backSymbol);
        this.cardNumber = number;
        this.cardPoints = points;
    }

    public int getCardPoints(){
        return cardPoints;
    }

}
