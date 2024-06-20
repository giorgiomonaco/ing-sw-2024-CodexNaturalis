package it.polimi.ingsw.server.model;


import java.io.Serializable;

/**
 * Represents a visible angle associated with a Symbol.
 */
public class VisibleAngle implements Serializable {


    private final Symbol symbol;

    //constructor
    public VisibleAngle(Symbol symbol){
        this.symbol = symbol;
    }

    //get of the symbol
    public Symbol getSymbol() {
        return symbol;
    }

}
