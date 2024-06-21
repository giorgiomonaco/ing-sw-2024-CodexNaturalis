package it.polimi.ingsw.network;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class ServerConfigNetwork {
    private final int portRMI;
    private final int portTCP;
    private final String registryName;
    private String serverIP;

    public ServerConfigNetwork(){

        this.portRMI = 1234;
        this.portTCP = 1235;
        this.registryName = "RMIServerInterface";
        try {
            this.serverIP = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
            System.err.println("Couldn't get the LocalHost IP address.");
        }
        //this.serverIP = "172.20.10.4";
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

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }
}
