package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.SelectionToken;

import java.rmi.RemoteException;

public class SelTokenCommand implements CommandManager {

    private final Client client;
    public SelTokenCommand(Client client){
        this.client = client;
    }


    /**
     * Handles a selection of token command message.
     *
     * @param commands array of command parameters
     * @param clientState the current state of the client
     * @throws RemoteException if a remote communication issue occurs
     * @throws CommandNotAvailableException if the command is not available in the current client state
     * @throws WrongInsertionException if the command insertion is incorrect
     */
    @Override
    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {

        if(!client.getCurrentState().equals(stateEnum.SELECT_TOKEN)){
            throw new CommandNotAvailableException();
        }
        if(!client.getAvailableTokens().contains(commands[1])){
            throw new WrongInsertionException("The selected color is not available. Please choose one of the available ones");
        }

        Message toSend = new SelectionToken(client.getUsername(), commands[1]);
        client.setCurrentState(stateEnum.SEL_FIRST_CARD_SIDE);
        client.sendMessage(toSend);

    }
}
