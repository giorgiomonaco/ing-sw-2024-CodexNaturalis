package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.ObjectiveCard;
import it.polimi.ingsw.server.model.Symbol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectiveCardInitializerTest {
    private ObjectiveCardInitializer initializer;
    private Game game;
    private List<Symbol> symbol;
    private List<ObjectiveCard> objdeck;
    private ObjectiveCard obj;
    @Test
    void initializeObjectiveCards() {
        game = new Game(2);
        symbol = new ArrayList<>();
        objdeck = new ArrayList<>();
        obj = new ObjectiveCard(1,3,"position","orange","down-left","blue","down","blue","null");

        initializer = new ObjectiveCardInitializer(game,symbol);

        initializer.initializeObjectiveCards();
        objdeck = game.getObjectiveDeck();




    }


    @Test
    void addCardToGame() {
    }
}