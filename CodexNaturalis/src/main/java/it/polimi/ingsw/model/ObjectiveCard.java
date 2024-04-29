package it.polimi.ingsw.model;


import java.util.List;

//manca check durante piazzamento della carta con risorse necessarie e del player
public class ObjectiveCard extends Card{

    //Temporary/for working issues name of the card
    private final String cardName;
    //how many points the card gives if we match the conditions
    private final int cardPoints;
    //Conditions for the points
    private final List<Symbol> cardConditions;

    //Constructor
    public ObjectiveCard(String name, int cardPoints, List<Symbol> cardConditions,
                         Angle[] frontAngles, Angle[] backAngles, Symbol backSymbol){
        super(frontAngles,backAngles, backSymbol);
        this.cardName = name;
        this.cardConditions = cardConditions;
        this.cardPoints = cardPoints;
    }

    //Getter of the card name
    public String getCardName() {
        return cardName;
    }

    //Get how many points the card gives when played
    public int getCardPoints() {
        return cardPoints;
    }

    //get the conditions to play the card
    public List<Symbol> getCardConditions() {
        return cardConditions;
    }
}
