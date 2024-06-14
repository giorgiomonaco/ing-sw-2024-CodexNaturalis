package it.polimi.ingsw.client.messageHandling;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.ChatMessage;
import it.polimi.ingsw.network.message.allMessages.ChatResponse;
import it.polimi.ingsw.network.message.allMessages.DrawCardRequest;


public class ChatHandler implements MessageHandler {

    @Override
    public void handle(Message msg, Client client) {
        ChatResponse chat = (ChatResponse) msg;
        client.setChat(chat.getChat());
        client.getUI().printChat();
    }
}