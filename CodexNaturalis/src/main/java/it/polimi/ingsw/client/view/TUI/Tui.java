package it.polimi.ingsw.client.view.TUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.ReadCommand;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.TUI.TuiViews.*;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Tui implements UserInterface{

    private Client tuiCli;
    private Map<stateEnum, TuiView> phaseView;
    private ReadCommand reader;
    List<Card> playerHand;
    Colors color = new Colors();



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
        phaseView.put(stateEnum.SELECT_OBJECTIVE, new SelObjView());
        phaseView.put(stateEnum.WAITING_TURN, new WaitTurnView());
        phaseView.put(stateEnum.REJECTED, new RejectedView());
        phaseView.put(stateEnum.SHOW_WINNER, new ShowWinnerView());

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
            case SELECT_OBJECTIVE:
                phaseView.get(stateEnum.SELECT_OBJECTIVE).play(tuiCli);
                break;
            case WAITING_TURN:
                phaseView.get(stateEnum.WAITING_TURN).play(tuiCli);
                break;
            case SHOW_WINNER:
                phaseView.get(stateEnum.SHOW_WINNER).play(tuiCli);
                break;
        }

    }
    @Override
    public void printChat(){
        List<Chat> a = tuiCli.getChat();
        System.out.println("\n\nCHAT: ");

        for (Chat c:a){
            System.out.println(c.getSender() + "-> "+ c.getMsg());
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

    @Override
    public void viewCards(List<Card> playerHand) {

    }

    public void viewCard(Card card) {
        DrawCardView drawCardView = new DrawCardView();
        printCard(card);
    }

    @Override
    public void viewFirst(InitialCard init) {

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
        VisibleAngle[] array;
        for(int p=0;p<2;p++) {
            if (p == 0) {array = g.getFrontAngles();
                System.out.println("\nfront:");}
            else { array = g.getBackAngles();
                System.out.println("\nback: ");}
            for (int i = 0; i < 4; i++) {
                if (i == 2) {
                    String q = g.getBackSymbol().getFirst().getSymbolName();
                    q = switch (q) {
                        case "leaf" -> color.greenColor + "GRE " + color.resetColor;
                        case "mushroom" -> color.orangeColor + "ORA " + color.resetColor;
                        case "butterfly" -> color.purpleColor + "PUR " + color.resetColor;
                        case "fox" -> color.blueColor + "BLU " + color.resetColor;
                        default -> q;
                    };
                    System.out.println("\n"+"||  " + q + "  ||");
                }

                if ( array[i] == null) {
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
                if (i == 0){ System.out.print(" == ");
                    if(p==0) System.out.print(k);
                    else System.out.print("=");
                    System.out.print(" == ");
                }

                if (i == 2 && p==0){
                    System.out.print(" ==");

                    int[] y = g.getNeededSymbols();
                    for (int m = 0; m < 4; m++) {
                        if (y[m] > 0 && m == 0) System.out.print(y[m] + "M");
                        if (y[m] > 0 && m == 1) System.out.print(y[m] + "L");
                        if (y[m] > 0 && m == 2) System.out.print(y[m] + "F");
                        if (y[m] > 0 && m == 3) System.out.print(y[m] + "B");

                    }
                    System.out.print("== ");
                } if (i == 2 && p==1){
                    System.out.print(" == = == ");
                }

                if(i==3) System.out.println();
            }

        }
    }

    private void printResourceCard(ResourceCard r) {
        VisibleAngle[] array;
        for (int p = 0; p < 2; p++) {
            if (p == 0) {
                array = r.getFrontAngles();
                System.out.println("\nfront:");
            } else {
                array = r.getBackAngles();
                System.out.println("\nback:");
            }
            for (int i = 0; i < 4; i++) {
                if (i == 2) {
                    String q = r.getBackSymbol().getFirst().getSymbolName();
                    switch (q) {
                        case "leaf":
                            q = color.greenColor + "GRE " + color.resetColor;;
                            break;
                        case "mushroom":
                            q = color.orangeColor + "ORA " + color.resetColor;;
                            break;
                        case "butterfly":
                            q = color.purpleColor + "PUR " + color.resetColor;;
                            break;
                        case "fox":
                            q = color.blueColor + "BLU " + color.resetColor;;
                            break;
                    }
                    System.out.println("\n"+"||  " + q + "  ||");
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
                if (i == 0){ System.out.print(" == ");
                    if(p==0) System.out.print(k);
                    else System.out.print("=");
                    System.out.print(" == ");
                }
                if (i == 2) System.out.print(" == = == ");
                if(i==3) System.out.println();
            }

        }
    }

    public void printInitialCard(InitialCard r) {
        System.out.println("\nCARTA INIZIALE:\n");
        String a;
        for (int p=0;p<2;p++){
            if(p==0) System.out.println("FRONTE:");
            if(p==1) System.out.println("\nRETRO:");
            if(p==0){
                for(int i=0;i<4;i++){
                    a=r.getFrontVisibleAngle(i).getSymbol().getSymbolName();

                    switch (a) {
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
                    if(i==0) System.out.print("=============");
                    if (i == 1) System.out.println("\n||           ||");
                    if(i==2) System.out.print("=============");

                }
            }
            if(p==1){
                for(int i=0;i<4;i++) {
                    a = r.getFrontVisibleAngle(i).getSymbol().getSymbolName();

                    switch (a) {
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
                    if (i == 0) System.out.print("=============");
                    if (i == 1) {
                        System.out.print("\n||    ");
                        for(Symbol u : r.getBackSymbol()){
                            switch(u.getSymbolName()) {case "mushroom":
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
                        System.out.println("     ||");
                    }
                    if (i == 2) System.out.print("=============");
                }
            }
        }
    }


    public void printObjectiveCard(ObjectiveCard o){
       if(!Objects.equals(o.getType(), "position")){
           System.out.println(" ========== ");
           System.out.println(" ||   "+o.getPoints()+"   ||");
           System.out.println(" ||       ||");
           String q=o.getType();
           if (Objects.equals(o.getType(), "fox") || Objects.equals(o.getType(), "butterfly") || Objects.equals(o.getType(), "mushroom") || Objects.equals(o.getType(), "leaf") || Objects.equals(o.getType(), "special")|| Objects.equals(o.getType(), "feather") || Objects.equals(o.getType(), "scroll") || Objects.equals(o.getType(), "bottle"))
               switch (o.getType()){
                   case "fox":
                       System.out.println(" || F F F ||");
                       break;
                   case "butterfly":
                       System.out.println(" || B B B ||");
                       break;
                   case "leaf":
                       System.out.println(" || L L L ||");
                       break;
                   case "mushroom":
                       System.out.println(" || M M M ||");
                       break;
                   case "special":
                       System.out.println(" || f b s ||");
                       break;

                       case "feather":
                           System.out.println(" ||  f  f ||");
                           break;
                       case "bottle":
                           System.out.println(" ||  b  b ||");
                           break;
                       case "scroll":
                           System.out.println(" ||  s  s ||");
                           break;
               }


           System.out.println(" ========== ");
           }
                if(Objects.equals(o.getCard1(), "orange") && Objects.equals(o.getCard2(), "blue")){
                    System.out.print("=============\n" +
                                     "||    "+color.orangeColor+"ORA"+color.resetColor+"  ||\n"+
                                     "||"+color.blueColor+"BLU"+color.resetColor+"      ||\n"+
                                     "||"+color.blueColor+"BLU"+color.resetColor+"+    3 ||\n"+
                                     "=============");
                }else if  (Objects.equals(o.getCard1(), "orange") && Objects.equals(o.getCard2(), "orange") && Objects.equals(o.getCard3(), "orange")) {
                    System.out.print("=============\n" +
                        "||      "+color.orangeColor+"ORA"+color.resetColor+"||\n"+
                        "||   "+color.orangeColor+"ORA"+color.resetColor+"   ||\n"+
                        "||"+color.orangeColor+"ORA"+color.resetColor+"    2 ||\n"+
                        "=============");}
                    else if (Objects.equals(o.getCard1(), "blue") && Objects.equals(o.getCard2(), "blue") && Objects.equals(o.getCard3(), "blue")) {
                        System.out.print("=============\n" +
                                "||      "+color.blueColor+"BLU"+color.resetColor+"||\n"+
                                "||   "+color.blueColor+"BLU"+color.resetColor+"   ||\n"+
                                "||"+color.blueColor+"BLU"+color.resetColor+"    2 ||\n"+
                                "=============");

                }
                else if (Objects.equals(o.getCard1(), "green") && Objects.equals(o.getCard2(), "green") && Objects.equals(o.getCard3(), "green")) {
                    System.out.print("=============\n" +
                            "||"+color.greenColor+"GRE"+color.resetColor+"      ||\n"+
                            "||   "+color.greenColor+"GRE"+color.resetColor+"   ||\n"+
                            "||2     "+color.greenColor+"GRE"+color.resetColor+"||\n"+
                            "=============");

                } else if (Objects.equals(o.getCard1(), "purple") && Objects.equals(o.getCard2(), "purple") && Objects.equals(o.getCard3(), "purple")) {
                    System.out.print("=============\n" +
                            "||"+color.purpleColor+"PUR"+color.resetColor+"      ||\n"+
                            "||   "+color.purpleColor+"PUR"+color.resetColor+"   ||\n"+
                            "||2     "+color.purpleColor+"PUR"+color.resetColor+"||\n"+
                            "=============");


                } else if (Objects.equals(o.getCard1(), "orange") && Objects.equals(o.getCard2(), "orange") && Objects.equals(o.getCard3(), "green")) {
        System.out.print("=============\n" +
                "||"+color.orangeColor+"ORA"+color.resetColor+"    3 ||\n"+
                "||"+color.orangeColor+"ORA"+color.resetColor+"      ||\n"+
                "||   "+color.greenColor+"GRE"+color.resetColor+"   ||\n"+
                "=============");


        }else if (Objects.equals(o.getCard1(), "green") && Objects.equals(o.getCard2(), "green") && Objects.equals(o.getCard3(), "purple")) {
                    System.out.print("=============\n" +
                            "||   "+color.greenColor+"GRE"+color.resetColor+"   ||\n"+
                            "||   "+color.greenColor+"GRE"+color.resetColor+"   ||\n"+
                            "||"+color.purpleColor+"PUR"+color.resetColor+"    3 ||\n"+
                            "=============");


                }
                else if (Objects.equals(o.getCard1(), "blue") && Objects.equals(o.getCard2(), "purple") && Objects.equals(o.getCard3(), "purple")) {
                    System.out.print("=============\n" +
                            "||"+color.blueColor+"BLU"+color.resetColor+"    3 ||\n"+
                            "||   "+color.purpleColor+"PUR"+color.resetColor+"   ||\n"+
                            "||   "+color.purpleColor+"PUR"+color.resetColor+"   ||\n"+
                            "=============");


                }
       }


}




