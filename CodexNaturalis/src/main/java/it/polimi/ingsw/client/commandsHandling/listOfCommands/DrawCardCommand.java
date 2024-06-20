package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.DrawCardResponse;

import java.rmi.RemoteException;

public class DrawCardCommand implements CommandManager {
    private final Client client;
    public DrawCardCommand(Client client) {
        this.client = client;
    }


    /**
     * Handles a draw card command message.
     *
     * @param commands array of command parameters
     * @param currState the current state of the client
     * @throws RemoteException if a remote communication issue occurs
     * @throws CommandNotAvailableException if the command is not available in the current client state
     * @throws IllegalArgumentException if the command format is invalid
     */
    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException {


        if (commands == null || commands.length < 2) {
            throw new IllegalArgumentException("Invalid command format");
        }

        int choice = Integer.parseInt(commands[1]);
        if (choice < 1 || choice > 6) {
            System.out.println("Invalid choice. Please choose between 1 and 6");
            throw new CommandNotAvailableException();
        }

        if (!currState.equals(stateEnum.DRAW_CARD)) {
            System.out.println("Invalid state. Expected DRAW_CARD state");
            throw new CommandNotAvailableException();
        }

        Message toSend = new DrawCardResponse(client.getUsername(), choice);
        client.sendMessage(toSend);
    }
}
