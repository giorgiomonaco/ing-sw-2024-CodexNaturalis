package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Panels.BackGroundPanel;
import it.polimi.ingsw.client.view.GUI.Panels.PointTrackerPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class PointTrackerFrame extends JFrame {
    /*
    This class manages the displacement of the point tracker,
    and it places the tokens on top of the right cell
     */

    //We define dimensions of tokens
    private final static int token_X = 25;
    private final static int token_Y = 25;

    //dimensions of frame
    private final static int frame_X = 250;
    private final static  int frame_Y = 570;

    //height of legend panel
    private final static int legend_Y = 50;


    //array to keep track of how many tokens in same box
    private int[] boxesPopulation = new int[30];
    private int[] positionInBox = new int[30];

    //We need to store the coordinates on the image of every box
    private List<Coordinates> boxesCoordinates = new ArrayList<>();
    private Client client;

    //panel to displace the point tracker
    private PointTrackerPanel backgroundPanel;

    //Panel to displace relation between player name and token chosen
    private JPanel legend;

    //Array of images to store the tokens so not retrieve them every time
    private List <JLabel> tokenList = new ArrayList<>();


    //Constructor
    public PointTrackerFrame(Client c) throws IOException {

        this.client = c;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(frame_X, frame_Y));
        setLayout(new BorderLayout());



        //we set up the coordinates to place the tokens
        defineCoordinates();
        //retrieve the tokens
        addElementsToTracker();
        //Create and place the point tracker
        paintBackgroundPanel();




        //create and place the legend panel
        if(!(client.getPlayerList().size() == tokenList.size())) {
            fillNotReadyLegendPanel();
        } else {
            fillStandardLegendPanel();
            fillBackgroundPanel();
        }

        setVisible(true);

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
                    backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX, placementY,20,20);
                    break;
                case 2:
                    //first we want to check if we are the first to access to the box
                    if(positionInBox[score] == 0){
                        //we place in the first spot
                        backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY,20,20);
                    } else if(positionInBox[score] == 1){
                        backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY,20,20);
                    }
                    positionInBox[score]++;
                    break;
                case 3:
                    //we check and place; 3 positions -> switch case
                    switch(positionInBox[score]){
                        case 0:
                            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX, placementY - 10,20,20);
                            break;
                        case 1:
                            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY + 10,20,20);
                            break;
                        case 2:
                            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY + 10,20,20);
                            break;
                    }
                    positionInBox[score]++;
                    break;
                case 4:
                    switch(positionInBox[score]){
                        case 0:
                            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY - 10,20,20);
                            break;
                        case 1:
                            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY - 10,20,20);
                            break;
                        case 2:
                            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX - 10, placementY + 10,20,20);
                            break;
                        case 3:
                            backgroundPanel.addLabelIntoLocation(resizedTokens.get(i), placementX + 10, placementY + 10,20,20);
                            break;
                    }
                    positionInBox[score]++;
                    break;
            }
        }

    }

    private void fillNotReadyLegendPanel(){
        legend = new JPanel();
        legend.setPreferredSize(new Dimension(frame_X, legend_Y));
        JLabel notReadyLabel = new JLabel("Not every player has chosen the token yet");
        add(legend, BorderLayout.NORTH);
    }

    private void fillStandardLegendPanel(){
        legend = new JPanel();
        legend.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        legend.setPreferredSize(new Dimension(frame_X, legend_Y));

        //Prepare vars for placement of elements into the panel
        int currentX = 0;
        int currentY = 0;
        //for every token
        for(int i = 0; i < tokenList.size(); i++){
            //we create a JLabel to be placed in the legend
            JLabel playerName = new JLabel(client.getPlayerList().get(i));
            //we add it using gbc
            gbc.gridx = currentX;
            gbc.gridy = currentY;
            legend.add(playerName, gbc);
            gbc.gridy = currentY + 1;
            legend.add(tokenList.get(i), gbc);
            currentX++;
        }

        add(legend, BorderLayout.NORTH);

    }

    private void paintBackgroundPanel() throws IOException{
        //first thing first we paint the panel with the point tracker image;
        //we retrieve the image we want to displace
        ClassLoader cl = this.getClass().getClassLoader();
        String pathToPointTracker = "images/pointTracker/PointTracker.png";
        InputStream is0 = cl.getResourceAsStream(pathToPointTracker);
        //we put it into an image icon
        ImageIcon x = new ImageIcon(ImageIO.read(is0));

        //Now we resize the image to have proper dimension of point tracker
        Image resizedPointTracker = x.getImage().getScaledInstance(frame_X, frame_Y - legend_Y, Image.SCALE_SMOOTH);

        //now we instance the panel and fill it up with the image
        backgroundPanel = new PointTrackerPanel(client, frame_X, frame_Y - legend_Y){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(resizedPointTracker != null){
                    g.drawImage(resizedPointTracker, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        add(backgroundPanel, BorderLayout.CENTER);

        setVisible(true);

    }


    private void addElementsToTracker(){
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
        setBoxesCoordinates(new Coordinates(125, 432));
        setBoxesCoordinates(new Coordinates(185, 432));

        setBoxesCoordinates(new Coordinates(186, 380));
        setBoxesCoordinates(new Coordinates(104, 380));
        setBoxesCoordinates(new Coordinates(82, 380));
        setBoxesCoordinates(new Coordinates(30, 380));

        setBoxesCoordinates(new Coordinates(30, 330));
        setBoxesCoordinates(new Coordinates(82, 330));
        setBoxesCoordinates(new Coordinates(104, 330));
        setBoxesCoordinates(new Coordinates(186, 330));

        setBoxesCoordinates(new Coordinates(186, 280));
        setBoxesCoordinates(new Coordinates(104, 280));
        setBoxesCoordinates(new Coordinates(82, 280));
        setBoxesCoordinates(new Coordinates(30, 280));

        setBoxesCoordinates(new Coordinates(30, 260));
        setBoxesCoordinates(new Coordinates(82, 260));
        setBoxesCoordinates(new Coordinates(104, 260));
        setBoxesCoordinates(new Coordinates(186, 260));

        setBoxesCoordinates(new Coordinates(186, 180));
        setBoxesCoordinates(new Coordinates(108, 155));
        setBoxesCoordinates(new Coordinates(30, 180));

        setBoxesCoordinates(new Coordinates(30, 130));
        setBoxesCoordinates(new Coordinates(30, 80));
        setBoxesCoordinates(new Coordinates(60, 40));
        setBoxesCoordinates(new Coordinates(108, 25));
        setBoxesCoordinates(new Coordinates(155, 40));
        setBoxesCoordinates(new Coordinates(186, 80));
        setBoxesCoordinates(new Coordinates(186, 130));
        setBoxesCoordinates(new Coordinates(108, 95));
    }



}