package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.ServerHandler;

import java.util.concurrent.TimeUnit;

public class GameStopper extends Thread{

    private final ServerHandler handler;

    public GameStopper(ServerHandler serverHandler){
        this.handler = serverHandler;
    }
    public void run(){

        try {
            TimeUnit.SECONDS.sleep(ServerHandler.TIMEOUT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        handler.endGame();
    }
}
