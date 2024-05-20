package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.InitialCard;
import it.polimi.ingsw.server.model.Symbol;
import it.polimi.ingsw.server.model.VisibleAngle;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class InitialCardsInitializer {

    private final Game game;

    //List of all the symbols to create the cards - 7, see CardManager for the list
    private final List<Symbol> symbols;

    private int cardNumber = 0;

    //Constructor
    public InitialCardsInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }

    //Create all the initial cards and add them to the game
    //into the initial cards deck
    //list of all resource symbols angles can have
    //The list:
    //0 = mushroom (res)
    //1 = leaf (res)
    //2 = fox (res)
    //3 = butterfly (res)
    //4 = feather (obj)
    //5 = bottle (obj)
    //6 = scroll (obj)
    public void initialCardInitializer() {

        String osName = System.getProperty("os.name").toLowerCase();
        String basePath = "src/main/resources/initCards.json";
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
                String FrontTopLeft = jsonObject.getString("FrontTopLeft");
                String FrontTopRight = jsonObject.getString("FrontTopRight");
                String FrontBottomLeft = jsonObject.getString("FrontBottomLeft");
                String FrontBottomRight = jsonObject.getString("FrontBottomRight");
                String BackTopLeft = jsonObject.getString("BackTopLeft");
                String BackTopRight = jsonObject.getString("BackTopRight");
                String BackBottomLeft = jsonObject.getString("BackBottomLeft");
                String BackBottomRight = jsonObject.getString("BackBottomRight");
                String BackSymbol1 = jsonObject.getString("BackSymbol1");
                String BackSymbol2 = jsonObject.getString("BackSymbol2");
                String BackSymbol3 = jsonObject.getString("BackSymbol3");

                // Creating the card front angles
                VisibleAngle[] initialCardFrontAngles =  new VisibleAngle[4];
                String[] frontCorners = {FrontTopLeft, FrontTopRight, FrontBottomLeft, FrontBottomRight};
                int i = 0;
                // Automation of card angles creation
                for (String corner : frontCorners) {
                    switch (corner) {
                        case "mushroom":
                            initialCardFrontAngles[i] = new VisibleAngle(symbols.get(0));
                            break;
                        case "leaf":
                            initialCardFrontAngles[i] = new VisibleAngle(symbols.get(1));
                            break;
                        case "fox":
                            initialCardFrontAngles[i] = new VisibleAngle(symbols.get(2));
                            break;
                        case "butterfly":
                            initialCardFrontAngles[i] = new VisibleAngle(symbols.get(3));
                            break;
                        case "feather":
                            initialCardFrontAngles[i] = new VisibleAngle(symbols.get(4));
                            break;
                        case "bottle":
                            initialCardFrontAngles[i] = new VisibleAngle(symbols.get(5));
                            break;
                        case "scroll":
                            initialCardFrontAngles[i] = new VisibleAngle(symbols.get(6));
                            break;
                        case "empty":
                            initialCardFrontAngles[i] = new VisibleAngle(null);
                            break;
                        case "null":
                            initialCardFrontAngles[i] = null;
                            break;
                        default:
                            System.out.println("Error while parsing through cards");
                    }
                    i++;
                }

                // Creating backside angles
                VisibleAngle[] initialCardBackAngles =  new VisibleAngle[4];
                String[] backCorners = {BackTopLeft, BackTopRight, BackBottomLeft, BackBottomRight};
                int j = 0;
                // Automation of card angles creation
                for (String corner : backCorners) {
                    switch (corner) {
                        case "mushroom":
                            initialCardBackAngles[i] = new VisibleAngle(symbols.get(0));
                            break;
                        case "leaf":
                            initialCardBackAngles[i] = new VisibleAngle(symbols.get(1));
                            break;
                        case "fox":
                            initialCardBackAngles[i] = new VisibleAngle(symbols.get(2));
                            break;
                        case "butterfly":
                            initialCardBackAngles[i] = new VisibleAngle(symbols.get(3));
                            break;
                        case "feather":
                            initialCardBackAngles[i] = new VisibleAngle(symbols.get(4));
                            break;
                        case "bottle":
                            initialCardBackAngles[i] = new VisibleAngle(symbols.get(5));
                            break;
                        case "scroll":
                            initialCardBackAngles[i] = new VisibleAngle(symbols.get(6));
                            break;
                        case "empty":
                            initialCardBackAngles[i] = new VisibleAngle(null);
                            break;
                        case "null":
                            initialCardBackAngles[i] = null;
                            break;
                        default:
                            System.out.println("Error while parsing through cards");
                    }
                    j++;
                }

                //getting the symbol of the card
                List<Symbol> backSymbol = new ArrayList<>();
                String[] type = {BackSymbol1, BackSymbol2, BackSymbol3};
                for (String symbol : type) {
                    switch (symbol){
                        case "orange":
                            backSymbol.add(symbols.getFirst());
                            break;
                        case "green":
                            backSymbol.add(symbols.get(1));
                            break;
                        case "blue":
                            backSymbol.add(symbols.get(2));
                            break;
                        case "purple":
                            backSymbol.add(symbols.get(3));
                            break;
                        default:
                            System.out.println("Error while parsing through cards");
                    }
                }


                InitialCard initialCard = new InitialCard(cardNumber, initialCardFrontAngles, initialCardBackAngles, backSymbol);
                addCardToGame(initialCard);

                cardNumber++;
            }
        }
        catch (IOException e) {
            e.getCause();
        }
    }


    //adding th card to the game
    public void addCardToGame(InitialCard card){
        game.addInitialCardToDeck(card);
    }



}