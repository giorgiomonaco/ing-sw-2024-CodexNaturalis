package it.polimi.ingsw.client.commands.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.CommandManager;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.DrawCardRequest;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.messEnum;

import java.rmi.RemoteException;

public class DrawCardCommand implements CommandManager {
    private Client client;
    public DrawCardCommand(Client client) {
        this.client = client;
    }
    public void handleMessage(String[] commands) throws RemoteException {
        Message toSend = new DrawCardRequest(messEnum.DRAW_CARD_REQUEST, commands[1], commands[2], Integer.parseInt(commands[3]));
        client.sendMessage(toSend);
    }

}
