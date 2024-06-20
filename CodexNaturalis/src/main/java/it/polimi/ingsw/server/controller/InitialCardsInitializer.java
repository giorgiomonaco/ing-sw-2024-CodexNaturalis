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
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InitialCardsInitializer {

    private final Game game;

    //List of all the symbols to create the cards - 7, see CardManager for the list
    private final List<Symbol> symbols;

    private int cardNumber = 1;

    //Constructor
    public InitialCardsInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }

    /**
     * Creates all the initial cards and adds them to the game's initial cards deck.
     *
     * This method initializes the game by creating and adding cards to the initial cards deck.
     *
     * List of all resource symbols angles can have:
     * <ul>
     *   <li>0 = mushroom (resource)</li>
     *   <li>1 = leaf (resource)</li>
     *   <li>2 = fox (resource)</li>
     *   <li>3 = butterfly (resource)</li>
     *   <li>4 = feather (object)</li>
     *   <li>5 = bottle (object)</li>
     *   <li>6 = scroll (object)</li>
     * </ul>
     *
     * Initialize the initial cards by reading from a JSON file, creating cards, and adding them to the game.
     */
    public void initialCardInitializer() {

        ClassLoader cl = this.getClass().getClassLoader();
        String item = "initCard.json";
        InputStream is = cl.getResourceAsStream(item);

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(is, "Couldn't find json file"));
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
                            initialCardBackAngles[j] = new VisibleAngle(symbols.get(0));
                            break;
                        case "leaf":
                            initialCardBackAngles[j] = new VisibleAngle(symbols.get(1));
                            break;
                        case "fox":
                            initialCardBackAngles[j] = new VisibleAngle(symbols.get(2));
                            break;
                        case "butterfly":
                            initialCardBackAngles[j] = new VisibleAngle(symbols.get(3));
                            break;
                        case "empty":
                            initialCardBackAngles[j] = new VisibleAngle(null);
                            break;
                        case "null":
                            initialCardBackAngles[j] = null;
                            break;
                        default:
                            System.out.println("Error while parsing through cards 2");
                    }
                    j++;
                }

                //getting the symbol of the card
                List<Symbol> backSymbol = new ArrayList<>();
                String[] type = {BackSymbol1, BackSymbol2, BackSymbol3};
                for (String symbol : type) {
                    switch (symbol){
                        case "mushroom":
                            backSymbol.add(symbols.getFirst());
                            break;
                        case "leaf":
                            backSymbol.add(symbols.get(1));
                            break;
                        case "fox":
                            backSymbol.add(symbols.get(2));
                            break;
                        case "butterfly":
                            backSymbol.add(symbols.get(3));
                            break;

                        case "null":

                            break;
                        default:
                            System.out.println("Error while parsing through cards 3");
                    }
                }

                String front = "images/initialCards/front/frontInitial-" + cardNumber + ".png";
                String back = "images/initialCards/back/backInitial-" + cardNumber + ".png";

                InitialCard initialCard = new InitialCard(cardNumber, initialCardFrontAngles, initialCardBackAngles, backSymbol, front, back);
                addCardToGame(initialCard);

                cardNumber++;
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't find json file");
        }
    }


    /**
     *  This method gets the card from the constructor and adds it to the game
     * @param card
     */
    public void addCardToGame(InitialCard card){
        game.addInitialCardToDeck(card);
    }



}