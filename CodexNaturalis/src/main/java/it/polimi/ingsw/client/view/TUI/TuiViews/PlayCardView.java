package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.Boards;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.ObjectiveCard;
import it.polimi.ingsw.server.model.VisibleAngle;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayCardView implements TuiView {
    private Boards boards;
    private List<Card> playerHand;
    private Client client;
    List<ObjectiveCard> commonObj = new ArrayList<>();




    @Override
    public void play(Client client) {
        this.client = client;
        System.out.println("\n\nThose are the common Objectives:");
        commonObj = client.getListObjective();
        int i = 1;
        for(ObjectiveCard c: commonObj){
            System.out.println("\nCARD " + i + " :\n");
            Tui view = (Tui) client.getUI();
            view.printObjectiveCard(c);
            i++;
        }
        System.out.println("\n\nCHOOSE A SPOT ON THE BOARD TO PLACE THE CARD\n   -Green: Position available    -Blue: Card already played");
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
        List<String>[][] elements = new ArrayList[100][100];
        Card[][] cardBoard =client.getBoards().getGameBoard();

        for (int y = 99; y >= 0; y--) {
            for (int x = 99; x >= 0; x--) {
                if (boards.checkBoard[x][y] == 1) {
                    if (x > maxX) maxX = x;
                    if (x < minX) minX = x;
                    if (y > maxY) maxY = y;
                    if (y < minY) minY = y;
                }
                elements[x][y] = new ArrayList<>();
            }
        }

        for (int y = minY-1; y < maxY+2; y++) {
            for (int x = minX-1; x < maxX+2; x++) {
                if(boards.getCheckBoard()[x][y] == 1){
                    for(int i=0;i<4;i++) {
                        if(boards.gameBoard[x][y].getFrontVisibleAngle(i) != null && boards.gameBoard[x][y].getFrontVisibleAngle(i).getSymbol() != null){
                            if(i==0){
                                elements[x-1][y-1].add(boards.getGameBoard()[x][y].getFrontVisibleAngle(i).getSymbol().getSymbolName());
                            }
                            if(i==1){
                                elements[x+1][y-1].add(boards.getGameBoard()[x][y].getFrontVisibleAngle(i).getSymbol().getSymbolName());
                            }
                            if(i==2){
                                elements[x-1][y+1].add(boards.getGameBoard()[x][y].getFrontVisibleAngle(i).getSymbol().getSymbolName());
                            }
                            if(i==3){
                                elements[x+1][y+1].add(boards.getGameBoard()[x][y].getFrontVisibleAngle(i).getSymbol().getSymbolName());
                            }
                        }
                    }
                }
            }
        }
        for (int y = minY-1; y < maxY+2; y++) {
            for (int x = minX-1; x < maxX+2; x++) {
                if (!elements[x][y].isEmpty() && boards.checkBoard[x][y]!=1){
                        System.out.print("\n["+x+"],["+y+"] -> "+ elements[x][y] + "");

                }
            }
        }
    }
}





