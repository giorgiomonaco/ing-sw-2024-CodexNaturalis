package it.polimi.ingsw.server.model;


import java.io.Serializable;
import java.util.List;

public abstract class Card implements Serializable {

    //ID of the card, may be removed later
    private static int cardID = 0;

    //If the angles array is null => not visible!!!!

    //Array representing the 4 angles in the front side of the card
    //0 = top right
    //1 = bottom right
    //2 = bottom left
    //3 = top left
    private final VisibleAngle[] frontAngles = new VisibleAngle[4];

    //array representing the 4 angles of the back of the card
    //same enumeration
    private final VisibleAngle[] backAngles = new VisibleAngle[4];

    //central symbol on the back of the card
    private final List<Symbol> backSymbol;


    //Attribute that defines if we are playing/considering
    //the front or the back of the card
    //if true = front visible
    //if false = back visible
    private boolean frontSide;

    private final String frontImage;

    private final String backImage;

    private int turn = -1;

    //Constructor
    public Card(VisibleAngle[] frontAngles, VisibleAngle[] backAngles, List<Symbol> backSymbol, String frontPath, String backPath) {
        for (int i = 0; i < 4; i++) {
            this.frontAngles[i] = frontAngles[i];
            this.backAngles[i] = backAngles[i];
            cardID++;
        }
        this.backSymbol = backSymbol;

        this.frontImage = frontPath;
        this.backImage = backPath;
    }


    //get of the front angles
    public VisibleAngle[] getFrontAngles() {
        return frontAngles;
    }

    //get of the back angles
    public VisibleAngle[] getBackAngles() {
        return backAngles;
    }

    //set of the bool var for front or back side
    public void setFrontSide(boolean frontSide) {
        this.frontSide = frontSide;
    }

    //check if it has been played front or back side
    public boolean isFrontSide() {
        return frontSide;
    }

    //getter of the symbol in the back of the card
    public List<Symbol> getBackSymbol() {
        return backSymbol;
    }





    /**
     * Takes a position as an input, checks if there is a visible angle
     * at the specified position in the frontAngles array, and returns it if present.
     * If there is no visible angle at the given position, it returns null.
     *
     * @param position the index position to retrieve the visible angle from, must be within the bounds of the frontAngles array
     * @return the VisibleAngle at the specified position, or null if no visible angle is present
     * @throws ArrayIndexOutOfBoundsException if the position index is out of bounds
     */

    public VisibleAngle getFrontVisibleAngle(int position) {
        if (frontAngles[position] != null) {
            return frontAngles[position];
        } else {
            return null;
        }
    }

    /**
     * Takes a position as an input, checks if there is a visible angle
     * at the specified position in the backAngles array, and returns it if present.
     * If there is no visible angle at the given position, it returns null.
     *
     * @param position the index position to retrieve the visible angle from, must be within the bounds of the backAngles array
     * @return the VisibleAngle at the specified position, or null if no visible angle is present
     * @throws ArrayIndexOutOfBoundsException if the position index is out of bounds
     */
    public VisibleAngle getBackVisibleAngle(int position) {
        if (backAngles[position] != null) {
            return backAngles[position];
        } else {
            return null;
        }
    }

    /**
     * Adds every symbol on the front of the card to the player's available resources.
     * Iterates through the frontAngles array, retrieves the symbol from each non-null
     * VisibleAngle, and adds it to the player's resources using the {@code resourceAdding} method.
     *
     * @param p the Player object to add resources to
     */
    public void addResources(Player p) {
        for (VisibleAngle angle : frontAngles) {
            if (angle != null) {
                Symbol symbol = angle.getSymbol();
                if (symbol != null) {
                    p.resourceAdding(symbol);
                }
            }
        }
    }


    /**
     * Returns the side of the card (front or back).
     *
     * @return true if the card is on the front side, false otherwise
     */
    public boolean getSide() {
        return frontSide;
    }


    /**
     * Getter method for retrieving the ID of the card.
     *
     * @return the ID of the card
     */
    public int getCardID() {
        return cardID;
    }




    /**
     * Checks if two cards are equal based on their card numbers.
     * This method compares two Card objects, 'in' and 'hand', and returns true if:
     * <li> Both 'in' and 'hand' are instances of GoldCard, and their card numbers match, or<br>
     * <li> Both 'in' and 'hand' are instances of ResourceCard, and their card numbers match.
     * <li> Returns false in all other cases, including when 'in' and 'hand' are not of the same type.
     *<br><br>
     * @param in the first Card object to compare
     * @param hand the second Card object to compare
     * @return true if the card numbers of 'in' and 'hand' are equal, false otherwise
     */
    public boolean equals(Card in, Card hand) {
        if (in instanceof GoldCard) {
            if (hand instanceof GoldCard) {
                return ((GoldCard) in).getCardNumber() == ((GoldCard) hand).getCardNumber();
            } else if (hand instanceof ResourceCard) {
                return ((GoldCard) in).getCardNumber() == ((ResourceCard) hand).getCardNumber();
            }
        } else if (in instanceof ResourceCard) {
            if (hand instanceof GoldCard) {
                return ((ResourceCard) in).getCardNumber() == ((GoldCard) hand).getCardNumber();
            } else if (hand instanceof ResourceCard) {
                return ((ResourceCard) in).getCardNumber() == ((ResourceCard) hand).getCardNumber();
            }
        }
        return false;
    }

    /**
     * Retrieves the path or name of the front image associated with this card.
     *
     * @return a String representing the front image of the card
     */
    public String getFrontImage() {
        return frontImage;
    }


    /**
     * Retrieves the path or name of the back image associated with this card.
     *
     * @return a String representing the back image of the card
     */
    public String getBackImage() {
        return backImage;
    }


    /**
     * Retrieves the turn number associated with this card.
     *
     * @return the turn number of the card
     */
    public int getTurn() {
        return turn;
    }



    /**
     * Sets the turn number associated with this card.
     *
     * @param turn the new turn number to set
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }
}

