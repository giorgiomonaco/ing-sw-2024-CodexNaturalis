package it.polimi.ingsw.client.commands.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.CommandManager;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ShowHandRequest;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class ShowHandCommand implements CommandManager {
    private Client client;
    public ShowHandCommand(Client client) {
        this.client = client;
    }
    public void handleMessage(String[] commands) throws RemoteException {
        Message toSend = new ShowHandRequest(messEnum.SHOW_HAND, client.getUsername());
        client.sendMessage(toSend);

    }
}
