package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.ChatHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Chat;

import java.util.List;

public class ChatResponse extends Message {

    private final List<Chat> chat;

    public ChatResponse(String senderUsername, List<Chat> chat) {
        super(messEnum.CHATRESPONSE, senderUsername);
        this.chat = chat;
    }

    public List<Chat> getChat() {
        return chat;
    }

    @Override
    public MessageHandler createHandler() {
        return new ChatHandler();
    }

}
