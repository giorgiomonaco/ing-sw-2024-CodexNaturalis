package it.polimi.ingsw.client.commands;

import it.polimi.ingsw.client.Client;

import java.rmi.RemoteException;

public interface CommandManager {

    public void handleMessage(String[] commands) throws RemoteException;

}
