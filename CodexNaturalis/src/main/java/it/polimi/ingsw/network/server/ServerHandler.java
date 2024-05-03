package it.polimi.ingsw.network.server;

import java.util.Scanner;

public class ServerHandler {
    ServerConfigurationBase data;

    public ServerHandler(ServerConfigurationBase configurationBase){
        this.data = configurationBase;
    }

    public void init() {
        String serverIP;
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the server address IP if you want, instead press only ENTER: ");
        serverIP = scan.nextLine().trim();

        if(!serverIP.isEmpty()) {
            // if(isValid(serverIP)){
                data.setSocketIP(serverIP);
            // }
        }
    }

    public void isValid(String check){
        // Controlla validità ip
    }
}
