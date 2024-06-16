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


        //Starting from creating labels for the cards
        for(int i = 0; i < 3; i++) {
            firstCard = new JLabel(assignImageToCard(i));
            secondCard = new JLabel(assignImageToCard(i));
            thirdCard = new JLabel(assignImageToCard(i));
        }

        //We populate the labels with the right cards



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

    //method to populate the labels with the cards
    private ImageIcon assignImageToCard(int i) {
        //we create the icon with the image of the card
        ImageIcon originalIcon = new ImageIcon(client.getPlayerHand().get(i).getFrontImage());
        //then we resize the image
        Image originalImage = originalIcon.getImage();
        //now we get the size of the label
        int labelWidth = firstCard.getWidth();
        int labelHeight = firstCard.getHeight();
        //we resize basing on the dimensions
        Image resizedImage2 = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
        //we return the icon to assign to label
        return new ImageIcon(resizedImage2);

    }

}
