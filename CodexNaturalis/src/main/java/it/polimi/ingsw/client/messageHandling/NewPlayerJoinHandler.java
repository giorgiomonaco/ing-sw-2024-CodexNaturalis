package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.CommonMessage;
import it.polimi.ingsw.network.message.allMessages.NewPlayerJoin;

public class NewPlayerJoinHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        NewPlayerJoin newPlayer = (NewPlayerJoin) msg;
        client.getPlayerList().add(newPlayer.getNewUser());
        client.getUI().printMessage(newPlayer);

        client.getUI().printMessage(new CommonMessage("",
                "ACTUAL LOBBY: "));

        for(String s : client.getPlayerList()){
            client.getUI().printMessage(new CommonMessage("",
                    s + " "));
        }
    }
}
