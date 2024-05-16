package it.polimi.ingsw.client.commands.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.CommandManager;
import it.polimi.ingsw.network.message.allMessages.SelectionNumPlayers;

public class SelNumPlayersCommand implements CommandManager {

    private final Client client;

    public SelNumPlayersCommand(Client client){
        this.client = client;
    }

    @Override
    public void handleMessage(String[] commands) {
        int numOfPlayers = Integer.parseInt(commands[1]);
        SelectionNumPlayers toSend = new SelectionNumPlayers(client.getUsername(), numOfPlayers);
        client.sendMessage(toSend);
    }
}
