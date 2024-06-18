package it.polimi.ingsw.server.model;


import java.util.List;

public class InitialCard extends Card{
    //By now we have only the name of the card
    private final int cardName;



    //Constructor
    public InitialCard(int name, VisibleAngle[] frontAngles, VisibleAngle[] backAngles, List<Symbol> backSymbol, String front, String back){
        super(frontAngles, backAngles, backSymbol, front, back);
        this.cardName = name;
        setTurn(10);
    }


    //getter of the card name
    public int getCardName() {
        return cardName;
    }

    public void addResourcesInitCard(Player p) {
        if(isFrontSide()){
            for(VisibleAngle angle : getFrontAngles()){
                if (angle != null) {
                    Symbol symbol = angle.getSymbol();
                    if (symbol != null) {
                        p.resourceAdding(symbol);
                    }
                }
            }
        }
        else {
            for(VisibleAngle angle : getBackAngles()){
                if (angle != null) {
                    Symbol symbol = angle.getSymbol();
                    if (symbol != null) {
                        p.resourceAdding(symbol);
                    }
                }
            }
            for (Symbol s: getBackSymbol()){
                p.resourceAdding(s);
            }
        }
    }
}
