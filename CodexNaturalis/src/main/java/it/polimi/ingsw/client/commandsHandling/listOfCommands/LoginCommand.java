package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class LoginCommand implements CommandManager {
    private final Client client;
    public LoginCommand(Client client) {
        this.client = client;
    }
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException {
        if(!client.getCurrentState().equals(stateEnum.LOGIN)){
            throw new CommandNotAvailableException();
        }
        Message toSend = new LoginRequest(messEnum.LOGIN_REQUEST, commands[1]);
        client.sendMessage(toSend);
    }

}
