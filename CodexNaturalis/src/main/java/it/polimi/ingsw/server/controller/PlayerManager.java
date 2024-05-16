package it.polimi.ingsw.server.controller;


import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.client.view.ViewTry;

public class PlayerManager {
    //This class manages the creation of players based on how many wants to play,
    //and it adds the players to the game
    private final Game game;

    //Constructor
    public PlayerManager(Game game){
        this.game = game;
    }
    public void addPlayerToGame(String playerName){
        Player p = new Player(game,playerName);
        game.addPlayer(p);
    }

}
