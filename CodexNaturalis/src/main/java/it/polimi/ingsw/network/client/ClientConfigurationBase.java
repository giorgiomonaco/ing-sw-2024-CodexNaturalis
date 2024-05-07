package it.polimi.ingsw.network.client;

import java.util.Scanner;

public class ClientConfigurationBase {
    private int portRMI;
    private int portTCP;
    private String registryName;
    private String socketIP;

    /*
    public ClientConfigurationBase(){
        this.portRMI = 1234;
        this.portTCP = 1235;
        this.registryName = "RMIServerInterface";
        try {
            this.socketIP = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
            System.err.println("Couldn't get the LocalHost IP address.");
        }
    }
    */

    public ClientConfigurationBase(){
    }

    public ClientConfigurationBase createConfig(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the IP address of the server: ");
        socketIP = scan.nextLine().trim();

        System.out.println("Insert the number of the TCP port: ");
        String tcpPort = scan.nextLine().trim();
        portTCP = Integer.parseInt(tcpPort);

        System.out.println("Insert the name of the RMI registry: ");
        registryName = scan.nextLine().trim();

        System.out.println("Insert the number of the RMI port: ");
        String rmiPort = scan.nextLine().trim();
        portRMI = Integer.parseInt(rmiPort);

        System.out.println("---Summary---");
        System.out.println("-Server IP address: " + socketIP);
        System.out.println("-TCP port: " + portTCP);
        System.out.println("-RMI port: " + portRMI);
        System.out.println("-Registry name: " + registryName);
        System.out.println("-------------");


        // Salvataggio dati su file da fare

        return this;
    }

    public int getPortRMI() {
        return portRMI;
    }

    public int getPortTCP() {
        return portTCP;
    }

    public String getRegistryName() {
        return registryName;
    }

    public String getSocketIP() {
        return socketIP;
    }


}
