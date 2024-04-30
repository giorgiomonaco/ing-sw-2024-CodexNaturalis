package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.server.RMIServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    static int PORT = 1234;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
            RMIServerInterface stub = (RMIServerInterface) registry.lookup("RMIServerInterface");
            stub.login("Gio");
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}
