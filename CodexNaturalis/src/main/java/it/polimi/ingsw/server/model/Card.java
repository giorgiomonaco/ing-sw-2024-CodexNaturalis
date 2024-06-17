package it.polimi.ingsw.server.model;


import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Symbol> backSymbol = new ArrayList<>();


    //Attribute that defines if we are playing/considering
    //the front or the back of the card
    //if true = front visible
    //if false = back visible
    private boolean frontSide;

    private String frontImage;

    private String backImage;

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


    /*
    Method to cover and angle
    May look to move it from here
     */
    public Symbol coverAngle(int angle) {
        Symbol sym = frontAngles[angle].getSymbol();
        frontAngles[angle] = null;
        return sym;
    }


    public VisibleAngle getFrontVisibleAngle(int position) {
        if (frontAngles[position] != null) {
            return frontAngles[position];
        } else {
            return null;
        }
    }

    public VisibleAngle getBackVisibleAngle(int position) {
        if (backAngles[position] != null) {
            return backAngles[position];
        } else {
            return null;
        }
    }

    //  we add every symbol on the front of the card to the player available resources.
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

    public boolean getSide() {
        return frontSide;
    }


    //getter of the ID of the card
    public int getCardID() {
        return cardID;
    }

    public String getSymbolName() {
        return null;
    }

    ;

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

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }
}

