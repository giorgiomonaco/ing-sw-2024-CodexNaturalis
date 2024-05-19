package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ShowHandRequest;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class ShowResourcesCommand implements CommandManager {
    private Client client;
    public ShowResourcesCommand(Client client) { this.client = client;
    }
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException {

        if(client.getCurrentState().equals(stateEnum.LOGIN) ||
                client.getCurrentState().equals(stateEnum.WAITING_LOBBY) ||
                client.getCurrentState().equals(stateEnum.SELECT_NUM_PLAYERS) ||
                client.getCurrentState().equals(stateEnum.LOBBY) ||
                client.getCurrentState().equals(stateEnum.ALREADY_STARTED) ||
                client.getCurrentState().equals(stateEnum.REJECTED)){
            throw new CommandNotAvailableException();
        }

        Message toSend = new ShowHandRequest(messEnum.SHOW_PLAYER_RESOURCES, client.getUsername());
        client.sendMessage(toSend);

    }
}
