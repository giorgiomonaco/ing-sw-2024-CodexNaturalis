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
        //what do we need to create a card?
        //A name for the card
        //4 front angles
        //4 back angles
        //A back central symbol

        //create a card is a nightmare
        //We have to create the lists of the 4 front and 4 back angles
        //then we have to create the card giving those as input
        //2000000000 lines for every card
        //there are like 80 cards

        //crete the list of the corresponding angles to the first initial card:
        Angle[] firstInitialFrontCardAngles =  new Angle[4];
        firstInitialFrontCardAngles[0] = new VisibleAngle(symbolList.get(0));
        firstInitialFrontCardAngles[1] = new VisibleAngle(symbolList.get(1));
        firstInitialFrontCardAngles[2] = new VisibleAngle(symbolList.get(2));
        firstInitialFrontCardAngles[3] = new VisibleAngle(symbolList.get(3));
        //create the list of the corresponding back angles of first card: (they are all null)
        Angle[] firstInitialBackCardAngles = new Angle[4];

        //create the card using the angles and a symbol for the back
        InitialCard c1 = new InitialCard("A",firstInitialFrontCardAngles,firstInitialBackCardAngles,symbolList.get(0));

        //crete the list of the corresponding angles to the second initial card:
        Angle[] secondInitialFrontCardAngles =  new Angle[4];
        secondInitialFrontCardAngles[0] = new VisibleAngle(symbolList.get(3));
        secondInitialFrontCardAngles[1] = new VisibleAngle(symbolList.get(0));
        secondInitialFrontCardAngles[2] = new VisibleAngle(symbolList.get(2));
        secondInitialFrontCardAngles[3] = new VisibleAngle(symbolList.get(1));
        //create the list of the corresponding back angles of first card: (all null)
        Angle[] secondInitialBackCardAngles = new Angle[4];

        InitialCard c2 = new InitialCard("B", secondInitialFrontCardAngles, secondInitialBackCardAngles, symbolList.get(1));

        //create the list of angles corresponding to the front of the third card
        Angle[] thirdInitialFrontCardAngles = new Angle[4];
        thirdInitialFrontCardAngles[0] = new VisibleAngle(symbolList.get(1));
        thirdInitialFrontCardAngles[1] = new VisibleAngle(symbolList.get(3));
        thirdInitialFrontCardAngles[2] = new VisibleAngle(symbolList.get(2));
        thirdInitialFrontCardAngles[3] = new VisibleAngle(symbolList.get(0));
        //create the list of all the back angles of the third card
        Angle[] thirdInitialBackCardAngles = new Angle[4];

        InitialCard c3 = new InitialCard("C", thirdInitialFrontCardAngles, thirdInitialBackCardAngles, symbolList.get(2));

        //add the cards to the game
        addCardToGame(c1);
        addCardToGame(c2);
        addCardToGame(c3);
        addCardToGame(c1);
        addCardToGame(c2);
        addCardToGame(c3);
    }

    //adding th card to the game
    public void addCardToGame(InitialCard card){
        game.addInitialCardToDeck(card);
    }

}
