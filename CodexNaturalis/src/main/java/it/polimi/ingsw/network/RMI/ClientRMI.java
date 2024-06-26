package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.message.Message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * ClientRMI class represents an RMI client in the system. It extends the Client class
 * and implements the RMIClientInterface.
 * It manages the connection with the RMI server and handles the sending and receiving
 * of messages.
 */
public class ClientRMI extends Client implements RMIClientInterface {
    static int PORT;
    static String serverIP;
    static String registry;
    private RMIServerInterface stub;
    private RMIClientPinger pinger;


    /**
     * Constructs a new ClientRMI object with the specified registry name, server IP, and port.
     *
     * @param RegistryName the name of the RMI registry
     * @param IP the IP address of the server
     * @param serverPort the port number of the server
     * @throws RemoteException if there is an error in the remote method call
     */
    public ClientRMI (String RegistryName, String IP, int serverPort) throws RemoteException {
        super(2);
        registry = RegistryName;
        serverIP = IP;
        PORT = serverPort;
        start();
    }


    /**
     * Starts the RMI client by connecting to the RMI registry and looking up the server object.
     */
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


    /**
     * Receives a message from the server.
     *
     * @param msg the message received from the server
     * @throws RemoteException if there is an error in the remote method call
     */
    @Override
    public void getFromServer(Message msg) throws RemoteException {
        manageMessage(msg);
    }


    /**
     * Sends a message to the server.
     *
     * @param msg the message to be sent to the server
     */
    @Override
    public void sendMessage(Message msg) {
        try {

            if(pinger == null) {
                pinger = new RMIClientPinger(stub, this);
                pinger.start();
                System.out.println(Colors.greenColor + "Start pinging the server." + Colors.resetColor);
            }

            stub.receiveFromClient(msg, this);

        } catch (RemoteException e) {
            // Lost connection with the server
            manageDisconnection();
        }
    }
}
