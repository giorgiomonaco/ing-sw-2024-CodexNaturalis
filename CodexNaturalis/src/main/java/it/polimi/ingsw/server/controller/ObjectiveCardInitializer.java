package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.ObjectiveCard;
import it.polimi.ingsw.server.model.Symbol;
import it.polimi.ingsw.server.model.Game;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class ObjectiveCardInitializer {
    private int count = 1;
    private final Game game;
    //list of every symbol that can be on the card
    private final List<Symbol> symbols;
    //Constructor
    public ObjectiveCardInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }


    /**
     * Initialize objective cards by reading from a JSON file, creating cards, and adding them to the game.
     */
    public void initializeObjectiveCards(){

        ClassLoader cl = this.getClass().getClassLoader();
        String item = "objectiveCards.json";
        InputStream is = cl.getResourceAsStream(item);

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(is, "Couldn't find json file"));
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

                front = "images/objectiveCards/front/" + count + ".png";

                ObjectiveCard objectiveCard = new ObjectiveCard(count, pointsInt, type, card1, direction1, card2, direction2, card3, front);
                addCardToGame(objectiveCard);

                count++;
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't find json file");
        }

    }

    /**
     *
     *  This method gets the card from the constructor and adds it to the game
     *
     * Creates all the initial cards and adds them to the game's initial cards deck.
     *
     *       This method initializes the game by creating and adding cards to the initial cards deck.
     *
     *       List of all resource symbols angles can have:
     *       <ul>
     *         <li>0 = mushroom (resource)</li>
     *        <li>1 = leaf (resource)</li>
     *         <li>2 = fox (resource)</li>
     *         <li>3 = butterfly (resource)</li>
     *         <li>4 = feather (object)</li>
     *         <li>5 = bottle (object)</li>
     *         <li>6 = scroll (object)</li>
     *       </ul>
     *
     * @param card
     */
    public void addCardToGame(ObjectiveCard card){
        game.addObjectiveCardToDeck(card);
    }
}