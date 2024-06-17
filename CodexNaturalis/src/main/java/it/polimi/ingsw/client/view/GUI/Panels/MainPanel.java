package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private Client client;
    private GridBagConstraints gbc;
    private ChatPanel chat;
    private BoardPanel board;
    private int x;
    private int y;

    public MainPanel(Client client){

        this.client = client;
        x = -1;
        y = -1;
        //setting right layout to manage the panel
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Create all the panels in the main panel
        chat = new ChatPanel(client);
        board = new BoardPanel(client, this);
        AccessoryPanel other = new AccessoryPanel(client);
        HandPanel hand = new HandPanel(client);

        // Set proportions for each panel
        // Board panel will cover 70% of y and 80% of x space
        // BOARD-----
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 80; // Reduced from 90
        gbc.gridheight = 85; // Increased from 80
        gbc.weightx = 0.8; // Reduced from 0.9
        gbc.weighty = 0.85; // Increased from 0.8
        gbc.fill = GridBagConstraints.BOTH;
        add(board, gbc);

        // OTHERS---
        gbc.gridx = 80; // Changed from 90
        gbc.gridy = 0;
        gbc.gridwidth = 20; // Increased from 10
        gbc.gridheight = 85; // Increased from 80
        gbc.weightx = 0.2; // Increased from 0.1
        gbc.weighty = 0.85; // Increased from 0.8
        gbc.fill = GridBagConstraints.BOTH;
        add(other, gbc);

        // Reset the gbc
        // CHAT---
        gbc.gridx = 80; // Changed from 90
        gbc.gridy = 85; // Changed from 80
        gbc.gridwidth = 20; // Increased from 10
        gbc.gridheight = 15; // Reduced from 20
        gbc.weightx = 0.2; // Reduced from 0.35
        gbc.weighty = 0.15; // Increased from 0.1
        gbc.fill = GridBagConstraints.BOTH;
        add(chat, gbc);

        // CARDS---
        gbc.gridx = 0;
        gbc.gridy = 85; // Changed from 80
        gbc.gridwidth = 80; // Reduced from 90
        gbc.gridheight = 15; // Reduced from 20
        gbc.weightx = 0.8; // Reduced from 0.9
        gbc.weighty = 0.15; // Increased from 0.1
        gbc.fill = GridBagConstraints.BOTH;
        hand.setBackground(Color.blue);
        add(hand, gbc);
    }

    public ChatPanel getChat() {
        return chat;
    }

    public BoardPanel getBoard() {
        return board;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
