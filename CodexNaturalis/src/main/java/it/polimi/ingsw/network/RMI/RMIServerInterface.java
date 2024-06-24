package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.network.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * The class contains the methods that can be called
 * by the client on the server.
 */
public interface RMIServerInterface extends Remote {

    void receiveFromClient(Message msg, RMIClientInterface rmiClientInterface) throws RemoteException;

}
