package it.polimi.ingsw.client.commands;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.commands.commandsException.CommandNotAvailableException;
import it.polimi.ingsw.client.commands.listOfCommands.*;
import it.polimi.ingsw.client.view.TUI.Tui;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadCommand implements Runnable{

    private Tui tui;
    private String command;
    private Map<String, CommandManager> mapOfCommand;
    private Client client;

    public ReadCommand(Tui tui, Client tcpClient){
        this.tui = tui;
        client = tcpClient;
        mapOfCommand = new HashMap<>();
        initializeMap();
    }

    public void run(){
        Scanner scan = new Scanner(System.in);

        while(!Thread.currentThread().isInterrupted()) {
            command = scan.nextLine().trim();

            try {
                mapCommand(command);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
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
            }
        }
    }

    public void initializeMap(){
        mapOfCommand.put("login", new LoginCommand(client));
        mapOfCommand.put("num", new SelNumPlayersCommand(client));
        mapOfCommand.put("showCards",new ShowHandCommand((client)));
        mapOfCommand.put("drawCard",new DrawCardCommand(client));
        mapOfCommand.put("showResources",new ShowResourcesCommand(client));
        mapOfCommand.put("showBoard",new ShowBoardCommand(client));
    }
}
