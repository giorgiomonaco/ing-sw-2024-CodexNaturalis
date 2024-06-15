package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.ObjectiveCard;
import it.polimi.ingsw.server.model.Symbol;
import it.polimi.ingsw.server.model.Game;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ObjectiveCardInitializer {
    private int count = 0;
    private final Game game;
    //list of every symbol that can be on the card
    private final List<Symbol> symbols;
    //Constructor
    public ObjectiveCardInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }

    public void initializeObjectiveCards(){

        String osName = System.getProperty("os.name").toLowerCase();
        String basePath = "src/main/resources/objectiveCards.json";
        String addPath = "CodexNaturalis/";
        String finalPath;

        if(osName.contains("mac")){
            finalPath = basePath;
        } else {
            finalPath = addPath + basePath;
        }

        try (Reader reader = new FileReader(finalPath);
             JsonReader jsonReader = Json.createReader(reader)) {

            JsonArray jsonArray = jsonReader.readArray();

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                // Store the values of each JSON object
                String points = jsonObject.getString("points");
                String type = jsonObject.getString("type");
                String card1 = jsonObject.getString("card1");
                String direction1 = jsonObject.getString("direction1");
                String card2 = jsonObject.getString("card2");
                String direction2 = jsonObject.getString("direction2");
                String card3 = jsonObject.getString("card3");


                // Getting the points of the card
                int pointsInt = Integer.parseInt(points);

                String front;

                if(osName.contains("mac")){
                    front = "src/main/resources/images/objectiveCards/front/" + count + ".png";
                } else {
                    front = "CodexNaturalis/src/main/resources/images/objectiveCards/front/" + count + ".png";
                }

                ObjectiveCard objectiveCard = new ObjectiveCard(count, pointsInt, type, card1, direction1, card2, direction2, card3, front);
                addCardToGame(objectiveCard);

                count++;
            }
        }
        catch (IOException e) {
            Throwable cause = e.getCause();
        }

    }

    //we add them to the obj card deck of the game
    public void addCardToGame(ObjectiveCard card){
        game.addObjectiveCardToDeck(card);
    }
}
