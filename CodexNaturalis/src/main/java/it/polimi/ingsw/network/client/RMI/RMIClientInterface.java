package it.polimi.ingsw.network.client.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {
    void receive(String message) throws RemoteException;
}
