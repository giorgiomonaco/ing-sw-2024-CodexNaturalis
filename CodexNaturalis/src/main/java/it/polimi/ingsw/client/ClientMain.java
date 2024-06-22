package it.polimi.ingsw.client;

import it.polimi.ingsw.client.view.ViewMode;
import it.polimi.ingsw.network.ClientNetwork;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws RemoteException {

        Scanner scan = new Scanner(System.in);
        String selString;
        int selection = 0;
        ViewMode selectedView;
        ClientHandler cliManager = null;
        ClientNetwork data = new ClientNetwork().createConfig();

        System.out.println("Hello! Please, choose one of the current options to start the game:");
        System.out.println("Press [1] for GUI");
        System.out.println("Press [2] for TUI");
        do {
            selString = scan.nextLine().trim();
            try {
                selection = Integer.parseInt(selString);
                if (selection != 1 && selection != 2) {
                    System.out.println("Invalid selection: please press [1] for GUI or press [2] for TUI.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection: you inserted a string!" +
                        "\nPlease press [1] for GUI or press [2] for TUI.");
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
            try {
                selection = Integer.parseInt(selString);
                if (selection != 1 && selection != 2) {
                    System.out.println("Invalid selection: please press [1] for RMI or press [2] for TCP.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection: you inserted a string!" +
                        "\nPlease press [1] for RMI or press [2] for TCP.");
            }
        } while(selection != 1 && selection != 2);


        if(selection == 1) {
            // Only for debug
            System.out.println("You selected RMI.");

            cliManager = new ClientHandler(selectedView, data.getRegistryName(), data.getServerIP(), data.getPortRMI());
        }
        else {
            // Only for debug
            System.out.println("You selected TCP.");

            cliManager = new ClientHandler(selectedView, data.getServerIP(), data.getPortTCP());
        }

    }
}
