package it.polimi.ingsw.model;


import java.util.ArrayList;
import java.util.List;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ResourceCardInitializer {
    private final Game game;
    //list of all the symbols may be present at card angles
    //see card manager to get the list
    private final List<Symbol> symbols;

    //constructor
    public ResourceCardInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }

    //Initialize cards, creation and call to adder to deck
    public void initializeResourceCards(){
        try (Reader reader = new FileReader("file.json");
             JsonReader jsonReader = Json.createReader(reader)) {

            JsonArray jsonArray = jsonReader.readArray();

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                // Leggi i valori di ogni oggetto JSON
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

                // Fai qualcosa con i valori, ad esempio stampali a schermo
                System.out.println("Top Left: " + topLeft);
                System.out.println("Top Right: " + topRight);
                System.out.println("Bottom Left: " + bottomLeft);
                System.out.println("Bottom Right: " + bottomRight);
                System.out.println("Points For Each: " + pointsForEach);
                System.out.println("Points: " + points);
                System.out.println("Required Mushroom: " + reqMushroom);
                System.out.println("Required Leaf: " + reqLeaf);
                System.out.println("Required Fox: " + reqFox);
                System.out.println("Required Butterfly: " + reqButterfly);
                System.out.println("Type: " + type);
                System.out.println("----------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
    }
    //add the card to the resource deck
    public void addCardToGame(ResourceCard card){
        game.addResourceCardToDeck(card);
    }


}
