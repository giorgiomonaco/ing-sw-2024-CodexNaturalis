package it.polimi.ingsw.model;


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
    public void initializeGoldCards() {
        //creation of the first card
        //We have to crete the 4 front angles for the specific card
        Angle[] firstGoldCardFrontAngles =  new Angle[4];
        firstGoldCardFrontAngles[0] = new VisibleAngle(symbols.get(0));
        firstGoldCardFrontAngles[1] = new VisibleAngle(symbols.get(1));
        firstGoldCardFrontAngles[2] = new VisibleAngle(symbols.get(2));
        firstGoldCardFrontAngles[3] = new VisibleAngle(symbols.get(3));

        //then we create the 4 back angles for the card
        Angle[] firstGoldCardBackAngles = new Angle[4];
        firstGoldCardBackAngles[0] = new VisibleAngle(symbols.get(0));
        firstGoldCardBackAngles[1] = null; //This is a not visible angle
        firstGoldCardBackAngles[2] = new VisibleAngle(symbols.get(2));
        firstGoldCardBackAngles[3] = new VisibleAngle(null); //this is a visible angle but empty

        //We create the var with the point the card gives if played
        int firstGoldCardFrontPoints = 1;
        //We create the list of needed symbols on the board to
        List<Symbol> neededSymbols = new ArrayList<>();
        neededSymbols.add(symbols.get(3));
        neededSymbols.add(symbols.get(5));

        //now we can create the card
        GoldCard firstGoldCard = new GoldCard("first",firstGoldCardFrontAngles,
                firstGoldCardBackAngles, symbols.get(2), firstGoldCardFrontPoints, neededSymbols);
        //then we add the card to the game
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);
        addCardToGame(firstGoldCard);


        //-----------------------------------Only for fun ----------------------------------------------


        //-----------------------------------End of fun--------------------------------


    }

    //we add them to the obj card deck of the game
    public void addCardToGame(GoldCard card){
        game.addGoldCardToDeck(card);
    }
}
