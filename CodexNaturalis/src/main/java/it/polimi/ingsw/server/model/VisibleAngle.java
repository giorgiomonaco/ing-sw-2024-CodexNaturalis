package it.polimi.ingsw.server.model;


public class VisibleAngle {
    //every visible VisibleAngle has a symbol in it,
    //It may be null, so the VisibleAngle is empty
    //!!!!!!!!!!!!!!!!!!Don't like this class cus only subclass of VisibleAngle,
    //may be useless have a distinction
    private final Symbol symbol;

    //attribute tha determines if the VisibleAngle is visible or not,
    //used to check how and which resources are available
    private boolean notCovered = true;

    //constructor
    public VisibleAngle(Symbol symbol){
        this.symbol = symbol;
    }

    //get of the symbol
    public Symbol getSymbol() {
        return symbol;
    }

    //get if VisibleAngle covered or not
    public boolean isNotCovered() {
        return notCovered;
    }

    //change the state of the coverage of the VisibleAngle
    public void setNotCovered(boolean notCovered) {
        this.notCovered = notCovered;
    }
}
