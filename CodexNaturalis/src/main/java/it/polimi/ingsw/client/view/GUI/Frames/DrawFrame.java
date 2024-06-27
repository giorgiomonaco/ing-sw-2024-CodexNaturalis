package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.GUI.Panels.DeckPanel;
import it.polimi.ingsw.network.message.allMessages.DrawCardResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class DrawFrame extends JFrame {
    private final Client client;
    private int selection;
    private static final int DIM_X = 650;
    private static final int DIM_Y = 450;

    public DrawFrame(Client client) {
        this.client = client;
        this.selection = -1;
        setSize(new Dimension(DIM_X,DIM_Y));
        setTitle("DRAW");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>DRAW A CARD FROM THE DECK<br>OR FROM THE VISIBLE ONES<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 30));
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
        private DrawFrame dp;
        private Client client;

        public buttonListener(DrawFrame dp, Client client) {
            this.dp = dp;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (dp.getSelection() != -1 && client.getCurrentState().equals(stateEnum.DRAW_CARD)) {
                try {
                    //send message with the number of the players
                    client.sendMessage(new DrawCardResponse(client.getUsername(), dp.getSelection()));
                    dp.dispose();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (dp.getSelection() == -1) {
                client.getUI().printErrorMessage("WRONG SELECTION! You have to select a card to draw first.");
            } else {
                client.getUI().printErrorMessage("PERMISSION DENIED! It's not your turn to draw a card. Wait for your turn.");
            }

        }

    }
}
