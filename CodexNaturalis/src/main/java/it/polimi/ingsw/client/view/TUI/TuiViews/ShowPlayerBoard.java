package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.model.*;

import java.io.Serializable;
import java.util.Scanner;

public class ShowPlayerBoard implements TuiView{
    private GameBoard gameboard;
    private Card[][] cardsMatrix;
    public void play(GameBoard gameBoard){
        this.gameboard=gameBoard;
    }
    private void printBoard(){
        cardsMatrix = gameboard.getCardMatrix();
        for(int y =0; y < gameboard.getMAX_Y();y++){
            System.out.println("\n");
            for(int x =0; x < gameboard.getMAX_X();x++){
                Card card = cardsMatrix[x][y];
                if(card instanceof GoldCard) {
                    printGoldCard((GoldCard) card);
                }
                else if(card instanceof ResourceCard){
                    printResourceCard((ResourceCard) card);
                }
                else {
                    System.out.println(gameboard.getCheckBoxValue(x,y));
                }
            }

        }
    }







    private void printGoldCard(GoldCard g) {
        System.out.println("Show front or back?\n [1] = front, [2] = back");
        Scanner t= new Scanner(System.in);
        int o = t.nextInt();
        VisibleAngle[] array;
        if (o == 2) { array = g.getBackAngles();}
        else { array = g.getFrontAngles();}
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                String q = g.getBackSymbol().getFirst().getSymbolName();
                q = switch (q) {
                    case "leaf" -> "GRE";
                    case "mushroom" -> "ORA";
                    case "butterfly" -> "PUR";
                    case "fox" -> "BLU";
                    default -> q;
                };
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
    private void printResourceCard(ResourceCard r) {
        System.out.println("show front or back?\n [1] = front, [2] = back");
        Scanner t= new Scanner(System.in);
        int o = t.nextInt();
        VisibleAngle[] array;
        if (o == 2) { array = r.getBackAngles();}
        else { array = r.getFrontAngles();}
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                String q = r.getBackSymbol().getFirst().getSymbolName();
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

    @Override
    public void play(Client client) {

    }
}
