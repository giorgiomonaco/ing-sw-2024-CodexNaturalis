package it.polimi.ingsw.server.model;

import java.io.Serializable;

public class Chat implements Serializable {
    String sender;
    String receiver;
    String msg;
    boolean priv;


    /**
     * Constructor
     */
    public Chat(String sender, String msg, boolean priv, String receiver){
        this.sender = sender;
        this.msg = msg;
        this.priv = priv;
        this.receiver = receiver;
    }


    /**
     * Getter method for retrieving the message.
     * @return  the message
     */
    public String getMsg() {
        return msg;
    }


    /**
     * Getter method for retrieving the sender.
     * @return  the sender
     */
    public String getSender() {
        return sender;
    }


    /**
     * Getter method for retrieving the private boolean.
     * @return  the private boolean
     */
    public boolean isPrivate() {
        return priv;
    }


    /**
     * Getter method for retrieving the receiver.
     * @return  the receiver
     */
    public String getReceiver() {
        return receiver;
    }
}
