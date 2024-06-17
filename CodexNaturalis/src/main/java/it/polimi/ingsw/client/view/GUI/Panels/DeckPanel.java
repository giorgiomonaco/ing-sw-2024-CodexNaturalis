package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class DeckPanel extends JPanel {

    private Client client;
    private final static int CARD_X = 210;
    private final static int CARD_Y = 140;

    public DeckPanel(Client client){
        this.client = client;
        initializePanel();
    }

    private void initializePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //we retrieve the images of the cards
        ImageIcon originalIcon0 = new ImageIcon(client.getPlayerHand().get(2).getBackImage());
        ImageIcon originalIcon1 = new ImageIcon(client.getVisibleResourceCards().get(0).getFrontImage());
        ImageIcon originalIcon2 = new ImageIcon(client.getVisibleResourceCards().get(1).getFrontImage());
        ImageIcon originalIcon3 = new ImageIcon(client.getPlayerHand().get(1).getBackImage());
        ImageIcon originalIcon4 = new ImageIcon(client.getVisibleGoldCards().get(0).getFrontImage());
        ImageIcon originalIcon5 = new ImageIcon(client.getVisibleGoldCards().get(1).getFrontImage());

        //then we get the images rescaled
        Image image0 = originalIcon0.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image1 = originalIcon1.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image2 = originalIcon2.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image3 = originalIcon3.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image4 = originalIcon4.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image5 = originalIcon5.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);

        //Add them to the right icons
        ImageIcon resizedIcon0 = new ImageIcon(image0);
        ImageIcon resizedIcon1 = new ImageIcon(image1);
        ImageIcon resizedIcon2 = new ImageIcon(image2);
        ImageIcon resizedIcon3 = new ImageIcon(image3);
        ImageIcon resizedIcon4 = new ImageIcon(image4);
        ImageIcon resizedIcon5 = new ImageIcon(image5);

        //Create the labels with the image icons
        JLabel deckRes = new JLabel(resizedIcon0);
        JLabel firstRes = new JLabel(resizedIcon1);
        JLabel secondRes = new JLabel(resizedIcon2);
        JLabel deckGold = new JLabel(resizedIcon3);
        JLabel firstGold = new JLabel(resizedIcon4);
        JLabel secondGold = new JLabel(resizedIcon5);

        // Set insets to reduce space between cards
        gbc.insets = new Insets(0, 0, 0, 0);

        // Set all parameters that have to be same for all
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;

        // Add the button first
        gbc.gridx = 0;
        add(deckRes, gbc);

        // Add every label
        gbc.gridx = 1;
        add(firstRes, gbc);

        gbc.gridx = 2;
        add(secondRes, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(deckGold, gbc);

        gbc.gridx = 1;
        add(firstGold, gbc);

        gbc.gridx = 2;
        add(secondGold, gbc);
    }


}
