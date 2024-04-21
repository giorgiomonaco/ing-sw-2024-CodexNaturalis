package it.polimi.ingsw.model;


public class ResourceCard extends Card{
    //class representing the resource cards by now onl with a name
    private final String cardName;

    //points the card gives when played
    private final int cardPoints;

    //Constructor
    public ResourceCard(String name, int points,
                        Angle[] frontAngles, Angle[] backAngles, Symbol backSymbol){
        super(frontAngles, backAngles, backSymbol);
        this.cardName = name;
        this.cardPoints = points;
    }


}
