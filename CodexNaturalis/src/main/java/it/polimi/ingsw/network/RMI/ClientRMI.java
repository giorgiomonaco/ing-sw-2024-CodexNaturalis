package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.Message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI extends Client implements RMIClientInterface {
    static int PORT;
    static String serverIP;
    static String registry;
    private RMIServerInterface stub;

    public ClientRMI (String RegistryName, String IP, int serverPort) throws RemoteException {
        super();
        registry = RegistryName;
        serverIP = IP;
        PORT = serverPort;
        start();
    }

    public void start() {
        try {
            Registry reg = LocateRegistry.getRegistry(serverIP, PORT);
            stub = (RMIServerInterface) reg.lookup(registry);
            System.out.println("RMI Client ready to receive and send.");
        } catch (RemoteException e) {
            System.err.println("Couldn't access the remote object.");
            manageDisconnection();
        } catch (NotBoundException e) {
            System.err.println("Server is not bound");
        }
    }

    @Override
    public void getFromServer(Message msg) throws RemoteException {
        manageMessage(msg);
    }

    @Override
    public void sendMessage(Message msg) {
        try {
            stub.receiveFromClient(msg, this);
        } catch (RemoteException e) {
            // Lost connection with the server
            manageDisconnection();
        }
    }
}
