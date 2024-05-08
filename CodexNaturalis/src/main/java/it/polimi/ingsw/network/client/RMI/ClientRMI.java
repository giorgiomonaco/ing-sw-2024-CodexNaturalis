package it.polimi.ingsw.network.client.RMI;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.server.RMI.RMIServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI extends Client implements RMIClientInterface{
    static int PORT;
    static String serverIP;
    static String registry;

    public ClientRMI (String RegistryName, String IP, int serverPort) {
        registry = RegistryName;
        serverIP = IP;
        PORT = serverPort;
    }

    public void start() {
        try {
            Registry reg = LocateRegistry.getRegistry(serverIP, PORT);
            RMIServerInterface stub = (RMIServerInterface) reg.lookup(registry);
            stub.login("Gio");
        } catch (RemoteException | NotBoundException e) {
            System.err.println("ClientMain exception: " + e.toString());
        }
    }

    @Override
    public void receive(String message) throws RemoteException {

    }
}
