package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionNumPlayers;

import java.rmi.RemoteException;

import static it.polimi.ingsw.client.view.GUI.Panels.NumOfPlayersPanel.isNumeric;

public class SelNumPlayersCommand implements CommandManager {

    private final Client client;

    public SelNumPlayersCommand(Client client){
        this.client = client;
    }


    /**
     * Handles a selection of the number of players command message.
     *
     * @param commands array of command parameters
     * @param currState the current state of the client
     * @throws RemoteException if a remote communication issue occurs
     * @throws CommandNotAvailableException if the command is not available in the current client state
     * @throws WrongInsertionException if the command insertion is incorrect
     */
    @Override
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {
        if(!client.getCurrentState().equals(stateEnum.SELECT_NUM_PLAYERS)){
            throw new CommandNotAvailableException();
        } else if(!isNumeric(commands[1])){
            throw new WrongInsertionException("WRONG SELECTION!\nYou have to insert a number not a string!");
        }

        int numOfPlayers = Integer.parseInt(commands[1]);
        if(numOfPlayers < 2 || numOfPlayers > 4){
            throw new WrongInsertionException("WRONG SELECTION!\nYou have to select a number of player between 2 and 4.");
        }
        SelectionNumPlayers toSend = new SelectionNumPlayers(client.getUsername(), numOfPlayers);
        client.sendMessage(toSend);
    }
}
