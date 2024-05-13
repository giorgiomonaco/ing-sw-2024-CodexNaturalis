package it.polimi.ingsw.network.TCP;

import java.io.ObjectOutputStream;

public class SenderTCP {
    private ObjectOutputStream out;

    public SenderTCP(ObjectOutputStream output) {
        out = output;
    }

}
