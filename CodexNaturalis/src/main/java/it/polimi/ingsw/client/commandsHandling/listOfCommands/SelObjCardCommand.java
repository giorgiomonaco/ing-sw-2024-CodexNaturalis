package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionObjCard;

import java.rmi.RemoteException;

public class SelObjCardCommand implements CommandManager {

    private Client client;

    public SelObjCardCommand(Client client){
        this.client = client;
    }

    @Override
    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {

        if(!client.getCurrentState().equals(stateEnum.SELECT_OBJECTIVE)){
            throw new CommandNotAvailableException();
        }

        int selection = Integer.parseInt(commands[1]);
        if(selection != 1 && selection != 2){
            throw new WrongInsertionException("WRONG SELECTION!\nYou have to select a card form the available ones.");
        }

        SelectionObjCard toSend = new SelectionObjCard(client.getUsername(), selection);
        client.sendMessage(toSend);
    }
}
