package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.states.stateEnum;

import java.rmi.RemoteException;

public class ShowUncoveredCardsCommand implements CommandManager {
    private Client client;
    public ShowUncoveredCardsCommand(Client client){
        this.client = client;
    }
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException {
        //Message toSend = new ShowUncoveredCardsRequest(messEnum.SHOW_UNCOVERED_CARDS, client.getUsername());
        //client.sendMessage(toSend);
    }
}
