package it.polimi.ingsw.model;


import java.util.*;
public class CommonBoard {
    //Board common to every player
    //Where are displaced the discovered cards

    private final Game game;

    //All the discovered cards
    private final List<ResourceCard> discoveredResourceCards = new ArrayList<>();

    //All the discovered gold cards
    private final List<GoldCard> discoveredGoldCards = new ArrayList<>();

    //Common discovered objective cards
    private final List<ObjectiveCard> commonObjectiveDiscoveredCards = new ArrayList<>();

    //The point tracker for the game
    private PointTracker pointTracker;

    //Constructor
    public CommonBoard(Game game){
        this.game = game;
    }



    //get the list of the resource discovered cards
    public List<ResourceCard> getDiscoveredSourceCards() {
        return discoveredResourceCards;
    }

    //add a source card from the deck to discovered ones
    public void addDiscoveredSourceCard(ResourceCard card){
        discoveredResourceCards.add(card);
    }


    //get of the discovered gold cards
    public List<GoldCard> getDiscoveredGoldCards() {
        return discoveredGoldCards;
    }

    //Add a gold card to the discovered ones
    public void addDiscoveredGoldCard(GoldCard card){
        discoveredGoldCards.add(card);
    }

    //Get the discovered objective cards
    public List<ObjectiveCard> getCommonObjectiveDiscoveredCards(){
        return commonObjectiveDiscoveredCards;
    }

    //Adder of a common objective
    public void addCommonObjectiveDiscoveredCard(ObjectiveCard card){
        commonObjectiveDiscoveredCards.add(card);
    }

    //Set the point tracker
    public void setPointTracker(PointTracker pointTracker){
        this.pointTracker = pointTracker;
    }

    //getter of the point tracker
    public PointTracker getPointTracker() {
        return pointTracker;
    }
}
