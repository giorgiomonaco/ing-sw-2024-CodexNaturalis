package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    @Test
    void getMsg() {
        Chat chat = new Chat("tom", "ciao");
        assertEquals(chat.getMsg(), "ciao");

    }

    @Test
    void getSender() {
        Chat chat = new Chat("tom", "ciao");
        assertEquals(chat.getSender(), "tom");
    }

    @Test
    void setMsg() {
        Chat chat = new Chat("tom", "ciao");
        assertEquals(chat.getMsg(), "ciao");
    }

    
}