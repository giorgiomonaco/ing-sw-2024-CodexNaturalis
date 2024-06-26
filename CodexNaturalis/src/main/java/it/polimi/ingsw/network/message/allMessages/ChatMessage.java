package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class ChatMessage extends Message {
    private String destination;
    private String chat;
    public ChatMessage(String SenderName, String destination, String chat) {
        super(messEnum.CHAT_MSG, SenderName);
        this.destination = destination;
        this.chat = chat;

    }


    public String getChat() {
        return chat;
    }

    public String getDestination() {
        return destination;
    }


    @Override
    public MessageHandler genHandler() {return null;
    }
}
