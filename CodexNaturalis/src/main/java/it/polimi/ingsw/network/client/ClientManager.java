package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.ViewMode;

public class ClientManager {
    ViewMode view;
    String serverIP;
    int serverPort;
    String Registry;

    // Constructor for TCP Client.
    public ClientManager(ViewMode selectedView, String IP, int port) {

    }

    // Constructor for RMI Client.
    public ClientManager(ViewMode selectedView, String Registry, String IP, int serverPort) {

    }
}
