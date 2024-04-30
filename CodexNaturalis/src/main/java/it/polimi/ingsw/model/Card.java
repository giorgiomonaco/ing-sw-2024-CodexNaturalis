package it.polimi.ingsw.model;


import javax.imageio.plugins.tiff.TIFFField;

public abstract class Card {

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
    private final Symbol backSymbol;

    //Attribute that defines if we are playing/considering
    //the front or the back of the card
    //if true = front visible
    //if false = back visible
    private boolean frontSide;

    //Constructor
    public Card(VisibleAngle[] frontAngles, VisibleAngle[] backAngles, Symbol backSymbol){
        for (int i = 0; i < 4; i++ ){
            this.frontAngles[i] = frontAngles[i];
            this.backAngles[i] = backAngles[i];
        }
        this.backSymbol = backSymbol;
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
    public Symbol getBackSymbol() {
        return backSymbol;
    }



    public VisibleAngle getFrontVisibleAngle(int position){
        if (frontAngles[position] != null){
            return frontAngles[position];
        }
        else
        {
            return null;
        }
    }

    public VisibleAngle getBackVisibleAngle(int position){
        if (backAngles[position] != null){
            return backAngles[position];
        }
        else
        {
            return null;
        }
    }


}
