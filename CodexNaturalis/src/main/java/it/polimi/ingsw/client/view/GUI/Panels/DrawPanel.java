package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    private Client client;

    public DrawPanel(Client client) {
        this.client = client;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>DRAW A CARD FROM THE DECK<br>OR FROM THE VISIBLE ONES<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("Papyrus", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        DeckPanel deck = new DeckPanel(client);

        add(deck, BorderLayout.CENTER);
    }
}
