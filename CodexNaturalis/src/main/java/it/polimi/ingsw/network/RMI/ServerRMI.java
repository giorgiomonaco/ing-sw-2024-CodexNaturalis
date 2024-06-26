package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.LoginResult;
import it.polimi.ingsw.network.ServerNetwork;
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
    private String ip;
    private Registry registry;
    // private RMIServerInterface obj;
    private final ServerHandler handlerRMI;

    public ServerRMI(ServerNetwork data, ServerHandler handler) throws RemoteException {
        super();
        PORT = data.getPortRMI();
        ip = data.getServerIP();
        handlerRMI = handler;
    }


    public void start(){
        try {
            registry = LocateRegistry.createRegistry(PORT);
            registry.rebind("RMIServerInterface", this);
            System.setProperty("java.rmi.server.hostname", ip);
        } catch (RemoteException e) {
            System.err.println("Error while starting server: " + ip);
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
    public void receiveFromClient(Message msg, RMIClientInterface rmiClientInterface) throws RemoteException {
        if(msg.getType().equals(messEnum.LOGIN_REQUEST)){
            onLogin((LoginRequest) msg, rmiClientInterface);
        } else {
            handlerRMI.manageMessage(msg);
        }
    }

    @Override
    public void receivePingFromClient(String username) throws RemoteException {
        System.out.println(Colors.greenColor + "Received ping from client: " + username + Colors.resetColor);
    }

    public void onLogin(LoginRequest request, RMIClientInterface rmiClientInterface) throws RemoteException {

        LoginResult result = handlerRMI.manageLoginRequest(request, new RMIClientHandler(handlerRMI, rmiClientInterface));

        if(result.isLogged() && result.isReconnected()){
            rmiClientInterface.getFromServer(new LoginResponse(ServerHandler.HOSTNAME, 3, request.getUsername()));
        }
        else if(result.isLogged() && !result.isReconnected()){
            rmiClientInterface.getFromServer(new LoginResponse(ServerHandler.HOSTNAME, 1, request.getUsername()));
            handlerRMI.newLoginRequest(request);
        } else {
            rmiClientInterface.getFromServer(new LoginResponse(ServerHandler.HOSTNAME, 2, "Username already in use, try to insert another one."));
        }
    }

}
