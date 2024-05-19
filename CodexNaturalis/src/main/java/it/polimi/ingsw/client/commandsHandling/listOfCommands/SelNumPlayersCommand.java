package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionNumPlayers;

import java.rmi.RemoteException;

public class SelNumPlayersCommand implements CommandManager {

    private final Client client;

    public SelNumPlayersCommand(Client client){
        this.client = client;
    }

    @Override
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException {
        if(!client.getCurrentState().equals(stateEnum.SELECT_NUM_PLAYERS)){
            throw new CommandNotAvailableException();
        }
        int numOfPlayers = Integer.parseInt(commands[1]);
        SelectionNumPlayers toSend = new SelectionNumPlayers(client.getUsername(), numOfPlayers);
        client.sendMessage(toSend);
    }
}
