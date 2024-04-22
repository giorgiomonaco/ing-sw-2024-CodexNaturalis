package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GoldCardsInitializer {
    private final Game game;

    //list of the symbols to create the cards
    //remember they are 7 - see card manager for the list
    private final List<Symbol> symbols;
    //Constructor
    public GoldCardsInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }

    //Method to initialize the gold cards, creation and call to add card to game
    public void initializeResourceCards() {
        try (Reader reader = new FileReader("CodexNaturalis/deliverables/goldCards.json");
             JsonReader jsonReader = Json.createReader(reader)) {

            JsonArray jsonArray = jsonReader.readArray();

            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                // Store the values of each JSON object
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

                // Creating the card front angles
                Angle[] resCardFrontAngles =  new Angle[4];

                String[] corners = {topLeft, topRight, bottomLeft, bottomRight};
                int i = 0;

                // Automation of card angles creation

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

                //getting the symbol of the card
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

                //getting the card condition for points
                int condition;
                switch (pointsForEach){
                    case "false":
                        condition = 0;
                        break;
                    case "coveredAngle":
                        condition = 1;
                        break;
                    case "feather":
                        condition = 2;
                        break;
                    case "bottle":
                        condition = 3;
                        break;
                    case "scroll":
                        condition = 4;
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
        }
    }

    //we add them to the obj card deck of the game
    public void addCardToGame(GoldCard card){
        game.addGoldCardToDeck(card);
    }
}
