package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.DrawCardResponse;
import it.polimi.ingsw.network.message.allMessages.SelectionCard;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.GoldCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class DrawPanel extends JPanel {
    private final Client client;
    private int selection;

    public DrawPanel(Client client) {
        this.client = client;
        this.selection = -1;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>DRAW A CARD FROM THE DECK<br>OR FROM THE VISIBLE ONES<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("Papyrus", Font.BOLD, 50));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        DeckPanel deck = new DeckPanel(client, this);

        add(deck, BorderLayout.CENTER);

        JButton button = new JButton("DRAW");
        button.addMouseListener(new buttonListener(this, client));

        add(button, BorderLayout.SOUTH);
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    private static class buttonListener extends MouseAdapter {
        private DrawPanel dp;
        private Client client;

        public buttonListener(DrawPanel dp, Client client) {
            this.dp = dp;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (dp.getSelection() != -1) {
                try {
                    //send message with the number of the players
                    client.sendMessage(new DrawCardResponse(client.getUsername(), dp.getSelection()));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                client.getUI().printErrorMessage("WRONG SELECTION! You have to select a card to draw first.");
            }

        }

    }
}
