package it.polimi.ingsw.client;

import it.polimi.ingsw.network.RMI.ClientRMI;
import it.polimi.ingsw.network.TCP.ClientTCP;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.GUI.Gui;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.client.view.ViewMode;

import java.rmi.RemoteException;

public class ClientHandler {

    Client client;
    UserInterface gameView;

    // Constructor for TCP Client.
    public ClientHandler(ViewMode selectedView, String IP, int serverPort) throws RemoteException {

        client = new ClientTCP(IP, serverPort);
        client.setCurrentState(stateEnum.LOGIN);

        if(selectedView == ViewMode.TUI) {
            gameView = new Tui(client);
        } else {
            gameView = new Gui(client);
        }
        gameView.run();
    }

    // Constructor for RMI Client.
    public ClientHandler(ViewMode selectedView, String Registry, String IP, int serverPort) throws RemoteException {

        client = new ClientRMI(Registry, IP, serverPort);
        client.setCurrentState(stateEnum.LOGIN);

        if(selectedView == ViewMode.TUI) {
            gameView = new Tui(client);
        } else {
            gameView = new Gui(client);
        }
        gameView.run();
    }
}
