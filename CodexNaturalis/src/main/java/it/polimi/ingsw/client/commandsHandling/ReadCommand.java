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

    /**
     * Constructs a ReadCommand object with the specified Tui and Client instances.
     * Initializes a command map and populates it.
     *
     * @param tui the Tui instance associated with the command
     * @param tcpClient the Client instance associated with the command
     */
    public ReadCommand(Tui tui, Client tcpClient){
        this.tui = tui;
        client = tcpClient;
        mapOfCommand = new HashMap<>();
        initializeMap();
    }


    /**
     * Runs the command reading loop, processing commands from the console input.
     * Handles command mapping and manages disconnection in case of RemoteException.
     */
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

    /**
     * Maps and handles a command from user input.
     *
     * @param command the command string to be processed
     * @throws RemoteException if a remote communication issue occurs
     */
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

    /**
     * Initializes the command map with predefined commands and their associated handlers.
     */
    public void initializeMap(){
        mapOfCommand.put("login", new LoginCommand(client));
        mapOfCommand.put("num", new SelNumPlayersCommand(client));
        mapOfCommand.put("color", new SelTokenCommand(client));
        mapOfCommand.put("obj", new SelObjCardCommand(client));
        mapOfCommand.put("draw",new DrawCardCommand(client));
        mapOfCommand.put("card", new PlayCardCommand(client));
        mapOfCommand.put("chat", new ChatCommand(client));
        mapOfCommand.put("side", new SelFirstCardSideCommand(client));
    }
}
