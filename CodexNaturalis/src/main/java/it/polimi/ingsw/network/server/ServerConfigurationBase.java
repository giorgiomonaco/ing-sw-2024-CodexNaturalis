package it.polimi.ingsw.network.server;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class ServerConfigurationBase {
    private int portRMI;
    private int portTCP;
    private String registryName;
    private String socketIP;
    public ServerConfigurationBase(){
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

    public void setSocketIP(String socketIP) {
        this.socketIP = socketIP;
    }
}
