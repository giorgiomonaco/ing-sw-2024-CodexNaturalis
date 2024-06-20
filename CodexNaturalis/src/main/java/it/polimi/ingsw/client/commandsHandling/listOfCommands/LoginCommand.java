package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.GUI.Panels.LoginPanel;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class LoginCommand implements CommandManager {
    private final Client client;
    public LoginCommand(Client client) {
        this.client = client;
    }


    /**
     * Handles a login command message.
     *
     * @param commands array of command parameters
     * @param currState the current state of the client
     * @throws RemoteException if a remote communication issue occurs
     * @throws CommandNotAvailableException if the command is not available in the current client state
     * @throws WrongInsertionException if the command insertion is incorrect
     */
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {
        if(!client.getCurrentState().equals(stateEnum.LOGIN)){
            throw new CommandNotAvailableException();
        } else if(commands[1].isEmpty() || !isAlphabetic(commands[1])) {
            throw new WrongInsertionException("WRONG INSERTION!\nYou have to insert a string of characters!");
        }
        Message toSend = new LoginRequest(messEnum.LOGIN_REQUEST, commands[1]);
        client.sendMessage(toSend);
    }

    public static boolean isAlphabetic(String str) {
        return LoginPanel.isAlphabetic(str);
    }

}
