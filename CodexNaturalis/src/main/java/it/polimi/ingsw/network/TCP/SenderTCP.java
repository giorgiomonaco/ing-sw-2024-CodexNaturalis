package it.polimi.ingsw.network.TCP;

import java.io.ObjectOutputStream;

public class SenderTCP extends Thread{
    private ObjectOutputStream out;

    public SenderTCP(ObjectOutputStream output) {
        out = output;
    }

}
