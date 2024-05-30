package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.DrawCardRequest;
import it.polimi.ingsw.network.message.allMessages.DrawCardResponse;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class DrawCardCommand implements CommandManager {
    private final Client client;
    public DrawCardCommand(Client client) {
        this.client = client;
    }

    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException {

        int choice = Integer.parseInt(commands[1]);

        if(!client.getCurrentState().equals(stateEnum.DRAW_CARD)){
            throw new CommandNotAvailableException();
        }

        else if (choice < 1 || choice > 6){
            System.out.println("The selected index is not available. Please choose above the available ones");
            throw new CommandNotAvailableException();
        }
        else {
            Message toSend = new DrawCardResponse(client.getUsername(), choice);
            client.sendMessage(toSend);
        }

    }

}
