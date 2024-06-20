package it.polimi.ingsw.server.model;


import java.util.List;

public class ResourceCard extends Card{
    //class representing the resource cards by now onl with a name
    private final int cardNumber;

    //points the card gives when played
    private final int cardPoints;

    /**
     * Constructs a ResourceCard object with specified attributes.
     *
     * @param number the number of the resource card
     * @param points the points awarded by the resource card
     * @param frontAngles array of VisibleAngle objects representing front angles of the card
     * @param backAngles array of VisibleAngle objects representing back angles of the card
     * @param backSymbol list of Symbol objects for the back side of the card
     * @param frontPath the file path to the front side image of the card
     * @param backPath the file path to the back side image of the card
     */
    public ResourceCard(int number, int points, VisibleAngle[] frontAngles, VisibleAngle[] backAngles, List<Symbol> backSymbol, String frontPath, String backPath){
        super(frontAngles, backAngles, backSymbol, frontPath, backPath);
        this.cardNumber = number;
        this.cardPoints = points;
    }

    public int getCardPoints(){
        return cardPoints;
    }

    public int getCardNumber() {
        return cardNumber;
    }
}
