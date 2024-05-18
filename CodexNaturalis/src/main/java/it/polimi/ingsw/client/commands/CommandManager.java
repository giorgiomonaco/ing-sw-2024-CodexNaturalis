package it.polimi.ingsw.client.commands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;

import java.rmi.RemoteException;

public interface CommandManager {

    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException;

}
