package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.server.model.Symbol;
import it.polimi.ingsw.server.model.VisibleAngle;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ResourceCardInitializer {
    public final Game game;
    // List of all the symbols may be present at card angles
    // See card manager to get the list
    public final List<Symbol> symbols;

    public int cardNumber = 1;


    // Constructor
    public ResourceCardInitializer(Game game, List<Symbol> symbols) {
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

     * Initialize resource cards by reading from a JSON file, creating cards, and adding them to the game.
     */
    public void initializeResourceCards() {

        ClassLoader cl = this.getClass().getClassLoader();
        String item = "resCards.json";
        InputStream is = cl.getResourceAsStream(item);

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(is, "Couldn't find json file"));
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
                VisibleAngle[] resCardFrontAngles =  new VisibleAngle[4];

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
                VisibleAngle[] resCardBackAngles = new VisibleAngle[4];
                resCardBackAngles[0] = new VisibleAngle(null);
                resCardBackAngles[1] = new VisibleAngle(null);
                resCardBackAngles[2] = new VisibleAngle(null);
                resCardBackAngles[3] = new VisibleAngle(null);

                // Getting the points of the card

                int pointsInt = Integer.parseInt(points);


                List<Symbol> backSymbol =new ArrayList<>();

                switch (type){
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

                String front;
                String back;

                front = "images/resCards/front/" + cardNumber + ".png";
                back = "images/resCards/back/" + type + ".png";

                ResourceCard resourceCard = new  ResourceCard( cardNumber,  pointsInt,
                        resCardFrontAngles, resCardBackAngles, backSymbol, front, back);
                addCardToGame(resourceCard);

                cardNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't find json file");
        }
    }

    /**
     * This method gets the card from the constructor and adds it to the game
     * @param card
     */
    public void addCardToGame(ResourceCard card) {
        game.addResourceCardToDeck(card);
    }
}