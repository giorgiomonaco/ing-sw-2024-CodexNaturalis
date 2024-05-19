package it.polimi.ingsw.client.commandsHandling;

import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;

import java.rmi.RemoteException;

public interface CommandManager {

    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException;

}
