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

    //list of all resource symbols angles can have
    //The list:
    //0 = mushroom (res)
    //1 = leaf (res)
    //2 = fox (res)
    //3 = butterfly (res)
    //4 = feather (obj)
    //5 = bottle (obj)
    //6 = scroll (obj)
    private final List<Symbol> symbols = new ArrayList<>();

    //Constructor
    public CardManager(Game game){
        this.game = game;
    }

    //Start the initialization of all the cards into the game:
    //Create and place into the decks of the game all types of cards
    //After creating their managers to do so
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
    // Shuffle all the decks
    private void shuffleDecks() {
        Collections.shuffle(game.getResourceDeck());
        Collections.shuffle(game.getGoldDeck());
        Collections.shuffle(game.getObjectiveDeck());
        Collections.shuffle(game.getInitialDeck());
    }


    //getter of the symbol list
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
        Symbol sword = new ObjectSymbol("sword", "object");
        Symbol bottle = new ObjectSymbol("bottle", "object");
        Symbol scroll = new ObjectSymbol("scroll", "object");

        //we add all the Symbols to the
        symbols.add(sword);
        symbols.add(bottle);
        symbols.add(scroll);

    }
    public void drawUncoveredCards(){
        game.drawUncoveredCards();
    }
}
