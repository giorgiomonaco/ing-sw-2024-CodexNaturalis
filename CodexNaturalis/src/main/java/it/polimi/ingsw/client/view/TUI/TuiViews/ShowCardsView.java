package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;
import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.server.model.VisibleAngle;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class ShowCardsView implements TuiView, Serializable {
    List<Card> playerHand;

    public void play(List<Card> list) {
        this.playerHand = list;
        for (Card c : list){
            if(c instanceof ResourceCard){
                printResourceCard((ResourceCard) c);
            }
            else if (c instanceof GoldCard)
                printGoldCard((GoldCard) c);
        }
    }

    private void printResourceCard(ResourceCard r) {
        System.out.println("show front or back?\n [1] = front, [2] = back");
        Scanner t= new Scanner(System.in);
        int o = t.nextInt();
        VisibleAngle[] array;
        if (o == 2) { array = r.getBackAngles();}
        else { array = r.getFrontAngles();}
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                String q = r.getBackSymbol().getSymbolName();
                switch (q) {
                    case "leaf":
                        q = "GRE";
                        break;
                    case "mushroom":
                        q = "ORA";
                        break;
                    case "butterfly":
                        q = "PUR";
                        break;
                    case "fox":
                        q = "BLU";
                        break;
                }
                System.out.println("\n||  " + q + "  ||");
            }
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
            if (i == 2) System.out.print(" == = == ");
        }

    }
    private void printGoldCard(GoldCard g) {
        System.out.println("show front or back?\n [1] = front, [2] = back");
        Scanner t= new Scanner(System.in);
        int o = t.nextInt();
        VisibleAngle[] array;
        if (o == 2) { array = g.getBackAngles();}
        else { array = g.getFrontAngles();}
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                String q = g.getBackSymbol().getSymbolName();
                switch (q) {
                    case "leaf":
                        q = "GRE";
                        break;
                    case "mushroom":
                        q = "ORA";
                        break;
                    case "butterfly":
                        q = "PUR";
                        break;
                    case "fox":
                        q = "BLU";
                        break;
                }
                System.out.println("\n||  " + q + "  ||");
            }
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
            int k = g.getCardPoints();
            if (i == 0) System.out.print(" == " + k + " == ");

            if (i == 2) System.out.print(" == ");

            int[] y = g.getNeededSymbols();
            for(int m=0; m<4; m++){
                if (y[m] > 0 && m==0) System.out.println(y[m]+"M-");
                if (y[m] > 0 && m==1) System.out.println(y[m]+"L-");
                if (y[m] > 0 && m==2) System.out.println(y[m]+"F-");
                if (y[m] > 0 && m==3) System.out.println(y[m]+"B-");

            }
            if (i == 2) System.out.print(" == ");
        }

    }


    @Override
    public void play() {


    }
}
