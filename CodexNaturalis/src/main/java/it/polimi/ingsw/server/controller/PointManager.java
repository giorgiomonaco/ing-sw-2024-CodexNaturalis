package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GoldCard;
import it.polimi.ingsw.server.model.ResourceCard;
import it.polimi.ingsw.client.view.ViewTry;

public class PointManager {
    private final Game game;
    private final ViewTry view;

    //Constructor
    public PointManager(Game game, ViewTry view){
        this.game = game;
        this.view = view;
    }

    /*
    Method to calculate points given by
    playing a gold card
     */
    public void givePoints(Card card){
        int points = 0;
        /*
        We first retrieve the points the gard gives
         */
        if(card instanceof ResourceCard){
            points = ((ResourceCard) card).getCardPoints();
        } else if (card instanceof GoldCard){
            points = ((GoldCard) card).getCardPoints();
        }
        /*
        then we assign them to the player
         */
        assignPoints(points);
        /*
        Finally we inform the player about the points we gave him
        And current state of his points
         */
        view.informAboutPoints(points);
    }

    /*
    Method to assign the points
     */
    public void assignPoints(int points){
        game.getCurrentPlayer().addPoints(points);
    }


}
