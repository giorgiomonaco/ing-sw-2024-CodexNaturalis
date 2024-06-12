package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.network.ClientConnection;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.ServerHandler;

import java.rmi.RemoteException;

public class RMIClientHandler extends ClientConnection {

    private final ServerHandler handler;
    private final RMIClientInterface rmiClientInterface;

    public RMIClientHandler(ServerHandler handler, RMIClientInterface rmiClientInterface){
        this.handler = handler;
        this.rmiClientInterface = rmiClientInterface;
        setConnected(true);
    }

    @Override
    public void sendMessage(Message msg) {
        try {
            rmiClientInterface.receiveFromServer(msg);
        } catch (RemoteException e) {
            System.err.println("Error on the remote invocation of the method.");
            handler.playerDisconnection(this);
        }
    }
}
