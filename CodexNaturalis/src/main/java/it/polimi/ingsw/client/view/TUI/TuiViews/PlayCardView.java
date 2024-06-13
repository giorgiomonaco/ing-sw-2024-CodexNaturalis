package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayCardView implements TuiView {
    private Boards boards;
    private List<Card> playerHand;
    private Client client;
    private Colors colors = new Colors();


    @Override
    public void play(Client client) {
        this.client = client;
        System.out.println("CHOOSE A CARD TO PLAY FROM YOUR HAND \n\nCHOOSE A SPOT ON THE BOARD TO PLACE THE CARD\n-Red: Position not available   -Green: Position available    -Blue: Cards played");
        playerHand = client.getPlayerHand();
        boards = client.getBoards();
        printBoard();

        printHand();
        printPoints();
        printResources();
        askCardToPlay();

    }

    private void askCardToPlay() {
        System.out.println("WHICH CARD DO YOU WANT TO PLAY ?\n\nInsert command card <num> <x> <y> <true>, where:\n-num is the number of the card you want to play\n-x and y are the coordinates on the board\n-Write true if you want to place the card face up, false if you want to place it back up");

    }

    private void printHand() {
        Tui view = (Tui) client.getUI();
        for (Card card : playerHand) {
            view.printCard(card);
        }
    }

    private void printBoard() {
     /*  System.out.print("  X: ");
        for (int o=40; o<60; o++ ) System.out.print(o+"  ");
       System.out.print("\nY:" );
        for (int y = 40; y < 60 ; y++){

            System.out.println();
            System.out.print(y + " ");
            for (int x = 40; x < 60 ; x++){

                switch (boards.checkboard[x][y]){
                    case -1:
                        System.out.print(" "+ colors.redColor+ boards.checkboard[x][y]+ colors.resetColor +" ");
                        break;
                    case 0:
                        System.out.print("  "+ colors.greenColor + boards.checkboard[x][y]+ colors.resetColor +" ");
                        break;
                    case 1:
                        System.out.print("  "+ colors.blueColor + boards.checkboard[x][y]+ colors.resetColor + " ");
                        break;

                }
            }
        }*/

        int maxX = 0;
        int maxY = 0;
        int minX = 100;
        int minY = 100;
        for (int y = 99; y >= 0; y--) {



            for (int x = 99; x >= 0; x--) {
                if (boards.checkboard[x][y] == 1) {
                    if (x > maxX) maxX = x;
                    if (x < minX) minX = x;
                    if (y > maxY) maxY = y;
                    if (y < minY) minY = y;

                }

            }
        }
        System.out.print("X->");
        for (int o = minX-1; o < maxX+2; o++){
            System.out.print(" "+o+" ");
        }
        for (int y = minY-1; y < maxY+2; y++) {

            System.out.println();
            System.out.print(y + " ");

            for (int x = minX-1; x < maxX+2; x++) {

                switch (boards.checkboard[x][y]) {
                    case -1:
                        System.out.print(" " + colors.redColor + boards.checkboard[x][y] + colors.resetColor + " ");
                        break;
                    case 0:
                        System.out.print("  " + colors.greenColor + boards.checkboard[x][y] + colors.resetColor + " ");
                        break;
                    case 1:
                        System.out.print("  " + colors.blueColor + boards.checkboard[x][y] + colors.resetColor + " ");
                        break;

                }
            }
        }

    }
}

    private void printPoints(){
        System.out.println("\nYour current points: " + client.getPoints());
    }

    private void printResources(){
        System.out.print("Your current resources: ");
        int count = 0;
        List<String> resName = List.of(new String[]{"mushroom", "leaf", "fox", "butterfly", "feather", "bottle", "scroll"});
        for(int i : client.getResources()){
            System.out.print(resName.get(count) + " ");
            System.out.print(i + " ");
            count++;
        }
        System.out.println();
    }

}



