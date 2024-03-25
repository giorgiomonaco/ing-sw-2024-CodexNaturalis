package it.polimi.ingsw.model;

abstract class Card {
    private String colour;
    private int points;
    public Corner[] visibleCorner; //da 1 a 4, posso istanziare dentro?
    public int CornerIndex;
    public int CardIndex;
    public boolean side;

 // GETTER DI OGNI ATTRIBUTO
    public boolean isSide() {
        return side;
    }

    public String getColour() {
        return colour;
    }

    public int getPoints() {
        return points;
    }

    public Corner[] getVisibleCorner() {
        return visibleCorner;
    }




    public boolean placeCardOnCorner(int CornerIndex, Card CartaSopra, Card CartaSotto){
        if (visibleCorner[CornerIndex] != null && CartaSotto.visibleCorner[CornerIndex].covered==false) {
            CartaSotto.visibleCorner[CornerIndex].covered=true;

            if (CornerIndex == 1){
                CartaSopra.visibleCorner[4].covered=true;
            }
            if (CornerIndex == 2){
                CartaSopra.visibleCorner[3].covered=true;
            }
            if (CornerIndex == 3){
                CartaSopra.visibleCorner[2].covered=true;
            }
            if (CornerIndex == 4){
                CartaSopra.visibleCorner[1].covered=true;
            }


        //    CornerIndex.linkedCard(CartaSopra);


        }
    return true;
    }


}
