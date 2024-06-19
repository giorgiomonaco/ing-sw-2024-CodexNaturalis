package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.ObjectSymbol;
import it.polimi.ingsw.server.model.ResourceSymbol;
import it.polimi.ingsw.server.model.Symbol;
import java.util.Collections;

import java.util.ArrayList;
import java.util.List;

public class CardManager {

    private final Game game;


    private final List<Symbol> symbols = new ArrayList<>();

    //Constructor
    public CardManager(Game game){
        this.game = game;
    }

    /**
     * Initializes all the cards in the game.
     *
     * This method creates and places all types of cards into the game's decks.
     * It also creates the managers to do so.
     *
     * @return void
     */
    public void initializeAllCards() {

        //first we have to create the list of all symbols (resources and objects)
        //to pass it to the managers to create correctly the cards
        createAllSymbols();

        //create the initializer/creator and adder of resource cards to game
        ResourceCardInitializer resourceCardInitializer = new ResourceCardInitializer(game, symbols);
        //initialize the resource cards
        resourceCardInitializer.initializeResourceCards();


        //create initializer/creator and adder of gold cards
        GoldCardsInitializer goldCardsInitializer = new GoldCardsInitializer(game, symbols);
        //Initialize gold cards
        goldCardsInitializer.initializeGoldCards();

        //Create the initializer of the obj cards (the obj cards creator)
        ObjectiveCardInitializer objectiveCardInitializer = new ObjectiveCardInitializer(game, symbols);
        //Create the cards and add them to the game
        objectiveCardInitializer.initializeObjectiveCards();

        //Create the initializer for the initial cards
        InitialCardsInitializer initialCardsInitializer = new InitialCardsInitializer(game, symbols);
        //create the cards and add them to the corresponding deck in the game
        initialCardsInitializer.initialCardInitializer();
        shuffleDecks();
        drawUncoveredCards();
    }


    /**
     * Shuffles all the decks used in the game.

     * After calling this method, the order of cards in each deck will be random.
     */
    private void shuffleDecks() {
        Collections.shuffle(game.getResourceDeck());
        Collections.shuffle(game.getGoldDeck());
        Collections.shuffle(game.getObjectiveDeck());
        Collections.shuffle(game.getInitialDeck());
    }


    /**
     * Creates all the symbols used in the game and adds them to the array of symbols.
     *
     * This method initializes the game by creating all the symbols present, starting
     * with resource symbols followed by object symbols.
     * <br>
     * <br>
     * The resource symbols created are:
     * <ul>
     *   <li>mushroom (resource)</li>
     *   <li>leaf (resource)</li>
     *   <li>fox (resource)</li>
     *   <li>butterfly (resource)</li>
     * </ul>
     *<br>
     * The object symbols created are:
     * <ul>
     *   <li>feather (object)</li>
     *   <li>bottle (object)</li>
     *   <li>scroll (object)</li>
     * </ul>
     *
     * After calling this method, the symbols array will contain all the initialized symbols.
     */
    public List<Symbol> getSymbols() {
        return symbols;
    }

    //creation of all the symbols and add them to the array of symbols
    public void createAllSymbols(){
        //We create all the symbols present in the game
        //first we create all the resource symbols
        Symbol mushroom = new ResourceSymbol("mushroom", "resource");
        Symbol leaf = new ResourceSymbol("leaf", "resource");
        Symbol fox = new ResourceSymbol("fox", "resource");
        Symbol butterfly = new ResourceSymbol("butterfly", "resource");

        //we add all the resource symbols
        symbols.add(mushroom);
        symbols.add(leaf);
        symbols.add(fox);
        symbols.add(butterfly);

        //then we create all the object symbols
        Symbol feather = new ObjectSymbol("feather", "object");
        Symbol bottle = new ObjectSymbol("bottle", "object");
        Symbol scroll = new ObjectSymbol("scroll", "object");

        //we add all the Symbols to the
        symbols.add(feather);
        symbols.add(bottle);
        symbols.add(scroll);

    }

    /**
     * Draws uncovered cards from the game.
     */
    public void drawUncoveredCards(){
        game.drawUncoveredCards();
    }
}
