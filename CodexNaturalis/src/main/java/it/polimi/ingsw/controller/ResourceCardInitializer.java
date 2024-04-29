package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

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

    private int cardNumber = 0;


    // Constructor
    public ResourceCardInitializer(Game game, List<Symbol> symbols) {
        this.game = game;
        this.symbols = symbols;
    }

    // Initialize cards, creation and call to adder to deck
    public void initializeResourceCards() {
        try (Reader reader = new FileReader("/Users/giorgiomonaco/Desktop/ProgIngSw/CodexNaturalis/deliverables/resCards.json");
             JsonReader jsonReader = Json.createReader(reader)) {

            JsonArray jsonArray = jsonReader.readArray();

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                // Store the values of each JSON object
                String topLeft = jsonObject.getString("topLeft");
                String topRight = jsonObject.getString("topRight");
                String bottomLeft = jsonObject.getString("bottomLeft");
                String bottomRight = jsonObject.getString("bottomRight");
                String points = jsonObject.getString("points");
                String type = jsonObject.getString("type");

                // Creating the card
                Angle[] resCardFrontAngles =  new Angle[4];

                String[] corners = {topLeft, topRight, bottomLeft, bottomRight};
                int i = 0;

                // Automation of card creation

                for (String corner : corners) {
                    switch (corner) {
                        case "mushroom":
                            resCardFrontAngles[i] = new VisibleAngle(symbols.get(0));
                            break;
                        case "leaf":
                            resCardFrontAngles[i] = new VisibleAngle(symbols.get(1));
                            break;
                        case "fox":
                            resCardFrontAngles[i] = new VisibleAngle(symbols.get(2));
                            break;
                        case "butterfly":
                            resCardFrontAngles[i] = new VisibleAngle(symbols.get(3));
                            break;
                        case "feather":
                            resCardFrontAngles[i] = new VisibleAngle(symbols.get(4));
                            break;
                        case "bottle":
                            resCardFrontAngles[i] = new VisibleAngle(symbols.get(5));
                            break;
                        case "scroll":
                            resCardFrontAngles[i] = new VisibleAngle(symbols.get(6));
                            break;
                        case "empty":
                            resCardFrontAngles[i] = new VisibleAngle(null);
                            break;
                        case "null":
                            resCardFrontAngles[i] = null;
                            break;
                        default:
                            System.out.println("Error while parsing through cards");
                    }
                    i++;
                }
                // Creating backside angles
                Angle[] resCardBackAngles = new Angle[4];
                resCardBackAngles[0] = new VisibleAngle(null);
                resCardBackAngles[1] = new VisibleAngle(null);
                resCardBackAngles[2] = new VisibleAngle(null);
                resCardBackAngles[3] = new VisibleAngle(null);

                // Getting the points of the card

                int pointsInt = Integer.parseInt(points);


                Symbol backSymbol = null;

                switch (type){
                    case "orange":
                        backSymbol = symbols.get(0);
                        break;
                    case "green":
                        backSymbol = symbols.get(1);
                        break;
                    case "blue":
                        backSymbol = symbols.get(2);
                        break;
                    case "purple":
                        backSymbol = symbols.get(3);
                        break;
                    default:
                        System.out.println("Error while parsing through cards");
                }

                ResourceCard resourceCard = new  ResourceCard( cardNumber,  pointsInt,
                resCardFrontAngles, resCardBackAngles, backSymbol);
                addCardToGame(resourceCard);

                cardNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();

            throw new RuntimeException("Impossibile caricare le carte.");
        }
    }

    // Add the card to the resource deck
    public void addCardToGame(ResourceCard card) {
        game.addResourceCardToDeck(card);
    }
}
