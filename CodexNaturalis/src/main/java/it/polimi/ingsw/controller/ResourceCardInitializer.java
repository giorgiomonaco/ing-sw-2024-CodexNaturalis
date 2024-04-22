package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ResourceCard;
import it.polimi.ingsw.model.Symbol;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ResourceCardInitializer {
    private final Game game;
    // List of all the symbols may be present at card angles
    // See card manager to get the list
    private final List<Symbol> symbols;

    // Constructor
    public ResourceCardInitializer(Game game, List<Symbol> symbols) {
        this.game = game;
        this.symbols = symbols;
    }

    // Initialize cards, creation and call to adder to deck
    public void initializeResourceCards() {
        try (Reader reader = new FileReader("file.json");
             JsonReader jsonReader = Json.createReader(reader)) {

            JsonArray jsonArray = jsonReader.readArray();

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                // Read the values of each JSON object
                String topLeft = jsonObject.getString("topLeft");
                String topRight = jsonObject.getString("topRight");
                String bottomLeft = jsonObject.getString("bottomLeft");
                String bottomRight = jsonObject.getString("bottomRight");
                String pointsForEach = jsonObject.getString("pointsForEach");
                String points = jsonObject.getString("points");
                String reqMushroom = jsonObject.getString("reqMushroom");
                String reqLeaf = jsonObject.getString("reqLeaf");
                String reqFox = jsonObject.getString("reqFox");
                String reqButterfly = jsonObject.getString("reqButterfly");
                String type = jsonObject.getString("type");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add the card to the resource deck
    public void addCardToGame(ResourceCard card) {
        game.addResourceCardToDeck(card);
    }
}
