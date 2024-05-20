package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.*;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.InitialCard;
import it.polimi.ingsw.server.model.Symbol;
import it.polimi.ingsw.server.model.VisibleAngle;

import java.util.List;

public class InitialCardsInitializer {

    private final Game game;

    //List of all the symbols to create the cards - 7, see CardManager for the list
    private final List<Symbol> symbolList;

    //Constructor
    public InitialCardsInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbolList = symbols;
    }

    //Create all the initial cards and add them to the game
    //into the initial cards deck
    public void initializeInitialCards(){


        //crete the list of the corresponding angles to the first initial card:
        VisibleAngle[] firstInitialFrontCardAngles =  new VisibleAngle[4];
        firstInitialFrontCardAngles[0] = new VisibleAngle(symbolList.get(0));
        firstInitialFrontCardAngles[1] = new VisibleAngle(symbolList.get(1));
        firstInitialFrontCardAngles[2] = new VisibleAngle(symbolList.get(2));
        firstInitialFrontCardAngles[3] = new VisibleAngle(symbolList.get(3));
        //create the list of the corresponding back angles of first card: (they are all null)
        VisibleAngle[] firstInitialBackCardAngles = new VisibleAngle[4];
        List<Symbol> list = List.of(symbolList.get(0), symbolList.get(1), symbolList.get(2));

        //create the card using the angles and a symbol for the back
        InitialCard c1 = new InitialCard("A",firstInitialFrontCardAngles,firstInitialBackCardAngles, list);
        InitialCard c2 = new InitialCard("B",firstInitialFrontCardAngles,firstInitialBackCardAngles, list);
        InitialCard c3 = new InitialCard("C", firstInitialFrontCardAngles, firstInitialBackCardAngles, list);
        InitialCard c4 = new InitialCard("D",firstInitialFrontCardAngles,firstInitialBackCardAngles, list);

        //add the cards to the game
        addCardToGame(c1);
        addCardToGame(c2);
        addCardToGame(c3);
        addCardToGame(c4);
    }

    //adding th card to the game
    public void addCardToGame(InitialCard card){
        game.addInitialCardToDeck(card);
    }

}