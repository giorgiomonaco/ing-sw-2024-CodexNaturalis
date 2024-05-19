package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.CommonMessage;
import it.polimi.ingsw.network.message.allMessages.LobbyCreation;

import java.util.List;

public class LobbyHandler implements MessageHandler{
    @Override
    public void handle(Message msg, Client client) {
        LobbyCreation lobby = (LobbyCreation) msg;
        client.setCurrentState(stateEnum.LOBBY);
        client.setPlayerList(lobby.getPlayersInLobby());
        client.getUI().run();
        client.getUI().printMessage(msg);

        client.getUI().printMessage(new CommonMessage("",
                "ACTUAL LOBBY: "));

        for(String s : client.getPlayerList()){
            client.getUI().printMessage(new CommonMessage("",
                    s + " "));
        }
    }
}
