package it.polimi.ingsw.model;


import java.util.ArrayList;
import java.util.List;

public class GoldCard extends Card {

    //name of the card if it has one
    private final String cardName;
    //number of points the card gives when played
    private final int cardPoints;
    //Visible symbols needed on the game board to play the card
    private final List<Symbol> neededSymbols;

    //Constructor
    public GoldCard(String name, Angle[] frontAngles, Angle[] backAngles, Symbol backSymbol,
                    int cardPoints, List<Symbol> neededSymbols){
        super(frontAngles, backAngles, backSymbol);
        //we assign the name of the card
        this.cardName = name;
        //We assign the value of the card points
        this.cardPoints = cardPoints;
        //we assign the symbols needed to play the card
        this.neededSymbols = neededSymbols;
    }

    //get of the card name
    public String getCardName() {
        return cardName;
    }

    //get the list of all the symbols needed to play the card
    public List<Symbol> getNeededSymbols() {
        return neededSymbols;
    }

    //get how many points the card gives when played
    public int getCardPoints() {
        return cardPoints;
    }
}
