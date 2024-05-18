package it.polimi.ingsw.client.commands.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.CommandManager;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class LoginCommand implements CommandManager {
    private Client client;
    public LoginCommand(Client client) {
        this.client = client;
    }
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException {
        Message toSend = new LoginRequest(messEnum.LOGIN_REQUEST, commands[1]);
        client.sendMessage(toSend);
    }

}
