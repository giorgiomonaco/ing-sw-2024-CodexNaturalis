import java.util.ArrayList;
import java.util.List;

public class ResourceCardInitializer {
    private final Game game;
    //list of all the symbols may be present at card angles
    //see card manager to get the list
    private final List<Symbol> symbols;

    //constructor
    public ResourceCardInitializer(Game game, List<Symbol> symbols){
        this.game = game;
        this.symbols = symbols;
    }

    //Initialize cards, creation and call to adder to deck
    public void initializeResourceCards(){
        //we create the cards,
        //then we call the adder to add them into the starting deck

        //To create the card we need :
        //4 front angles
        //4 back angles
        //The back symbol
        //the points the card gives when played

        //Creation of the first card
        //4 angles
        Angle[] firstResourceCardAngles = new Angle[4];
        firstResourceCardAngles[0] = new VisibleAngle(symbols.get(0));
        firstResourceCardAngles[1] = new VisibleAngle(symbols.get(3));
        firstResourceCardAngles[2] = new VisibleAngle(symbols.get(2));
        firstResourceCardAngles[3] = new VisibleAngle(null); //visible but empty

        //then we create the back angles
        Angle[] firstResourceCardBackAngles = new Angle[4];
        firstResourceCardBackAngles[0] = new VisibleAngle(symbols.get(2));
        firstResourceCardBackAngles[1] = new VisibleAngle(symbols.get(0));
        firstResourceCardBackAngles[2] = new VisibleAngle(symbols.get(1));
        firstResourceCardBackAngles[3] = null; //not visible angle

        //then we create the back symbol
        Symbol firstResourceCardBackSymbol = symbols.get(2);

        //then we create the points the card gives
        int firstResourceCardPoints = 0;

        //finally we create the card
        ResourceCard firstResourceCard = new ResourceCard("first", firstResourceCardPoints, firstResourceCardAngles,
                firstResourceCardBackAngles, firstResourceCardBackSymbol);
        //then we call the card adder to add it to the game deck of resource cards
        addCardToGame(firstResourceCard);

        //_-------------------------------------------------------------
        addCardToGame(firstResourceCard);
        addCardToGame(firstResourceCard);
        addCardToGame(firstResourceCard);
        addCardToGame(firstResourceCard);
        addCardToGame(firstResourceCard);
        addCardToGame(firstResourceCard);
        addCardToGame(firstResourceCard);
        addCardToGame(firstResourceCard);


        //_-------------------------------------------------------------


    }

    //add the card to the resource deck
    public void addCardToGame(ResourceCard card){
        game.addResourceCardToDeck(card);
    }


}
