package it.polimi.ingsw.network.RMI;

import it.polimi.ingsw.client.Client;

import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class RMIClientPinger extends Thread {

    private final RMIServerInterface stub;
    private final Client client;

    public RMIClientPinger(RMIServerInterface stub, Client client) {
        this.stub = stub;
        this.client = client;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            try {
                stub.receivePingFromClient(client.getUsername());
            } catch (RemoteException e) {
                client.manageDisconnection();
                Thread.currentThread().interrupt();
            }

            try{
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                client.manageDisconnection();
                Thread.currentThread().interrupt();
            }

        }
    }
}
