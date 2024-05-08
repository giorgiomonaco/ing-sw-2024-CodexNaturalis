package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.RMI.ClientRMI;
import it.polimi.ingsw.network.client.TCP.ClientTCP;
import it.polimi.ingsw.network.client.stateManager.stateEnum;
import it.polimi.ingsw.view.ViewMode;

public class ClientManager {
    ViewMode view;
    Client client;

    // Constructor for TCP Client.
    public ClientManager(ViewMode selectedView, String IP, int serverPort) {
        // This has to be the command that starts the selected view
        view = selectedView;


        client = new ClientTCP(IP, serverPort);
        client.setCurrentState(stateEnum.LOGIN);
    }

    // Constructor for RMI Client.
    public ClientManager(ViewMode selectedView, String Registry, String IP, int serverPort) {
        // This has to be the command that starts the selected view
        view = selectedView;

        client = new ClientRMI(Registry, IP, serverPort);
        client.setCurrentState(stateEnum.LOGIN);
    }
}
