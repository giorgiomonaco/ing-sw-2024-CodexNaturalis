package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.network.ClientConnection;
import it.polimi.ingsw.server.ServerHandler;

public class RMIClientHandler extends ClientConnection {

    private ServerHandler handler;
    private RMIClientInterface rmiClientInterface;

    public RMIClientHandler(ServerHandler handler, RMIClientInterface rmiClientInterface){
        this.handler = handler;
        this.rmiClientInterface = rmiClientInterface;
        setConnected(true);
    }
}
