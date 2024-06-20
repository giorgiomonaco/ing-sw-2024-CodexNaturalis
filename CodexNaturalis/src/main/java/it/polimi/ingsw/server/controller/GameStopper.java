package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.message.allMessages.CommonMessage;
import it.polimi.ingsw.network.message.allMessages.GameStopped;
import it.polimi.ingsw.server.ServerHandler;

import java.util.concurrent.TimeUnit;

public class GameStopper extends Thread{


    private final ServerHandler handler;

    /**
     * Constructs a GameStopper with the specified ServerHandler.
     */
    public GameStopper(ServerHandler serverHandler){
        this.handler = serverHandler;
    }

    /**
     * Runs the game stopper process. This method sends a game stopped message to all players, waits for a specified
     * timeout period, and then ends the game if no players rejoin during the timeout. If a player rejoins, the
     * countdown stops.
     */
    public void run(){
        handler.sendMessageToAll(new GameStopped(ServerHandler.HOSTNAME));
        System.out.println(Colors.redColor + "The game stopped because there is only one player connected." + Colors.resetColor);

        try {
            TimeUnit.SECONDS.sleep(ServerHandler.TIMEOUT);
        } catch (InterruptedException e) {
            System.out.println(Colors.yellowColor + "A player rejoined the game! The counter stopped." + Colors.resetColor);
            return;
        }

        handler.endGame();
    }
}
