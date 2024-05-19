package it.polimi.ingsw.network.message.allMessages;

import it.polimi.ingsw.client.messageHandling.MessageHandler;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.messEnum;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameBoard;

public class ShowPlayerBoard extends Message {
    GameBoard gameBoard;

    public ShowPlayerBoard(messEnum messType, String senderUsername, GameBoard gameBoard){
        super(messType, senderUsername);
        this.gameBoard=gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public MessageHandler createHandler() {
        return null;
    }
}
