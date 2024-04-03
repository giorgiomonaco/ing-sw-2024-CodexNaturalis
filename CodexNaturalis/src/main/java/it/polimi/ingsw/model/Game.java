package it.polimi.ingsw.model;

import java.util.List;

public class Game {
    private List<Player> playersList;
    private List<Objective>  commonObj;
    private Deck goldDeck;
    private Deck resDek;
    private Chat gameChat;

    //methods


    public List<Objective> getCommonObj() {
        return commonObj;
    }

    public Player[] getPlayersList() {
        return playersList;
    }

    public void addCommonObjective(Objective object){
        this.commonObj.add(object);
    }
    public void addPlayer(Player player){
        this.playersList.add(player);
    }

    public void playTurn(){
        GameState.nextPlayer();
    }
}
