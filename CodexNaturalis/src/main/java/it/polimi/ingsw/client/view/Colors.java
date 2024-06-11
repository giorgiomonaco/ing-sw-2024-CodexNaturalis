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
            case "black" -> result = lightGrayColor;
            case "purple" -> result = purpleColor;
            case "orange" -> result = orangeColor;
            default -> result = "";
        }

        return result;
    }

    public static String redColor = "\u001B[31m";
    public static String greenColor = "\u001B[32m";
    public static String yellowColor = "\u001B[33m";
    public static String resetColor = "\u001B[0m";
    public static String blueColor = "\u001B[34m";

    //Not used because it's too dark for the TUI
    public static String blackColor = "\u001B[30m";

    public static String purpleColor = "\u001B[35m";
    public static String orangeColor = "\u001B[38;5;208m";
    public static String lightGrayColor = "\u001B[37m";

}
