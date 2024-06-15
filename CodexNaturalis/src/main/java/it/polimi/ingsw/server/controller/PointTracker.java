package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Token;

import java.util.ArrayList;
import java.util.List;

public class PointTracker {
    //class representing the point tracker
    //it has 29 boxes representing the points of each player
    //tokens are placed on the point tracker
    //they are moved when points are scored

    //it has an array of tokens
    //length equal the max points and index = current points of the player
    //How to have multiple tokens in the same box? IDK
    //I can use an array of arraylists to keep track of how many in same box

    //Array of arraylist representing: the array -> points
    //the arraylists = which token are in that box
    //We didn't set the max length of the point tracker, so it has to be checked
    private final List<ArrayList<Token>> pointBoxes = new ArrayList<>();

    //getter of the tokens inside a certain box
    public ArrayList<Token> getTokenBox(int index){
        return pointBoxes.get(index);
    }

    //add a box to the point tracker
    public void addBoxToTracker(ArrayList<Token> box){
        pointBoxes.add(box);
    }

    //getter of the point box list
    public List<ArrayList<Token>> getPointBoxes(){
        return pointBoxes;
    }

}
