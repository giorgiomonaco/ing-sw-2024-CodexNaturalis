package it.polimi.ingsw.network.server;

import it.polimi.ingsw.model.Message;
import it.polimi.ingsw.network.client.RMIClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The class contains the methods that can be called
 * by the client on the server.
 */
public interface RMIServerInterface extends Remote {
    //void receiveMessage(Message message, RMIClientInterface clientInterface);

    /**
     * Class to try the connection, no use in the project.
     * @param nick username of the player
     * @throws RemoteException
     */
    public void login(String nick) throws RemoteException;



}
