package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.message.allMessages.CommonMessage;
import it.polimi.ingsw.network.message.allMessages.GameStopped;
import it.polimi.ingsw.server.ServerHandler;

import java.util.concurrent.TimeUnit;

public class GameStopper extends Thread{

    private final ServerHandler handler;

    public GameStopper(ServerHandler serverHandler){
        this.handler = serverHandler;
    }
    public void run(){
        handler.sendMessageToAll(new GameStopped(ServerHandler.HOSTNAME));

        try {
            TimeUnit.MINUTES.sleep(ServerHandler.TIMEOUT);
        } catch (InterruptedException e) {
            System.out.println(Colors.yellowColor + "A player rejoined the game! The counter stopped." + Colors.resetColor);
            return;
        }

        handler.endGame();
    }
}
