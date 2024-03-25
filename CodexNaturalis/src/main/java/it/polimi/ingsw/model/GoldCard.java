package it.polimi.ingsw.model;

public class GoldCard {
    private int[] FixedAttributes;
    public int[] requirements;

    public int[] getFixedAttributes() {
        return FixedAttributes;
    }
    public boolean checkReq(player P){
        for(int i=0;i<6;i++){
            if requirements[i]>P.elements[i]{
                    System.out.println("\nnon hai abbastanza elementi");
                    return false;
            }
        }
        System.out.println("\nhai abbastanza elementi");
        return true;
    }
}
