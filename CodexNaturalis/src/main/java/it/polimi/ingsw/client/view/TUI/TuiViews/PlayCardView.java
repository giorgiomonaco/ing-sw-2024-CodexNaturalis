package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.VisibleAngle;


import java.util.ArrayList;
import java.util.List;

public class PlayCardView implements TuiView {
    private Boards boards;
    private List<Card> playerHand;
    private Client client;



    @Override
    public void play(Client client) {
        this.client = client;
        System.out.println("CHOOSE A SPOT ON THE BOARD TO PLACE THE CARD\n   -Green: Position available    -Blue: Card already played");
        playerHand = client.getPlayerHand();
        boards = client.getBoards();
        printBoard();
        printLosableResources();

        System.out.println("\nCHOOSE A CARD TO PLAY FROM YOUR HAND \n");
        printHand();
        printPoints();
        printResources();
        askCardToPlay();

    }

    private void askCardToPlay() {
        System.out.println("WHICH CARD DO YOU WANT TO PLAY ?\n\nInsert command [card <num> <x> <y> <side>], where:\n->num is the number of the card you want to play\n->x and y are the coordinates that you choose\n->side = front / back");

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

                switch (boards.getCheckBoard()[x][y]) {
                    case -1, -2:
                        System.out.print("    ");
                        break;
                    case 0:
                        System.out.print("  " + Colors.greenColor + boards.getCheckBoard()[x][y] + Colors.resetColor + " ");
                        break;
                    case 1:
                        System.out.print("  " + Colors.blueColor + boards.getCheckBoard()[x][y] + Colors.resetColor + " ");
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

    private void printLosableResources() {
        System.out.println("\n\nthose are your losable resources by placing a card on that spot");
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
        }/*
        Card[][] cardBoard =client.getBoards().getGameBoard();

        for (int y = minY-1; y < maxY+2; y++) {
            for (int x = minX-1; x < maxX+2; x++) {

                // List to store the covered angles by neighboring cards
                List<VisibleAngle> coveredAngle = new ArrayList<>();
                // Check the front visible angle of the card at (x+1, y+1) if it exists
                if (cardBoard[x + 1][y + 1] != null) {
                    boolean front = cardBoard[x + 1][y + 1].getSide();
                    if (front) {
                        if (cardBoard[x + 1][y + 1].getFrontVisibleAngle(0) != null) {
                            coveredAngle.add(cardBoard[x + 1][y + 1].getFrontVisibleAngle(0));
                        }
                    }
                }

                // Check the front visible angle of the card at (x+1, y-1) if it exists
                if (cardBoard[x + 1][y - 1] != null) {
                    boolean front = cardBoard[x + 1][y - 1].getSide();
                    if (front) {
                        if (cardBoard[x + 1][y - 1].getFrontVisibleAngle(2) != null) {
                            coveredAngle.add(cardBoard[x + 1][y - 1].getFrontVisibleAngle(2));
                        }
                    }
                }

                // Check the front visible angle of the card at (x-1, y+1) if it exists
                if (cardBoard[x - 1][y + 1] != null) {
                    boolean front = cardBoard[x - 1][y + 1].getSide();
                    if (front) {
                        if (cardBoard[x - 1][y + 1].getFrontVisibleAngle(1) != null) {
                            coveredAngle.add(cardBoard[x - 1][y + 1].getFrontVisibleAngle(1));
                        }
                    }
                }

                // Check the front visible angle of the card at (x-1, y-1) if it exists
                if (cardBoard[x - 1][y - 1] != null) {
                    boolean front = cardBoard[x - 1][y - 1].getSide();
                    if (front) {
                        if (cardBoard[x - 1][y - 1].getFrontVisibleAngle(3) != null) {
                            coveredAngle.add(cardBoard[x - 1][y - 1].getFrontVisibleAngle(3));
                        }
                    }
                }

                // If there are covered angles, lower the player's resources based on the symbols
                if (!coveredAngle.isEmpty()) {
                    System.out.print("["+x+","+y+"] ->");
                    System.out.print("[");
                    for (VisibleAngle angle : coveredAngle) {
                        System.out.print(angle.getSymbol().getSymbolName());
                        if(coveredAngle.size()>1){
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");

                }


                coveredAngle.clear(); // Clear the list for future use
            }
        }
    }*/
    }
}





