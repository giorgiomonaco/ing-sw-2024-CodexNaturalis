package it.polimi.ingsw.model;


import java.util.*;
public class Token {
    //this class represents the colored tokens of the players

    //Color of the token
    private final String tokenColor;

    //Constructor
    public Token(String color){
        this.tokenColor = color;
    }

    //getter of the color
    public String getTokenColor(){
        return tokenColor;
    }

}
