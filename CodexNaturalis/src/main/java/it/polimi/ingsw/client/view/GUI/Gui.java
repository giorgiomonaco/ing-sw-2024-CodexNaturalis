package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;

import java.util.List;

public class Gui implements UserInterface {

    private final Client client;

    public Gui (Client client){
        this.client = client;

    }

    @Override
    public void run() {

    }
    @Override
    public void printChat(){

    }

    @Override
    public void endGame() {

    }

    @Override
    public void printErrorMessage(Message msg) {

    }

    @Override
    public void printMessage(Message msg) {

    }

    @Override
    public void viewCards(List<Card> playerHand) {

    }

    @Override
    public void viewCard(Card card) {

    }

    @Override
    public void viewFirst(InitialCard init) {

    }

}
