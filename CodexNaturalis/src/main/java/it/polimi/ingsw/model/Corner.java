package it.polimi.ingsw.model;

public class Corner {
    public boolean covered;
    private Card linkedUnderCard;
    private Card linkedOverCard;

    public boolean linkCard(Card CartaSotto, Card CartaSopra){
        this.linkedUnderCard = CartaSotto;
        this.linkedOverCard = CartaSopra;
        return true;
    }
}
