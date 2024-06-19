package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.controller.PointTracker;
import it.polimi.ingsw.server.model.Token;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PointTrackerFrame extends JFrame {
    /*
    This class manages the displacement of the point tracker,
    and it places the tokens on top of the right cell
     */

    //We define dimensions of tokens
    private final static int token_X = 50;
    private final static int token_Y = 50;

    private static int dim_X;
    private static int dim_Y;

    //We need to store the coordinates on the image of every box
    private static java.util.List<Coordinates> boxesCoordinates = new ArrayList<>();

    private Client client;
    public PointTrackerFrame(Client c){
        this.client = c;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); //to place in an absolute spot we use no layout
        /*
        we want to get the dimensions of the image of the point tracker
        to use them for the frame size:
        Creating the image icon
        retrieve dimensions
        use them
         */

        ImageIcon originalImage = new ImageIcon("images/PointTracker/PointTracker.jpg");
        dim_X = originalImage.getIconWidth();
        dim_Y = originalImage.getIconHeight();
        setSize(dim_X, dim_Y);
        setResizable(false);
        setVisible(true);
        //populates the boxesCoordinates Array
        defineCoordinates();
        //now we displace everything we have to
        addElementsToTracker();
    }


    //class for the coordinates, used only here so create it here
    private static class Coordinates{

        public Coordinates(int x, int y){
            setX(x);
            setY(y);
        }
        private int x;
        private int y;

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public void setBoxesCoordinates(Coordinates c ){
        boxesCoordinates.add(c);
    }

    public static java.util.List<Coordinates> getBoxesCoordinates() {
        return boxesCoordinates;
    }

    /*
        This populates the boxesCoordinates array list
        index = which box
        value = x and y wrt to the whole image (in % to be consistent if frame changed in size)
    */
    private void defineCoordinates(){
        //just a fucking list of couples fof ints from 0 to 100
        setBoxesCoordinates(new Coordinates(105, 875));
        setBoxesCoordinates(new Coordinates(240, 875));
        setBoxesCoordinates(new Coordinates(350, 875));

        setBoxesCoordinates(new Coordinates(410, 775));
        setBoxesCoordinates(new Coordinates(300, 775));
        setBoxesCoordinates(new Coordinates(190, 775));
        setBoxesCoordinates(new Coordinates(80, 775));

        setBoxesCoordinates(new Coordinates(80, 675));
        setBoxesCoordinates(new Coordinates(190, 675));
        setBoxesCoordinates(new Coordinates(300, 675));
        setBoxesCoordinates(new Coordinates(410, 675));

        setBoxesCoordinates(new Coordinates(410, 575));
        setBoxesCoordinates(new Coordinates(300, 575));
        setBoxesCoordinates(new Coordinates(190, 575));
        setBoxesCoordinates(new Coordinates(80, 575));

        setBoxesCoordinates(new Coordinates(80, 475));
        setBoxesCoordinates(new Coordinates(190, 475));
        setBoxesCoordinates(new Coordinates(300, 475));
        setBoxesCoordinates(new Coordinates(410, 475));

        setBoxesCoordinates(new Coordinates(410, 375));
        setBoxesCoordinates(new Coordinates(300, 325));
        setBoxesCoordinates(new Coordinates(190, 375));

        setBoxesCoordinates(new Coordinates(80, 275));

        setBoxesCoordinates(new Coordinates(80, 175));

        setBoxesCoordinates(new Coordinates(140, 90));

        setBoxesCoordinates(new Coordinates(250, 75));

        setBoxesCoordinates(new Coordinates(350, 90));

        setBoxesCoordinates(new Coordinates(410, 175));

        setBoxesCoordinates(new Coordinates(410, 275));

        setBoxesCoordinates(new Coordinates(200, 250));
    }

    private void addElementsToTracker(){
        /*
        we must add:
        every token at right coordinate, given by the points of the player
         */

        //we do this for every player
        for(int i = 0; i < client.getPlayerList().size()-1; i++){
            JLabel token = null; //creating the label for the token
            token.setSize(token_X, token_Y);
            ImageIcon tokenIcon = null; //we assume to be able to access the player list for the players token;
            Image resizedTokenImage = tokenIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            token.setIcon(tokenIcon);
            int points = 0; //we assume to be able to access to the score of every player
            placeToken(token, points);
        }

    }

    //we place the token in right spot
    private void placeToken(JLabel token, int score){
        //first we transform score->coordinates
        Coordinates tokenPosition = getBoxesCoordinates().get(score);
        //now we calculate the position of the top left corner of every label
        int cornerX = dim_X - tokenPosition.getX() - token.getWidth()/2;
        int cornerY = dim_Y - tokenPosition.getY() - token.getHeight()/2;

        //set bounds of the label
        token.setBounds(cornerX, cornerY, token.getWidth(), token.getHeight());
        //Add the token to the frame
        add(token);
        setVisible(true);
    }

}
