package it.polimi.ingsw.network;

import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.allMessages.PingMessage;
import it.polimi.ingsw.server.ServerHandler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Pinger extends Thread {
    private final ServerHandler handler;
    private Map<String, ClientConnection> connectedClients;
    private Message msg;

    public Pinger(ServerHandler serverHandler){
        handler = serverHandler;
    }

    private void getClients(){
        connectedClients = handler.getConnectedClients();
    }

    public void loadMessage(Message msg){
        this.msg = msg;
    }


    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            getClients();
            if(!connectedClients.isEmpty()){
                for(String c: connectedClients.keySet()){
                    if(connectedClients.get(c).isConnected()){
                        System.out.println(Colors.greenColor + "Pinging to " + c + Colors.resetColor);
                        handler.sendMessageToPlayer(c,
                                new PingMessage(ServerHandler.HOSTNAME));

                        try{
                            TimeUnit.MILLISECONDS.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if(msg == null){
                            handler.playerDisconnection(connectedClients.get(c));
                            System.out.println(Colors.redColor + "Ping to " + c + " failed." + Colors.resetColor);
                        }
                        else{
                            System.out.println(Colors.greenColor + "Ping to " + c + " successful." + Colors.resetColor);
                        }
                    }
                    msg = null;
                }

                try{
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try{
                    System.out.println(Colors.greenColor + "No players connected yet." + Colors.resetColor);
                    TimeUnit.MILLISECONDS.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
