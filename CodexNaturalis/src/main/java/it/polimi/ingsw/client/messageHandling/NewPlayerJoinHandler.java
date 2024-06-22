package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.TUI.Tui;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.CommonMessage;
import it.polimi.ingsw.network.message.allMessages.NewPlayerJoin;

public class NewPlayerJoinHandler implements MessageHandler{
    @Override
    public void manage(Message msg, Client client) {
        NewPlayerJoin newPlayer = (NewPlayerJoin) msg;
        client.getPlayerList().add(newPlayer.getNewUser());
        client.setServerLastMessage(newPlayer.getDescription());
        if(client.getUI() instanceof Tui) {
            System.out.println(client.getServerLastMessage());

            System.out.println("ACTUAL LOBBY: ");

            for (String s : client.getPlayerList()) {
                System.out.println(s + " ");
            }
        } else {
            client.getUI().run();
        }
    }
}
