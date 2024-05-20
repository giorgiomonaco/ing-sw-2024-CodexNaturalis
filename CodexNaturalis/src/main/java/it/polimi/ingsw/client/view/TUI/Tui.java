package it.polimi.ingsw.client.view.TUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.ReadCommand;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.TUI.TuiViews.*;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;
import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.server.model.VisibleAngle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Tui implements UserInterface{

    private Client tuiCli;
    private Map<stateEnum, TuiView> phaseView;
    private ReadCommand reader;
    List<Card> playerHand;


    public Tui(Client client){

        tuiCli = client;
        client.setUI(this);
        phaseView = new HashMap<>();
        reader = new ReadCommand(this, client);
        Thread readerThread = new Thread(reader);
        readerThread.start();

        phaseView.put(stateEnum.LOGIN, new LoginView());
        phaseView.put(stateEnum.WAITING_LOBBY, new WaitingLobbyView());
        phaseView.put(stateEnum.ALREADY_STARTED, new AlreadyStartedView());
        phaseView.put(stateEnum.DISCONNECTION, new DisconnectionView());
        phaseView.put(stateEnum.DRAW_CARD, new DrawCardView());
        phaseView.put(stateEnum.LOBBY, new LobbyView());
        phaseView.put(stateEnum.GAME_STARTED, new GameStartedView());
        phaseView.put(stateEnum.PLAY_CARD, new PlayCardView());
        phaseView.put(stateEnum.SELECT_NUM_PLAYERS, new SelNumPlayerView());
        phaseView.put(stateEnum.SELECT_TOKEN, new SelTokenView());
        phaseView.put(stateEnum.WAITING_TURN, new WaitTurnView());
        phaseView.put(stateEnum.REJECTED, new RejectedView());
    }


    @Override
    public void run() {
        switch(tuiCli.getCurrentState()){
            case LOGIN:
                phaseView.get(stateEnum.LOGIN).play(tuiCli);
                break;
            case WAITING_LOBBY:
                phaseView.get(stateEnum.WAITING_LOBBY).play(tuiCli);
                break;
            case REJECTED:
                phaseView.get(stateEnum.REJECTED).play(tuiCli);
                break;
            case ALREADY_STARTED:
                phaseView.get(stateEnum.ALREADY_STARTED).play(tuiCli);
                break;
            case DISCONNECTION:
                phaseView.get(stateEnum.DISCONNECTION).play(tuiCli);
                break;
            case DRAW_CARD:
                phaseView.get(stateEnum.DRAW_CARD).play(tuiCli);
                break;
            case LOBBY:
                phaseView.get(stateEnum.LOBBY).play(tuiCli);
                break;
            case GAME_STARTED:
                phaseView.get(stateEnum.GAME_STARTED).play(tuiCli);
                break;
            case PLAY_CARD:
                phaseView.get(stateEnum.PLAY_CARD).play(tuiCli);
                break;
            case SELECT_NUM_PLAYERS:
                phaseView.get(stateEnum.SELECT_NUM_PLAYERS).play(tuiCli);
                break;
            case SELECT_TOKEN:
                phaseView.get(stateEnum.SELECT_TOKEN).play(tuiCli);
                break;
            case WAITING_TURN:
                phaseView.get(stateEnum.WAITING_TURN).play(tuiCli);
                break;
        }
    }

    @Override
    public void endGame() {

    }

    @Override
    public void printErrorMessage(Message msg) {
        String toPrint = msg.getDescription();
        System.err.println(toPrint);
    }

    @Override
    public void printMessage(Message msg) {
        String toPrint = msg.getDescription();
        System.out.println(toPrint);
    }


    public void viewCards(List<Card> playerHand) {
        ShowCardsView showCardsView = new ShowCardsView();
        showCardsView.play(playerHand);
    }


    public void viewCard(Card card) {
        DrawCardView drawCardView = new DrawCardView();
        printCard(card);
    }



    public void viewUncoveredCards(List<Card> cardList) {
        ShowUncoveredCardsView showUncoveredCardsView = new ShowUncoveredCardsView();
        showUncoveredCardsView.viewUncoveredCards(cardList);
    }

    public void printCard(Card card) {
        if (card instanceof ResourceCard) {
            printResourceCard((ResourceCard) card);
        } else if (card instanceof GoldCard) {
            printGoldCard((GoldCard) card);
        } else {
            System.out.println(card.getCardID());
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
                String q = g.getBackSymbol().getSymbolName();
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

}


