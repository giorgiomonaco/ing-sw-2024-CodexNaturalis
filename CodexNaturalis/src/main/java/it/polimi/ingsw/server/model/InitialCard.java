package it.polimi.ingsw.server.model;


import java.util.List;
import java.util.Objects;

public class InitialCard extends Card{
    //By now we have only the name of the card
    private final int cardName;



    //Constructor
    public InitialCard(int name, VisibleAngle[] frontAngles, VisibleAngle[] backAngles, List<Symbol> backSymbol, String front, String back){
        super(frontAngles, backAngles, backSymbol, front, back);
        this.cardName = name;
    }


    //getter of the card name
    public int getCardName() {
        return cardName;
    }
}
