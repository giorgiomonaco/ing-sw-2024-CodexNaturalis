package it.polimi.ingsw.client.commands.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.CommandManager;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ShowUncoveredCardsRequest;
import it.polimi.ingsw.network.message.messEnum;

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
