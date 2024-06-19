package it.polimi.ingsw.client.view.TUI.TuiViews;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.server.model.Card;

import java.util.List;

public class DrawCardView implements TuiView {

    private Client client;
    private List<Card> visibleGoldCards;
    private List<Card> visibleResourceCards;
    int cardNumber;


    @Override
    public void play(Client client) {
        cardNumber = 1;
        this.client = client;
        System.out.println("\nUNCOVERED GOLD CARDS:");
        printGoldCards();
        System.out.println("\nUNCOVERED RESOURCE CARDS:");
        printResourceCards();
        System.out.println("\nCHOOSE A CARD TO DRAW\n\nWrite [draw <choice>]\n where choice is: \n-The number of the card you want to draw or\n-Write either [5] for goldDeck, or  [6] for resourceDeck, to draw a covered card");
    }

    private void printResourceCards() {
        List<Card> resourceCards = client.getVisibleResourceCards();
        Tui view = (Tui) client.getUI();
        view.printCards(resourceCards);

        }


    private void printGoldCards() {
        List<Card> goldCards = client.getVisibleGoldCards();
        Tui view = (Tui) client.getUI();
        view.printCards(goldCards);
        /*for (Card c : goldCards) {
            Tui view = (Tui) client.getUI();
            System.out.println("CARD " + cardNumber + " :\n");
            cardNumber++;
            view.printCard(c);*/
        }
    }


