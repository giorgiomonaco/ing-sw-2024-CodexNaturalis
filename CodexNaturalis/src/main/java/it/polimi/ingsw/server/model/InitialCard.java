package it.polimi.ingsw.server.model;


import java.util.List;

public class InitialCard extends Card{
    //By now we have only the name of the card
    private final String cardName;



    //Constructor
    public InitialCard(String name, VisibleAngle[] frontAngles, VisibleAngle[] backAngles, List<Symbol> backSymbol){
        super( frontAngles, backAngles, backSymbol);
        this.cardName = name;
    }

    //getter of the card name
    public String getCardName() {
        return cardName;
    }
}
