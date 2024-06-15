package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.SelectionFirstCardSide;
import it.polimi.ingsw.network.message.allMessages.SelectionObjCard;

import java.rmi.RemoteException;
import java.util.Objects;

public class SelFirstCardSideCommand implements CommandManager {
    private final Client client;


    public SelFirstCardSideCommand(Client client) {
        this.client = client;
    }

    @Override
    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {

        if (!client.getCurrentState().equals(stateEnum.SEL_FIRST_CARD_SIDE)) {
            throw new CommandNotAvailableException();
        }

        String selection = commands[1];
        if (!Objects.equals(selection, "front") && !Objects.equals(selection, "back")) {
            throw new WrongInsertionException("WRONG SELECTION!\nYou have to put [side] than [front] or [back]");
        }


        SelectionFirstCardSide toSend = new SelectionFirstCardSide(client.getUsername(), selection);
        client.sendMessage(toSend);

    }
}
