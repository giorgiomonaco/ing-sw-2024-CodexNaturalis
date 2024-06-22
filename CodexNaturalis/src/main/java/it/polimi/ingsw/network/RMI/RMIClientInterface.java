package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.network.message.Message;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The class contains the methods that can be called
 * by the server on the client.
 */
public interface RMIClientInterface extends Remote {

    void getFromServer(Message message) throws RemoteException;
}
