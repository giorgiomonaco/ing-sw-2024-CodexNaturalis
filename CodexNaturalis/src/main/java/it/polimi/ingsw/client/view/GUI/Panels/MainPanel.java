package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainPanel extends JPanel {

    private Client client;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    ImageIcon image;

    public MainPanel(GridBagLayout layout, Client client){

        setPreferredSize(new Dimension(900, 500));



        //Create the panel that will contain the cards
        JPanel cardsPanel = new JPanel();

        //Now we create the slots for the cards and we populate just for the fun

        image = new ImageIcon("it/polimi/ingsw/client/view/GUI/Images/swamp.jpg");
        //Dimensions fixed for the card
        Dimension fixedCardDim = new Dimension(250, 375);
        //create the container of the card
        JLabel firstCard = new JLabel(image);
        firstCard.setPreferredSize(fixedCardDim);
        firstCard.setMaximumSize(fixedCardDim);

        image = new ImageIcon("it/polimi/ingsw/client/view/GUI/Images/plains.jpg");
        //create the container of the card
        JLabel secondCard = new JLabel(image);
        secondCard.setPreferredSize(fixedCardDim);
        secondCard.setMaximumSize(fixedCardDim);

        image = new ImageIcon("it/polimi/ingsw/client/view/GUI/Images/mountain land.jpg");
        //create the container of the card
        JLabel thirdCard = new JLabel(image);
        thirdCard.setPreferredSize(fixedCardDim);
        thirdCard.setMaximumSize(fixedCardDim);

        //We create the panel for the chat


    }
}
