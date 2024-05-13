package it.polimi.ingsw.server.model;


public abstract class Symbol {
    //the Symbol has its name, representing the symbol
    private final String symbolName;
    //The Symbol has a type Object or Resource
    private final String symbolType;

    //Constructor
    public Symbol(String s, String symbolType){
        this.symbolName = s;
        this.symbolType = symbolType;
    }

    //get the symbol
    public String getSymbolName() {
        return symbolName;
    }

    //getter of the symbol type
    public String getSymbolType() {
        return symbolType;
    }
}
