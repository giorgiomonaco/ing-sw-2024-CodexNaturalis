package it.polimi.ingsw.client.view;

import java.io.Serializable;

public class Colors {

    public String getColor(String s){

        String result;

        switch(s){
            case "red" -> result = redColor;
            case "green" -> result = greenColor;
            case "yellow" -> result = yellowColor;
            case "blue" -> result = blueColor;
            case "black" -> result = blackColor;
            default -> result = "";
        }

        return result;
    }
    public String redColor = "\u001B[31m";
    public String greenColor = "\u001B[32m";
    public String yellowColor = "\u001B[33m";
    public String resetColor = "\u001B[0m";
    public String blueColor = "\u001B[34m";
    public String blackColor = "\u001B[30m";
    public String purpleColor = "\u001B[35m";
    public String orangeColor = "\u001B[36m";
    public String lightGrayColor = "\u001B[37m";

}
