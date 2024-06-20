package it.polimi.ingsw.server.model;


import java.util.List;

public class GoldCard extends Card {

    //name of the card if it has one
    private final int cardNumber;
    // Card condition for points (points for each covered VisibleAngle, symbol, or for just placing the card
    private final int condition;
    //number of points the card gives when played
    private final int cardPoints;

    /**
     * Visible symbols needed on the game board to play the card
     * <p>In order:
     * <li>0 = mushroom (res)
     * <li>1 = leaf (res)
     * <li>2 = fox (res)
     * <li>3 = butterfly (res)
     * </p>
     */
    private int[] neededSymbols;


    /**
     * Constructs a GoldCard object with specified attributes.
     *
     * @param name the name of the card,as indexed in the deck
     * @param frontAngles array of VisibleAngle objects representing front angles of the card
     * @param backAngles array of VisibleAngle objects representing back angles of the card
     * @param backSymbol list of Symbol objects for the back side of the card
     * @param condition the resources needed to play the card
     * @param cardPoints the points value of the card
     * @param neededSymbols array of integers representing symbols needed to play the card
     * @param front the front side image path
     * @param back the back side image path
     */

    public GoldCard(int name, VisibleAngle[] frontAngles, VisibleAngle[] backAngles, List<Symbol> backSymbol, int condition,
                    int cardPoints, int[] neededSymbols, String front, String back){
        super(frontAngles, backAngles, backSymbol, front, back);
        //we assign the name of the card
        this.cardNumber = name;
        this.condition = condition;
        //We assign the value of the card points
        this.cardPoints = cardPoints;
        //we assign the symbols needed to play the card
        this.neededSymbols = neededSymbols;
    }

    /**
     * Retrieves the number of the card.
     *
     * @return the number of the card
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * Retrieves the array of symbols needed to play the card.
     *
     * @return an array of integers representing symbols needed to play the card
     */
    public int[] getNeededSymbols() {
        return neededSymbols;
    }

    /**
     * Retrieves the points value of the card when played.
     *
     * @return the points value of the card
     */
    public int getCardPoints() {
        return cardPoints;
    }

    /**
     * Retrieves the condition of the card.
     *
     * @return the condition of the card
     */
    public int getCondition() {
        return condition;
    }



}
