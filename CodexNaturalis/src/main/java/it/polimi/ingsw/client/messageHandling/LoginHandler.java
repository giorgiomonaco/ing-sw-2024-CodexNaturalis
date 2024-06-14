package it.polimi.ingsw.client.messageHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.CommonMessage;
import it.polimi.ingsw.network.message.allMessages.LoginResponse;

public class LoginHandler implements MessageHandler{

    @Override
    public void handle(Message msg, Client client) {
        LoginResponse response = (LoginResponse) msg;

        if(response.getResult() == 1){
            client.setUsername(response.getDescription());
            client.setCurrentState(stateEnum.LOGIN_SUCCESSFUL);
            client.getUI().run();
        } else {
            client.getUI().printErrorMessage(response);
        }
    }

}
