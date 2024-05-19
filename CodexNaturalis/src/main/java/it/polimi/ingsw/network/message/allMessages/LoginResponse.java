package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.LoginHandler;
import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class LoginResponse extends Message {
    private int result;
    private boolean successful;
    public LoginResponse(String senderUsername) {
        super(messEnum.LOGIN_RESPONSE, senderUsername);
    }

    public LoginResponse(String senderUsername, String optDescription) {
        super(messEnum.LOGIN_RESPONSE, senderUsername, optDescription);
    }

    public LoginResponse(String senderUsername, int result, String optDescription) {
        super(messEnum.LOGIN_RESPONSE, senderUsername, optDescription);
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    @Override
    public MessageHandler createHandler() {
        return new LoginHandler();
    }

    public boolean isSuccessful() {
        return successful;
    }
}
