package it.polimi.ingsw.client.commandsHandling.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.allMessages.ChatMessage;
import it.polimi.ingsw.network.message.allMessages.SelectionCard;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.CommandManager;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


public class ChatCommand implements CommandManager {
    private Client client;

    public ChatCommand(Client client) {
        this.client = client;
    }

    @Override
    public void handleMessage(String[] commands, stateEnum clientState) throws RemoteException, CommandNotAvailableException, WrongInsertionException {
        if (client.getCurrentState().equals(stateEnum.LOGIN)) {
            throw new CommandNotAvailableException();
        }


            String[] temp = Arrays.copyOfRange(commands, 2, commands.length);
            String result = String.join(" ", temp);
            ChatMessage toSend = new ChatMessage(client.getUsername(), commands[1], result);
            client.sendMessage(toSend);



    }
}

