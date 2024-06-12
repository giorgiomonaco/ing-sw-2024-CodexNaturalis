package it.polimi.ingsw.server.model;

import java.io.Serializable;

public class Chat implements Serializable {
    String sender;
    String msg;

    public Chat(String sender, String msg){
        this.sender = sender;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getSender() {
        return sender;
    }

    public void setMsg(String msg) {
        msg = msg;
    }

    public void setSender(String sender) {
        sender = sender;
    }
}
