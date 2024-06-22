package it.polimi.ingsw.network.message;

import it.polimi.ingsw.client.messageHandling.MessageHandler;

import java.io.Serializable;

public abstract class Message implements Serializable {
    /**
     * The header used to recognize the type of message sent.
     */
    private final messEnum type;
    /**
     * The username of the sender of the message.
     */
    private final String username;
    /**
     * This is an optional description that the sender could add to the message.
     */
    private String description;

    /**
     * Constructor for the messages without a description
     * @param messType type of message
     * @param senderUsername username of the sender (it could be also the server)
     */
    public Message(messEnum messType, String senderUsername) {
        type = messType;
        username = senderUsername;
    }

    /**
     * Constructor for the messages with a description
     * @param messType type of message
     * @param senderUsername username of the sender (it could be also the server)
     * @param optDescription optional description that could be printed
     */
    public Message(messEnum messType, String senderUsername, String optDescription) {
        type = messType;
        username = senderUsername;
        description = optDescription;
    }

    public messEnum getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public abstract MessageHandler genHandler();
}
