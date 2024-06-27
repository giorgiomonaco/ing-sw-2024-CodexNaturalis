package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PointTrackerPanel extends JPanel {
    //Dimensions of tokens
    private final static int token_X = 25;
    private final static int token_Y = 25;

    //dimensions of the panel:
    private final static int dim_X = 250;
    private final static  int dim_Y = 520;

    //array to keep track of how many tokens in same box
    private int[] boxesPopulation = new int[30];
    private int[] positionInBox = new int[30];

    //We need to store the coordinates on the image of every box
    private List<Coordinates> boxesCoordinates = new ArrayList<>();
    //Array of images to store the tokens so not retrieve them every time
    private List <JLabel> tokenList = new ArrayList<>();

    //image for background
    Image resizedPointTracker;



    private Client client;
    public PointTrackerPanel(Client c){
        this.client = c;
        setPreferredSize(new Dimension(dim_X, dim_Y));
        setLayout(null);
        setOpaque(true);

        //we load the image needed to fil the panel
        //first thing first we paint the panel with the point tracker image;
        //we retrieve the image we want to displace
        ClassLoader cl = this.getClass().getClassLoader();
        String pathToPointTracker = "images/pointTracker/PointTracker.png";
        InputStream is0 = cl.getResourceAsStream(pathToPointTracker);
        //we put it into an image icon
        ImageIcon x = null;
        try {
            x = new ImageIcon(ImageIO.read(is0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Now we resize the image to have proper dimension of point tracker
        resizedPointTracker = x.getImage().getScaledInstance(dim_X, dim_Y, Image.SCALE_SMOOTH);


        /*
        Now we proceed to:
        define all the coordinates,
        add all the tokens to token list
        place the tokens on the panel
         */
        defineCoordinates();
        addElementsToTracker();
        fillBackgroundPanel();

    }


    //method to add any element into the panel -------------------------------- only method original
    public void addLabelIntoLocation(JLabel label, int label_X, int label_Y, int label_Width, int label_Height){
        //setting bounds
        label.setBounds(label_X, label_Y, label_Width, label_Height);
        label.setOpaque(false);
        add(label);
        revalidate();
        repaint();

    }

    public void fillBackgroundPanel(){
        //we declare local vars for transformations
        Image token0Image;
        Image token1Image;
        Image token2Image;
        Image token3Image;
        ImageIcon resizedToken0;
        ImageIcon resizedToken1;
        ImageIcon resizedToken2;
        ImageIcon resizedToken3;

        //Fist thing first we check how many tokens in every box
        for(int i = 0; i < client.getPlayerList().size(); i++){
            //we increment the population of that box if a player has that score
            boxesPopulation[client.getPoints()[i]]++;
        }

        //we prepare the list of resized tokens here
        List<JLabel> resizedTokens = new ArrayList<>();

        //very ugly way to solve maybe:
        switch(client.getPlayerList().size()){
            case 2:
                //we add the 2 tokens to the resized list
                token0Image = ((ImageIcon)(tokenList.get(0).getIcon())).getImage();
                token1Image = ((ImageIcon)(tokenList.get(1).getIcon())).getImage();
                resizedToken0 = new ImageIcon(token0Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedToken1 = new ImageIcon(token1Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedTokens.add(new JLabel(resizedToken0));
                resizedTokens.add(new JLabel(resizedToken1));
                /*
                //just debug of boxes right coordinates

                for(int i = 0; i < boxesCoordinates.size(); i++){
                    JLabel x = new JLabel("0");
                    x.setBackground(Color.orange);
                    x.setOpaque(true);
                    resizedTokens.add(x);
                }

                 */
                break;
            case 3:
                token0Image = ((ImageIcon)(tokenList.get(0).getIcon())).getImage();
                token1Image = ((ImageIcon)(tokenList.get(1).getIcon())).getImage();
                token2Image = ((ImageIcon)(tokenList.get(2).getIcon())).getImage();
                resizedToken0 = new ImageIcon(token0Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedToken1 = new ImageIcon(token1Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedToken2 = new ImageIcon(token2Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedTokens.add(new JLabel(resizedToken0));
                resizedTokens.add(new JLabel(resizedToken1));
                resizedTokens.add(new JLabel(resizedToken2));
                break;
            case 4:
                token0Image = ((ImageIcon)(tokenList.get(0).getIcon())).getImage();
                token1Image = ((ImageIcon)(tokenList.get(1).getIcon())).getImage();
                token2Image = ((ImageIcon)(tokenList.get(2).getIcon())).getImage();
                token3Image = ((ImageIcon)(tokenList.get(3).getIcon())).getImage();
                resizedToken0 = new ImageIcon(token0Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedToken1 = new ImageIcon(token1Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedToken2 = new ImageIcon(token2Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedToken3 = new ImageIcon(token3Image.getScaledInstance(20,20, Image.SCALE_SMOOTH));
                resizedTokens.add(new JLabel(resizedToken0));
                resizedTokens.add(new JLabel(resizedToken1));
                resizedTokens.add(new JLabel(resizedToken2));
                resizedTokens.add(new JLabel(resizedToken3));
        }

        /*
        //for debugging coordinates
        for(int i = 0; i < 30; i++){
            System.out.println("in the loop " + i );
            int x = boxesCoordinates.get(i).getX();
            int y = boxesCoordinates.get(i).getY();
            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), x, y, 20, 20);
        }

         */

        //Now we cycle on the token list and players scores to place tokens
        for(int i = 0; i < resizedTokens.size(); i++){

            //we retrieve the score of the player
            int score = client.getPoints()[i];
            //use it to get the coordinates corresponding to the box
            int placementX = boxesCoordinates.get(score).getX();
            int placementY = boxesCoordinates.get(score).getY();
            //Switch case on the population of that box to displace in different manners the tokens
            switch (boxesPopulation[score]){
                case 1:
                    //if only one in the box we place it in the center
                    addLabelIntoLocation(resizedTokens.get(i), placementX, placementY,20,20);
                    break;
                case 2:
                    //first we want to check if we are the first to access to the box
                    if(positionInBox[score] == 0){
                        //we place in the first spot
                        addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY,20,20);
                    } else if(positionInBox[score] == 1){
                        addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY,20,20);
                    }
                    positionInBox[score]++;
                    break;
                case 3:
                    //we check and place; 3 positions -> switch case
                    switch(positionInBox[score]){
                        case 0:
                            addLabelIntoLocation(resizedTokens.get(i), placementX, placementY - 10,20,20);
                            break;
                        case 1:
                            addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY + 10,20,20);
                            break;
                        case 2:
                            addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY + 10,20,20);
                            break;
                    }
                    positionInBox[score]++;
                    break;
                case 4:
                    switch(positionInBox[score]){
                        case 0:
                            addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY - 10,20,20);
                            break;
                        case 1:
                            addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY - 10,20,20);
                            break;
                        case 2:
                            addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY + 10,20,20);
                            break;
                        case 3:
                            addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY + 10,20,20);
                            break;
                    }
                    positionInBox[score]++;
                    break;
            }

        }

    }

    public void addElementsToTracker(){
        /*
        we must add:
        every token at right coordinate, given by the points of the player
         */

        //we do this for every player
        for(int i = 0; i < client.getPlayersToken().size(); i++){
            JLabel token = new JLabel(); //creating the label for the token
            token.setSize(token_X, token_Y);
            //we create the class loader
            ClassLoader cl = this.getClass().getClassLoader();
            //We get the corresponding token color
            String tokenColor = client.getPlayersToken().get(i);
            //we retrieve the corresponding image
            InputStream is = cl.getResourceAsStream("images/token/CODEX_pion_" + tokenColor + ".png");
            //we crete the icon with the image retrieved
            try {
                ImageIcon notResizedTokenIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(is)));
                //then we extract the image from the icon and create the final image icon resized
                ImageIcon resizedTokenIcon = new ImageIcon( notResizedTokenIcon.getImage().getScaledInstance(token_X, token_Y, Image.SCALE_SMOOTH));
                //finally we set the icon as the icon of the label we created
                token.setIcon(resizedTokenIcon);
                //We also add the token to the list of tokens
                tokenList.add(token);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }



    //setter and getter of the coordinate Box
    public void setBoxesCoordinates(Coordinates c ){
        boxesCoordinates.add(c);
    }

    public  List<Coordinates> getBoxesCoordinates() {
        return boxesCoordinates;
    }
    //class for the coordinates, used only here so create it here
    public static class Coordinates{

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
    /*
        This populates the boxesCoordinates array list
        index = which box
        value = x and y wrt to the whole image (in % to be consistent if frame changed in size)
    */
    public void defineCoordinates(){
        setBoxesCoordinates(new Coordinates(54, 432));
        setBoxesCoordinates(new Coordinates(106, 432));
        setBoxesCoordinates(new Coordinates(158, 432));

        setBoxesCoordinates(new Coordinates(186, 380));
        setBoxesCoordinates(new Coordinates(134, 380));
        setBoxesCoordinates(new Coordinates(82, 380));
        setBoxesCoordinates(new Coordinates(30, 380));

        setBoxesCoordinates(new Coordinates(30, 330));
        setBoxesCoordinates(new Coordinates(82, 330));
        setBoxesCoordinates(new Coordinates(134, 330));
        setBoxesCoordinates(new Coordinates(186, 330));

        setBoxesCoordinates(new Coordinates(186, 280));
        setBoxesCoordinates(new Coordinates(134, 280));
        setBoxesCoordinates(new Coordinates(82, 280));
        setBoxesCoordinates(new Coordinates(30, 280));

        setBoxesCoordinates(new Coordinates(30, 230));
        setBoxesCoordinates(new Coordinates(82, 230));
        setBoxesCoordinates(new Coordinates(134, 230));
        setBoxesCoordinates(new Coordinates(186, 230));

        setBoxesCoordinates(new Coordinates(186, 180));
        setBoxesCoordinates(new Coordinates(108, 155));
        setBoxesCoordinates(new Coordinates(30, 180));

        setBoxesCoordinates(new Coordinates(30, 130));
        setBoxesCoordinates(new Coordinates(30, 80));   //23
        setBoxesCoordinates(new Coordinates(60, 40));   //24
        setBoxesCoordinates(new Coordinates(108, 25));  //25
        setBoxesCoordinates(new Coordinates(158, 40));  //26
        setBoxesCoordinates(new Coordinates(186, 80));
        setBoxesCoordinates(new Coordinates(186, 130));
        setBoxesCoordinates(new Coordinates(108, 95));  //29
    }

    //Override of the method to paint entirely the component aka set an image as background
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(resizedPointTracker != null){
            g.drawImage(resizedPointTracker, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
