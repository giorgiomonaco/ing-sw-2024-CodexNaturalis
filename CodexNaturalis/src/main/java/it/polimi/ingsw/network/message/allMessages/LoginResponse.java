package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;

public class LoginResponse extends Message {
    private int result;
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

}
