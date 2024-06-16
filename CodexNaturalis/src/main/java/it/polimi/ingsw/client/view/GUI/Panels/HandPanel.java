package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel {

    private final static int CARD_X = 210;
    private final static int CARD_Y = 140;

    /*
    In this panel there will be only the cards the player got in hand
    So 3 cards
    Clickable if in play phase
    (getting bright if overed by mouse)
    when clicked -> "Do you want to play it?"
    then "send" card to the board panel
     */

    //---- optimize with a for each
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
        //then we get the images rescaled
        Image image0 = originalIcon0.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image1 = originalIcon1.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image2 = originalIcon2.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);

        //Add them to the right icons
        ImageIcon resizedIcon0 = new ImageIcon(image0);
        ImageIcon resizedIcon1 = new ImageIcon(image1);
        ImageIcon resizedIcon2 = new ImageIcon(image2);

        //Create the labels with the image icons
        JLabel firstCard = new JLabel(resizedIcon0);
        JLabel secondCard = new JLabel(resizedIcon1);
        JLabel thirdCard = new JLabel(resizedIcon2);

        //We add a padding to the labels to separate the different cards
        gbc.insets = new Insets(5,5, 5, 5);

        //we set all parameters that have to be same for all
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        //add every label
        gbc.gridx = 0;
        add(firstCard, gbc);

        gbc.gridx = 1;
        add(secondCard, gbc);

        gbc.gridx = 2;
        add(thirdCard, gbc);
    }

}
