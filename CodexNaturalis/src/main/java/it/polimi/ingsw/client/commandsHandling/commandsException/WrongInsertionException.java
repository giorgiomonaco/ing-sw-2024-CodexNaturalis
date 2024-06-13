package it.polimi.ingsw.client.commandsHandling.commandsException;

public class WrongInsertionException extends Exception{

    private final String exception;

    public WrongInsertionException(String s){
        exception = s;
    }

    public String getException() {
        return exception;
    }
}
