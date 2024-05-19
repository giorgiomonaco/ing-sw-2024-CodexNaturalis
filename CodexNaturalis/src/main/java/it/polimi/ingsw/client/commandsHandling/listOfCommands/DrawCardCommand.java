package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.DrawCardRequest;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class DrawCardCommand implements CommandManager {
    private Client client;
    public DrawCardCommand(Client client) {
        this.client = client;
    }

    public void handleMessage(String[] commands, stateEnum currState) throws RemoteException, CommandNotAvailableException {
        if(!client.getCurrentState().equals(stateEnum.DRAW_CARD)){
            throw new CommandNotAvailableException();
        }
        Message toSend = new DrawCardRequest(messEnum.DRAW_CARD_REQUEST, commands[1], commands[2], Integer.parseInt(commands[3]));
        client.sendMessage(toSend);
    }

}
