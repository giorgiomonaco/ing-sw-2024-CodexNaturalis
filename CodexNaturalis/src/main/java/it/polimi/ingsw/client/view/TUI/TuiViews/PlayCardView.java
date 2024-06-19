package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;


import java.util.List;

public class PlayCardView implements TuiView {
    private Boards boards;
    private List<Card> playerHand;
    private Client client;



    @Override
    public void play(Client client) {
        this.client = client;
        System.out.println("CHOOSE A SPOT ON THE BOARD TO PLACE THE CARD\n-Red: Position not available   -Green: Position available    -Blue: Cards played");
        playerHand = client.getPlayerHand();
        boards = client.getBoards();
        printBoard();

        System.out.println("\nCHOOSE A CARD TO PLAY FROM YOUR HAND \n");
        printHand();
        printPoints();
        printResources();
        askCardToPlay();

    }

    private void askCardToPlay() {
        System.out.println("WHICH CARD DO YOU WANT TO PLAY ?\n\nInsert command [card <num> <x> <y> <side>], where:\n->num is the number of the card you want to play\n->x and y are the coordinates that you choose\n-side -> front / back");

    }

    private void printHand() {
        Tui view = (Tui) client.getUI();
            view.printCards(playerHand);
    }

    private void printBoard() {

        int maxX = 0;
        int maxY = 0;
        int minX = 100;
        int minY = 100;

        for (int y = 99; y >= 0; y--) {
            for (int x = 99; x >= 0; x--) {
                if (boards.checkBoard[x][y] == 1) {
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

                switch (boards.checkBoard[x][y]) {
                    case -1, -2:
                        System.out.print("   ");
                        break;
                    case 0:
                        System.out.print("  " + Colors.greenColor + boards.checkBoard[x][y] + Colors.resetColor + " ");
                        break;
                    case 1:
                        System.out.print("  " + Colors.blueColor + boards.checkBoard[x][y] + Colors.resetColor + " ");
                        break;
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



