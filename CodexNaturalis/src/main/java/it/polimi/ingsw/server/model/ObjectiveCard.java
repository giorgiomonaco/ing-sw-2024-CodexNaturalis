package it.polimi.ingsw.server.model;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

//manca check durante piazzamento della carta con risorse necessarie e del player
public class ObjectiveCard implements Serializable {
    private final int cardNumber;
    private final int points;
    private final String type;
    private final String card1;
    private final String direction1;
    private final String card2;
    private final String direction2;
    private final String card3;
    private String image;



    /**
     * Constructs an ObjectiveCard object with specified attributes.
     *
     * @param num the number of the card
     * @param points the points awarded by the card
     * @param type the type of the objective card
     * @param card1 the first card to look for if the type is "position"
     * @param direction1 the direction associated with the first card
     * @param card2 the second card to look for if the type is "position"
     * @param direction2 the direction associated with the second card
     * @param card3 the third card to look for if the type is "position"
     * @param path the path to the image associated with the card
     */
    public ObjectiveCard(int num, int points, String type, String card1, String direction1, String card2, String direction2, String card3, String path) {
        this.type = type;
        this.card1 = card1;
        this.direction1 = direction1;
        this.card2 = card2;
        this.direction2 = direction2;
        this.card3 = card3;
        this.points = points;
        this.cardNumber = num;
        this.image = path;
    }

    public int getPoints() {
        return points;
    }

    public String getCard1() {
        return card1;
    }

    public String getCard2() {
        return card2;
    }

    public String getCard3() {
        return card3;
    }

    public String getDirection1() {
        return direction1;
    }

    public String getDirection2() {
        return direction2;
    }

    public String getType() {
        return type;
    }
    public int getCardName(){
        return this.cardNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
