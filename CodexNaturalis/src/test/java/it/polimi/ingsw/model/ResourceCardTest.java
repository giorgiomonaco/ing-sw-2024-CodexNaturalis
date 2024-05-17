package it.polimi.ingsw.model;

import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.server.model.ResourceSymbol;
import it.polimi.ingsw.server.model.Symbol;
import it.polimi.ingsw.server.model.VisibleAngle;

public class ResourceCardTest {
    private ResourceCard r;

    @org.junit.Test
    public void testSomething() {
        Symbol s1 = new ResourceSymbol("mushroom", "resources");
        VisibleAngle[] resCardBackAngles = new VisibleAngle[4];
        resCardBackAngles[0] = new VisibleAngle(s1);
        resCardBackAngles[1] = new VisibleAngle(null);
        resCardBackAngles[2] = new VisibleAngle(null);
        resCardBackAngles[3] = new VisibleAngle(null);
        VisibleAngle[] resCardFrontAngles = new VisibleAngle[4];
        resCardFrontAngles[0] = null;
        resCardFrontAngles[1] = new VisibleAngle(s1);
        resCardFrontAngles[2] = new VisibleAngle(null);
        resCardFrontAngles[3] = new VisibleAngle(null);

        r = new ResourceCard(10, 3, resCardFrontAngles, resCardBackAngles, s1);


        r.setFrontSide(false);
        VisibleAngle[] array = r.getBackAngles();
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                String q = r.getBackSymbol().getSymbolName();
                switch (q){
                    case "leaf": q = "GRE"; break;
                    case "mushroom": q = "ORA"; break;
                    case "butterfly": q = "PUR"; break;
                    case "fox": q = "BLU"; break;
                }
                System.out.println("\n||  "+q+"  ||");}
            if (array[i] == null) {
                System.out.print("X");

            } else if (array[i].getSymbol() == null) {

                System.out.print("E");
            } else {
                String s = array[i].getSymbol().getSymbolName();
                switch (s) {
                    case "mushroom":
                        System.out.print("M");
                        break;
                    case "leaf":
                        System.out.print("L");
                        break;
                    case "fox":
                        System.out.print("F");
                        break;
                    case "butterfly":
                        System.out.print("B");
                        break;
                    case "bottle":
                        System.out.print("b");
                        break;
                    case "scroll":
                        System.out.print("s");
                        break;
                    case "feather":
                        System.out.print("f");
                        break;
                }

            }
            int k = r.getCardPoints();
            if (i == 0) System.out.print(" == " + k + " == ");


            if (i == 2) System.out.print(" == "+ k +" == ");
        }


    }
}