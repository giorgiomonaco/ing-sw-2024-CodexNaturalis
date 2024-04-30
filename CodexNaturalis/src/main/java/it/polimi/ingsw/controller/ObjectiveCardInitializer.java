package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.VisibleAngle;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ObjectiveCard;
import it.polimi.ingsw.model.Symbol;

import java.util.ArrayList;
import java.util.List;

public class ObjectiveCardInitializer {
    private final Game game;
    //list of every symbol that can be on the card
    private final List<Symbol> symbols;
    //Constructor
    public ObjectiveCardInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }

    //Method to initialize the obj cards, creation and call to add card to game
    public void initializeObjectiveCards() {
        //Creation of the cards
        //we need:
        //only points and conditions to give those points

        //!!angles and back symbol are non existing => null
        VisibleAngle[] firstObjectiveCardFrontAngles = new VisibleAngle[4];
        VisibleAngle[] firstObjectiveCardBackAngles = new VisibleAngle[4];
        //We create the point conditions
        Symbol firstConditionSymbol = symbols.get(4);
        Symbol secondConditionSymbol = symbols.get(5);
        //create the list of all symbols on which assign points
        List<Symbol> conditionSymbols = new ArrayList<>();
        //adding the symbols to the list
        conditionSymbols.add(firstConditionSymbol);
        conditionSymbols.add(secondConditionSymbol);
        //Create the points the card gives if conditions matched
        int firstObjectiveCardPoints = 2;
        //Create the card
        ObjectiveCard firstObjectiveCard = new ObjectiveCard("first", firstObjectiveCardPoints, conditionSymbols,
                firstObjectiveCardFrontAngles, firstObjectiveCardBackAngles, null);
        //finally add the card to the objective game deck
        addCardToGame(firstObjectiveCard);

        //------------------------------------------------
        addCardToGame(firstObjectiveCard);
        addCardToGame(firstObjectiveCard);
        addCardToGame(firstObjectiveCard);
        addCardToGame(firstObjectiveCard);
        addCardToGame(firstObjectiveCard);
        addCardToGame(firstObjectiveCard);
        addCardToGame(firstObjectiveCard);
        //------------------------------------------------




    }

    //we add them to the obj card deck of the game
    public void addCardToGame(ObjectiveCard card){
        game.addObjectiveCardToDeck(card);
    }

}
