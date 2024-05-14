package it.polimi.ingsw.client.commands.listOfCommands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.CommandManager;
import it.polimi.ingsw.network.TCP.ClientTCP;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.messEnum;

public class LoginCommand implements CommandManager {
    private Client tcpClient;
    public LoginCommand(Client client) {
        tcpClient = client;
    }
    public void handleMessage(String[] commands){
        Message toSend = new LoginRequest(messEnum.LOGIN_REQUEST, commands[1]);
        tcpClient.sendMessage(toSend);
    }

}
