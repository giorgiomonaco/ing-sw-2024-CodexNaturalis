package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionCard;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class MainPanel extends JPanel {

    private Client client;
    private GridBagConstraints gbc;
    private ChatPanel chat;
    private BoardPanel board;
    private int xCoord;
    private int yCoord;
    private boolean side;
    private Card card;

    public MainPanel(Client client){

        this.client = client;
        this.card = null;
        xCoord = -1;
        yCoord = -1;
        side = true;
        //setting right layout to manage the panel
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        createElements();
    }

    public void createElements(){
        // Create all the panels in the main panel
        chat = new ChatPanel(client);
        board = new BoardPanel(client, this);
        AccessoryPanel other = new AccessoryPanel(client);
        HandPanel hand = new HandPanel(client, this);

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
        gbc.gridwidth = 70; // Reduced from 90
        gbc.gridheight = 15; // Reduced from 20
        gbc.weightx = 0.7; // Reduced from 0.9
        gbc.weighty = 0.15; // Increased from 0.1
        gbc.fill = GridBagConstraints.BOTH;
        hand.setBackground(Color.gray);
        add(hand, gbc);

        // PLACE BUTTON---
        gbc.gridx = 70;
        gbc.gridy = 85;
        gbc.gridwidth = 10;
        gbc.gridheight = 15;
        gbc.weightx = 0.1;
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH;
        JButton button = new JButton("PLACE");
        button.addMouseListener(new buttonListener(this, client));
        add(button, gbc);
    }

    private static class buttonListener extends MouseAdapter {
        private MainPanel mp;
        private Client client;

        public buttonListener(MainPanel mp, Client client) {
            this.mp = mp;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // for debugging
            // System.out.println(mp.getxCoord() + " " + mp.getyCoord());
            if ((mp.getxCoord() != -1) && (mp.getyCoord() != -1) && (mp.getCard() != null) && availableResources(mp.getCard(), mp.isSide())) {
                try {
                    //send message with the number of the players
                    client.sendMessage(new SelectionCard(client.getUsername(), mp.getCard(), mp.getxCoord(), mp.getyCoord(), mp.isSide()));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (mp.getxCoord() == -1 || mp.getyCoord() == -1){
                client.getUI().printErrorMessage("WRONG SELECTION! You have to select a place in the board first.");
            } else if (mp.getCard() == null) {
                client.getUI().printErrorMessage("WRONG SELECTION! You have to select a card first.");
            } else {
                client.getUI().printErrorMessage("You do not have enough resources to play this card. Please choose another one.");
            }

        }

        private boolean availableResources(Card card, boolean side){
            if(card instanceof GoldCard && side){
                for (int i = 0; i < 7; i++) {
                    if(!(((GoldCard) card).getNeededSymbols()[i] <= client.getResources()[i])){
                        return false;
                    }

                }
            }
            return true;
        }
    }

    public ChatPanel getChat() {
        return chat;
    }

    public BoardPanel getBoard() {
        return board;
    }


    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int x) {
        this.xCoord = x;
    }


    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int y) {
        this.yCoord = y;
    }

    public boolean isSide() {
        return side;
    }

    public void setSide(boolean side) {
        this.side = side;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
