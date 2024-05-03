package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.ViewMode;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String selString;
        int selection = 0;
        ViewMode selectedView;
        ClientManager cliManager = null;
        String serverIP;
        int serverPort;
        String registry;

        System.out.println("Hello! Please, choose one of the current options to start the game:");
        System.out.println("Press [1] for GUI");
        System.out.println("Press [2] for TUI");
        do {
            selString = scan.nextLine().trim();
            selection = Integer.parseInt(selString);
            if(selection != 1 && selection != 2) {
                System.out.println("Invalid selection: please press [1] for GUI or press [2] for TUI.");
            }
        } while(selection != 1 && selection != 2);

        // Only for debug
        if(selection == 1) System.out.println("You selected GUI.");
        else System.out.println("You selected TUI.");

        // Here we manage to save the selection for the view...
        selectedView = selection == 1 ? ViewMode.GUI : ViewMode.TUI;

        selection = 0;

        System.out.println("Now please, choose the type of connection:");
        System.out.println("Press [1] for RMI");
        System.out.println("Press [2] for TCP");
        do {
            selString = scan.nextLine().trim();
            selection = Integer.parseInt(selString);
            if(selection != 1 && selection != 2) {
                System.out.println("Invalid selection: please press [1] for RMI or press [2] for TCP.");
            }
        } while(selection != 1 && selection != 2);

        // Only for debug
        if(selection == 1) System.out.println("You selected RMI.");
        else System.out.println("You selected TCP.");

        selection == 1 ? cliManager = new ClientManager(selectedView, registry, serverIP, serverPort) :
                cliManager = new ClientManager(selectedView, serverIP, serverPort);
    }
}
