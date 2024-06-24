package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Panels.BackGroundPanel;

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

    //We need to store the coordinates on the image of every box
    private List<Coordinates> boxesCoordinates = new ArrayList<>();
    private Client client;

    //panel to displace the point tracker
    private JPanel backgroundPanel;

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
        fillBackgroundPanel();

        //create and place the legend panel
        if(!(client.getPlayerList().size() == tokenList.size())) {
            fillNotReadyLegendPanel();
        } else {
            fillStandardLegendPanel();
        }

        setVisible(true);

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

    private void fillBackgroundPanel() throws IOException{
        //we retrieve the image we want to displace
        ClassLoader cl = this.getClass().getClassLoader();
        String pathToPointTracker = "images/pointTracker/PointTracker.png";
        InputStream is0 = cl.getResourceAsStream(pathToPointTracker);
        //we put it into an image icon
        ImageIcon x = new ImageIcon(ImageIO.read(is0));

        //Now we resize the image to have proper dimension of point tracker
        Image resizedPointTracker = x.getImage().getScaledInstance(frame_X, frame_Y - legend_Y, Image.SCALE_SMOOTH);

        //now we instance the panel and fill it up with the image
        backgroundPanel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(resizedPointTracker != null){
                    g.drawImage(resizedPointTracker, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        backgroundPanel.setPreferredSize(new Dimension(frame_X, frame_Y - legend_Y));
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(false);

        //try to add the token to the box
        populateTracker();

        add(backgroundPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    //let's go for the method in different method
    private void populateTracker(){


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
                ImageIcon notResizedTokenIcon = new ImageIcon(ImageIO.read(is));
                //then we extract the image from the icon and create the final image icon resized
                ImageIcon resizedTokenIcon = new ImageIcon( notResizedTokenIcon.getImage().getScaledInstance(token_X, token_Y, Image.SCALE_SMOOTH));
                //finally we set the icon as the icon of the label we created
                token.setIcon(resizedTokenIcon);
                //We also add the token to the list of tokens
                int score = client.getPoints()[i];
                //now we got a token and the box
                Coordinates selectedBox = boxesCoordinates.get(score);
                //retrieve the x and y of center of box
                int center_X = selectedBox.getX();
                int center_Y = selectedBox.getY();
                System.out.println("coord: " + center_X + ", " + center_Y);
                token.setBounds(center_X, center_Y,token.getWidth(), token.getHeight());
                token.setOpaque(true);
                token.setBackground(Color.orange);
                backgroundPanel.add(token);
                backgroundPanel.repaint();
                backgroundPanel.revalidate();
                backgroundPanel.setVisible(true);
                setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    //
    private void addElementsToTracker(){
        /*
        we must add:
        every token at right coordinate, given by the points of the player
         */

        //we do this for every player
        for(int i = 0; i < client.getPlayersToken().size(); i++){
            JLabel token = new JLabel(); //creating the label for the token
            System.out.println( i + "th time in the loop of max : " + client.getPlayersToken().size());
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
    /*
        This populates the boxesCoordinates array list
        index = which box
        value = x and y wrt to the whole image (in % to be consistent if frame changed in size)
    */
    private void defineCoordinates(){
        setBoxesCoordinates(new Coordinates(70, 480));
        setBoxesCoordinates(new Coordinates(125, 480));
        setBoxesCoordinates(new Coordinates(185, 480));
        setBoxesCoordinates(new Coordinates(210, 425));
        setBoxesCoordinates(new Coordinates(155, 425));
        setBoxesCoordinates(new Coordinates(100, 425));
        setBoxesCoordinates(new Coordinates(40, 425));
        setBoxesCoordinates(new Coordinates(40, 370));
        setBoxesCoordinates(new Coordinates(100, 370));
        setBoxesCoordinates(new Coordinates(155, 370));
        setBoxesCoordinates(new Coordinates(210, 370));
        setBoxesCoordinates(new Coordinates(210, 315));
        setBoxesCoordinates(new Coordinates(155, 315));
        setBoxesCoordinates(new Coordinates(100, 315));
        setBoxesCoordinates(new Coordinates(40, 315));
        setBoxesCoordinates(new Coordinates(40, 260));
        setBoxesCoordinates(new Coordinates(100, 260));
        setBoxesCoordinates(new Coordinates(155, 260));
        setBoxesCoordinates(new Coordinates(210, 260));
        setBoxesCoordinates(new Coordinates(210, 205));
        setBoxesCoordinates(new Coordinates(125, 180));
        setBoxesCoordinates(new Coordinates(40, 205));
        setBoxesCoordinates(new Coordinates(40, 150));
        setBoxesCoordinates(new Coordinates(40, 95));
        setBoxesCoordinates(new Coordinates(75, 50));
        setBoxesCoordinates(new Coordinates(125, 40));
        setBoxesCoordinates(new Coordinates(180, 50));
        setBoxesCoordinates(new Coordinates(215, 95));
        setBoxesCoordinates(new Coordinates(210, 150));
        setBoxesCoordinates(new Coordinates(125, 110));
    }



}