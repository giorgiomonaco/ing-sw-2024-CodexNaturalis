package it.polimi.ingsw.server.model;

import java.util.List;

public class InitialCard extends Card {

    private final int cardName;

    /**
     * Constructs an InitialCard object with specified attributes.
     *
     * @param name the number of the card
     * @param frontAngles array of VisibleAngle objects representing front angles of the card
     * @param backAngles array of VisibleAngle objects representing back angles of the card
     * @param backSymbol list of Symbol objects for the back side of the card
     * @param front the front side image path
     * @param back the back side image path
     */
    public InitialCard(int name, VisibleAngle[] frontAngles, VisibleAngle[] backAngles, List<Symbol> backSymbol, String front, String back) {
        super(frontAngles, backAngles, backSymbol, front, back);
        this.cardName = name;
        setTurn(10);
    }

    /**
     * Retrieves the number of the card.
     *
     * @return the number of the card
     */
    public int getCardName() {
        return cardName;
    }

    /**
     * Adds resources to a player based on the current state of the InitialCard.
     *
     * @param p the Player object to which resources are added
     */
    public void addResourcesInitCard(Player p) {
        if(isFrontSide()) {
            for(VisibleAngle angle : getFrontAngles()) {
                if (angle != null) {
                    Symbol symbol = angle.getSymbol();
                    if (symbol != null) {
                        p.resourceAdding(symbol);
                    }
                }
            }
        }
        else {
            for (VisibleAngle angle : getBackAngles()) {
                if (angle != null) {
                    Symbol symbol = angle.getSymbol();
                    if (symbol != null) {
                        p.resourceAdding(symbol);
                    }
                }
            }
            for (Symbol s : getBackSymbol()) {
                p.resourceAdding(s);
            }
        }
    }
}
