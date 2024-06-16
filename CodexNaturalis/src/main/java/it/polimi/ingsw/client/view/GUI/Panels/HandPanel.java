package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel {
    /*
    In this panel there will be only the cards the player got in hand
    So 3 cards
    Clickable if in play phase
    (getting bright if overed by mouse)
    when clicked -> "Do you want to play it?"
    then "send" card to the board panel
     */
    private Client client;

    JLabel firstCard;
    JLabel secondCard;
    JLabel thirdCard;

    public HandPanel(Client client) {
        this.client = client;
        //setting layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //we retrieve the images of the cards
        ImageIcon originalIcon0 = new ImageIcon(client.getPlayerHand().get(0).getFrontImage());
        ImageIcon originalIcon1 = new ImageIcon(client.getPlayerHand().get(1).getFrontImage());
        ImageIcon originalIcon2 = new ImageIcon(client.getPlayerHand().get(2).getFrontImage());
        //then we get the images
        Image image0 = originalIcon0.getImage();
        Image image1 = originalIcon1.getImage();
        Image image2 = originalIcon2.getImage();

        //we create the labels for the cards and populate with images
            firstCard = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                        // Ridimensiona l'immagine per adattarla al label
                        int width = getWidth();
                        int height = getHeight();
                        Image scaledImage = image0.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        g.drawImage(scaledImage, 0, 0, null);

                }
            };
            secondCard = new JLabel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    // Ridimensiona l'immagine per adattarla al label
                    int width = getWidth();
                    int height = getHeight();
                    Image scaledImage = image1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, null);

                }
            };
            thirdCard = new JLabel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    // Ridimensiona l'immagine per adattarla al label
                    int width = getWidth();
                    int height = getHeight();
                    Image scaledImage = image2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, null);

                }
            };

        //We add a padding to the labels to separate the different cards
        gbc.insets = new Insets(5,5, 5, 5);
        //now we set that the labels have to fill up the whole space
        gbc.fill = GridBagConstraints.BOTH;

        //we set all parameters that have to be same for all
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        //add every label
        gbc.gridx = 0;
        add(firstCard, gbc);

        gbc.gridx = 1;
        add(secondCard, gbc);

        gbc.gridx = 2;
        add(thirdCard, gbc);
    }

}
