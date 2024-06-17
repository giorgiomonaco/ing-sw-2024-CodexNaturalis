package it.polimi.ingsw.server.model;

import java.io.Serializable;

public class Chat implements Serializable {
    String sender;
    String receiver;
    String msg;
    boolean priv;

    public Chat(String sender, String msg, boolean priv, String receiver){
        this.sender = sender;
        this.msg = msg;
        this.priv = priv;
        this.receiver = receiver;
    }

    public String getMsg() {
        return msg;
    }

    public String getSender() {
        return sender;
    }

    public boolean isPrivate() {
        return priv;
    }

    public String getReceiver() {
        return receiver;
    }
}
