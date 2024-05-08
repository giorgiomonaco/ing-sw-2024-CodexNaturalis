package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ObjectiveCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.VisibleAngle;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class EndgameManager {
    Game game;
    Player player;

    //constructor
    public EndgameManager(Game game, Player player){
        this.player = player;
        this.game = game;
    }

    public void objectivePointsCounter(){
        ObjectiveCard obj = this.player.getPlayerObjectiveCard();

        //getting the type of the objective (1. cards position -- 2. points for each resource)
        try {
            switch (obj.getType()){
                case "position":
                    objectiveCreator();
                    break;
                case "mushroom", "fox", "leaf", "butterfly", "feather", "bottle", "scroll", "special":
                    resourceCounter(obj.getType());
                    break;

            }
        }
        catch(NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }


    }
    private void objectiveCreator(){
        return;
    }
    private void resourceCounter(String type){
        if (!Objects.equals(type, "special")){
            int[] resources = player.getResourcesAvailable();
            switch (type) {
                case "mushroom":
                    player.addPoints(Math.floorDiv(resources[0],3));
                    break;
                case "leaf":
                    player.addPoints(Math.floorDiv(resources[1],3));
                    break;
                case "fox":
                    player.addPoints(Math.floorDiv(resources[2],3));
                    break;
                case "butterfly":
                    player.addPoints(Math.floorDiv(resources[3],3));
                    break;
                case "feather":
                    player.addPoints(Math.floorDiv(resources[4],3));
                    break;
                case "bottle":
                    player.addPoints(Math.floorDiv(resources[5],3));
                    break;
                case "scroll":
                    player.addPoints(Math.floorDiv(resources[6],3));
                    break;
            }
        }
    }
    private void specialCounter(){

    }
}
