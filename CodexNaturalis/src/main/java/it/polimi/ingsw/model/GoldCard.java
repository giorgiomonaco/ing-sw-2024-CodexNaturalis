package it.polimi.ingsw.model;

public class GoldCard {
    private int[] FixedAttributes;
    public int[] requirements;

    public int[] getFixedAttributes() {
        return FixedAttributes;
    }
    public boolean checkReq(Player P){
        for(int i=0;i<6;i++){
            boolean b = requirements[i] > P.elements[i];
            if (b) {
                    System.out.println("\nnon hai abbastanza elementi");
                    return false;
            }
        }
        System.out.println("\nhai abbastanza elementi");
        return true;
    }
}
