package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.Client;

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

    //dimensions of the panel of point tracker
    private final static int dim_X = 320;
    private final static int dim_Y = 610;

    private final static int REAL_IMAGE_WIDTH = 481;
    private final static int REAL_IMAGE_HEIGHT = 955;

    private int[] boxesPopulation = new int[30];


    //We need to store the coordinates on the image of every box
    private static List<Coordinates> boxesCoordinates = new ArrayList<>();
    private Client client;
    private JPanel backgroundPanel;
    public PointTrackerFrame(Client c) throws IOException {
        this.client = c;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /*
        we want to get the dimensions of the image of the point tracker
        to use them for the frame size:
        Creating the image icon
        retrieve dimensions
        use them
         */
        ClassLoader cl = this.getClass().getClassLoader();
        String pathToPointTracker = "images/pointTracker/PointTracker.png";
        InputStream is0 = cl.getResourceAsStream(pathToPointTracker);
        ImageIcon x = new ImageIcon(ImageIO.read(is0));

        //Now we resize the image to have proper dimension of point tracker
        Image resizedPointTracker = x.getImage().getScaledInstance(dim_X, dim_Y, Image.SCALE_SMOOTH);
        ImageIcon resizedPTIcon = new ImageIcon(resizedPointTracker);

        /*
        Let's try with the null layout
         */
        setLayout(null);
        //we create the panel for the background
        backgroundPanel = new JPanel() {
            //now we use the image to create the background
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(resizedPointTracker, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //set dimension of background
        backgroundPanel.setBounds(0, 0, dim_X, dim_Y);

        //we set not opaque the panel
        backgroundPanel.setOpaque(false);

        //add panel to the frame
        add(backgroundPanel);

        //now we set the size of the frame based on the just created icon
        setSize(new Dimension(dim_X + 15, dim_Y + 30));

        this.setResizable(false);
        this.setVisible(true);

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

    public  List<Coordinates> getBoxesCoordinates() {
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
        for(int i = 0; i < client.getPlayerList().size(); i++){
            JLabel token = new JLabel(); //creating the label for the token
            System.out.println( i + "th time in the loop of max : " + client.getPlayerList().size());
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
                //we retrieve the points of the player
                int score = client.getPoints()[i];
                // now we call the method that actually places the token
                placeToken(token, score);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //we place the token in right spot
    private void placeToken(JLabel token, int score){
        //Prepare variables for token placement
        int cornerX;
        int cornerY;
        backgroundPanel.setLayout(null);
        //first we transform score->coordinates
        Coordinates tokenPosition = getBoxesCoordinates().get(score);
        //Now we need to check how many tokens are already in that box to displace them all
        int currentBoxPopulation = boxesPopulation[score];
        switch(currentBoxPopulation){
            case 0:
                System.out.println("inserting first token");
                /*
                We calculate the relative position of the corner of the token:
                real x : width of real image = wanted x : resized image width
                then we obtain x and y of center -> calculate corner
                 */
                 cornerX = (int)Math.round(((dim_X*tokenPosition.getX())/REAL_IMAGE_WIDTH) - token.getWidth()/2);
                 cornerY = (int)Math.round(((dim_Y*tokenPosition.getY())/REAL_IMAGE_HEIGHT) - token.getHeight()/2);

                //set bounds of the label
                token.setBounds(cornerX, cornerY, token.getWidth(), token.getHeight());
                //Add the token to the frame
                backgroundPanel.add(token);
                setVisible(true);
                break;
            case 1:
                System.out.println("Inserting second");
                /*
                we cannot place the token in same position:
                we place it at the just right of other
                 */
                cornerX = (int)Math.round(((dim_X*tokenPosition.getX())/REAL_IMAGE_WIDTH) + token.getWidth()/2);
                cornerY = (int)Math.round(((dim_Y*tokenPosition.getY())/REAL_IMAGE_HEIGHT) - token.getHeight()/2);

                //set bounds of the label
                token.setBounds(cornerX, cornerY, token.getWidth(), token.getHeight());
                //Add the token to the frame
                backgroundPanel.add(token);
                setVisible(true);
                break;
            case 2:
                System.out.println("Inserting third");
                cornerX = (int)Math.round(((dim_X*tokenPosition.getX())/REAL_IMAGE_WIDTH) - token.getWidth()/2);
                cornerY = (int)Math.round(((dim_Y*tokenPosition.getY())/REAL_IMAGE_HEIGHT) + token.getHeight()/2);

                //set bounds of the label
                token.setBounds(cornerX, cornerY, token.getWidth(), token.getHeight());
                //Add the token to the frame
                backgroundPanel.add(token);
                setVisible(true);
                break;
            case 3:
                System.out.println("Inserting fourth");
                cornerX = (int)Math.round(((dim_X*tokenPosition.getX())/REAL_IMAGE_WIDTH) + token.getWidth()/2);
                cornerY = (int)Math.round(((dim_Y*tokenPosition.getY())/REAL_IMAGE_HEIGHT) + token.getHeight()/2);

                //set bounds of the label
                token.setBounds(cornerX, cornerY, token.getWidth(), token.getHeight());
                //Add the token to the frame
                backgroundPanel.add(token);
                setVisible(true);
                break;
        }
        boxesPopulation[score]++;

    }

}
