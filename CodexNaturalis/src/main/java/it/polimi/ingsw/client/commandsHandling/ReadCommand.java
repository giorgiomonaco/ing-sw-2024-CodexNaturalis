package it.polimi.ingsw.client.commandsHandling;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commandsHandling.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commandsHandling.commandsException.WrongInsertionException;
import it.polimi.ingsw.client.commandsHandling.listOfCommands.*;
import it.polimi.ingsw.client.view.TUI.Tui;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadCommand implements Runnable{

    private final Tui tui;
    private final Map<String, CommandManager> mapOfCommand;
    private final Client client;

    public ReadCommand(Tui tui, Client tcpClient){
        this.tui = tui;
        client = tcpClient;
        mapOfCommand = new HashMap<>();
        initializeMap();
    }

    public void run(){
        Scanner scan = new Scanner(System.in);

        while(!Thread.currentThread().isInterrupted()) {
            String command = scan.nextLine().trim();

            try {
                mapCommand(command);
            } catch (RemoteException e) {
                client.manageDisconnection();
            }
        }
    }

    public void mapCommand(String command) throws RemoteException {
        String[] commands = command.split(" ");

        if(!mapOfCommand.containsKey(commands[0])) {
            System.err.println("Command not found. Try another command.");
        }
        else {
            try {
                mapOfCommand.get(commands[0]).handleMessage(commands, client.getCurrentState());
            } catch (CommandNotAvailableException e) {
                System.err.println("PERMISSION DENIED!");
                System.err.println("You are not allowed to do this command now.");
            } catch (WrongInsertionException e) {
                System.err.println(e.getException());
            }
        }
    }

    public void initializeMap(){
        mapOfCommand.put("login", new LoginCommand(client));
        mapOfCommand.put("num", new SelNumPlayersCommand(client));
        mapOfCommand.put("color", new SelTokenCommand(client));
        mapOfCommand.put("obj", new SelObjCardCommand(client));
        mapOfCommand.put("draw",new DrawCardCommand(client));
        mapOfCommand.put("card", new PlayCardCommand(client));
        mapOfCommand.put("chat", new ChatCommand(client));
    }
}
