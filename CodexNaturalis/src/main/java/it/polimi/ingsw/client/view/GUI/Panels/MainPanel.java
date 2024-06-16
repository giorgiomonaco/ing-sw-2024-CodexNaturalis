package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainPanel extends JPanel {

    private Client client;
    private GridBagConstraints gbc;

    public MainPanel(Client client){

        this.client = client;
        //setting right layout to manage the panel
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //we crete all the panels in the main panel
        ChatPanel chat = new ChatPanel(client);
        BoardPanel board = new BoardPanel(client);
        AccessoryPanel other = new AccessoryPanel(client);
        HandPanel hand = new HandPanel(client);
        //we assign the right space to every panel in proportions to be defined later
        //board panel will cover 65% of y and 90% of x space
        //BOARD-----
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 90;
        gbc.gridheight = 65;
        gbc.weightx = 0.9;
        gbc.weighty = 0.65;
        gbc.fill = GridBagConstraints.BOTH;
        //color panel just for visualization
        board.setBackground(Color.red);
        //add component to the panel
        add(board, gbc);

        //same for every component with different proportions
        //first we reset the gbc just to be sure
        //CHAT---
        gbc.gridx = 65;
        gbc.gridy = 65;
        gbc.gridwidth = 35;
        gbc.gridheight = 35;
        gbc.weightx = 0.35;
        gbc.weighty = 0.35;
        gbc.fill = GridBagConstraints.BOTH;
        chat.setBackground(Color.green);
        add(chat, gbc);

        //CARDS---
        gbc.gridx = 0;
        gbc.gridy = 65;
        gbc.gridwidth = 65;
        gbc.gridheight = 35;
        gbc.weightx = 0.65;
        gbc.weighty = 0.35;
        gbc.fill = GridBagConstraints.BOTH;
        hand.setBackground(Color.blue);
        add(hand, gbc);

        //CHAT---
        gbc.gridx = 90;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        gbc.gridheight = 65;
        gbc.weightx = 0.1;
        gbc.weighty = 0.65;
        gbc.fill = GridBagConstraints.BOTH;
        other.setBackground(Color.black);
        add(other, gbc);


    }
}
