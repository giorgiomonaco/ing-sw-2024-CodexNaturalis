package it.polimi.ingsw.client.view;

import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;

import java.util.List;

public interface UserInterface {
    void run();
    void endGame();
    void printErrorMessage(String msg);
    void printMessage(String msg);
    void printChat();

}
