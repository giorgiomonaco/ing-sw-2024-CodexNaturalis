package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.network.LoginResult;
import it.polimi.ingsw.network.ServerConfigNetwork;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.allMessages.LoginResponse;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.ServerHandler;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServerRMI extends UnicastRemoteObject implements RMIServerInterface {
    private static int PORT;
    private Registry registry;
    // private RMIServerInterface obj;
    private ServerHandler handlerRMI;

    public ServerRMI(ServerConfigNetwork data, ServerHandler handler) throws RemoteException {
        super();
        PORT = data.getPortRMI();
        handlerRMI = handler;
    }

    public ServerRMI() throws RemoteException {
        super();
    }

    public void start(){
        try {
            // obj = new ServerRMI();
            // RMIServerInterface stub = (RMIServerInterface) UnicastRemoteObject.exportObject(this, 0);
            registry = LocateRegistry.createRegistry(PORT);
            registry.rebind("RMIServerInterface", this);
        } catch (RemoteException e) {
            System.err.println("Error while starting server: " + e.toString());
        }
        System.out.println("--- RMI server is ready on port: " + PORT + " ---");
    }

    public void stop() {
        try {
            registry.unbind("RMIServerInterface");
            // UnicastRemoteObject.unexportObject(obj, true);
            UnicastRemoteObject.unexportObject(registry, true);
            System.err.println("Server stopped.");
        } catch (Exception e) {
            System.err.println("Error while stopping server: " + e.getMessage());
        }
    }

    @Override
    public void receiveMessage(Message msg, RMIClientInterface rmiClientInterface) throws RemoteException {
        if(msg.getType().equals(messEnum.LOGIN_REQUEST)){
            onLogin((LoginRequest) msg, rmiClientInterface);
        } else {
            handlerRMI.manageMessage(msg);
        }
    }

    public void onLogin(LoginRequest request, RMIClientInterface rmiClientInterface) throws RemoteException {

        LoginResult result = handlerRMI.manageLoginRequest(request, new RMIClientHandler(handlerRMI, rmiClientInterface));

        if(result.isLogged() && result.isReconnected()){
            rmiClientInterface.receiveFromServer(new LoginResponse(ServerHandler.HOSTNAME, 1, request.getUsername()));
        }
        else if(result.isLogged() && !result.isReconnected()){
            rmiClientInterface.receiveFromServer(new LoginResponse(ServerHandler.HOSTNAME, 1, request.getUsername()));
            handlerRMI.newLoginRequest(request);
        } else {
            rmiClientInterface.receiveFromServer(new LoginResponse(ServerHandler.HOSTNAME, 2, request.getUsername()));
        }
    }

}
