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

public class LegendPanel extends JPanel {
    //We define dimensions of tokens
    private final static int token_X = 25;
    private final static int token_Y = 25;
    private final static int legend_Y = 50;
    private final static int frame_X = 250;
    private final static  int frame_Y = 570;
    private Client client;
    private List <JLabel> tokenList = new ArrayList<>();
    private Image backgroundImage;
    private GridBagConstraints gbc;

    public LegendPanel(Client c) {
        this.client = c;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        setPreferredSize(new Dimension(frame_X, legend_Y));

        paintPanel();
        addElements();
        fillLegend();

    }

    private void fillLegend(){
        //Prepare vars for placement of elements into the panel
        int currentX = 0;
        int currentY = 0;
        //for every token
        for (int i = 0; i < tokenList.size(); i++) {
            //we create a JLabel to be placed in the legend
            JLabel playerName = new JLabel(client.getPlayerList().get(i));
            //we add it using gbc
            gbc.gridx = currentX;
            gbc.gridy = currentY;
            add(playerName, gbc);
            gbc.gridy = currentY + 1;
            add(tokenList.get(i), gbc);
            currentX++;
        }
    }


    private void addElements(){
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

    private void paintPanel(){
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("images/backGround3.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
