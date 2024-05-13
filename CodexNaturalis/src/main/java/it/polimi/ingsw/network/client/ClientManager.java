package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.RMI.ClientRMI;
import it.polimi.ingsw.network.client.TCP.ClientTCP;
import it.polimi.ingsw.network.client.stateManager.stateEnum;
import it.polimi.ingsw.view.GUI.Gui;
import it.polimi.ingsw.view.TUI.Tui;
import it.polimi.ingsw.view.UserInterface;
import it.polimi.ingsw.view.ViewMode;

public class ClientManager {

    Client client;
    UserInterface gameView;

    // Constructor for TCP Client.
    public ClientManager(ViewMode selectedView, String IP, int serverPort) {

        client = new ClientTCP(IP, serverPort);
        client.setCurrentState(stateEnum.LOGIN);

        if(selectedView == ViewMode.TUI) {
            gameView = new Tui(client);
        } else {
            gameView = new Gui();
        }
    }

    // Constructor for RMI Client.
    public ClientManager(ViewMode selectedView, String Registry, String IP, int serverPort) {

        client = new ClientRMI(Registry, IP, serverPort);
        client.setCurrentState(stateEnum.LOGIN);

        if(selectedView == ViewMode.TUI) {
            gameView = new Tui(client);
        } else {
            gameView = new Gui();
        }
    }
}
