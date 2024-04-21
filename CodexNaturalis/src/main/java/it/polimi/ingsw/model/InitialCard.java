package it.polimi.ingsw.model;


public class InitialCard extends Card{
    //By now we have only the name of the card
    private final String cardName;

    //Constructor
    public InitialCard(String name, Angle[] frontAngles, Angle[] backAngles, Symbol backSymbol){
        super(frontAngles,backAngles,backSymbol);
        this.cardName = name;
    }

    //getter of the card name ù
    public String getCardName() {
        return cardName;
    }
}
