public class VisibleAngle extends Angle{
    //every visible angle has a symbol in it,
    //It may be null, so the angle is empty
    //!!!!!!!!!!!!!!!!!!Don't like this class cus only subclass of angle,
    //may be useless have a distinction
    private final Symbol symbol;

    //attribute tha determines if the angle is visible or not,
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

    //get if angle covered or not
    public boolean isNotCovered() {
        return notCovered;
    }

    //change the state of the coverage of the angle
    public void setNotCovered(boolean notCovered) {
        this.notCovered = notCovered;
    }
}
