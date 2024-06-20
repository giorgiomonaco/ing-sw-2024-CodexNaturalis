package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Frames.BoardFrame;
import it.polimi.ingsw.client.view.GUI.Frames.DrawFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TurnOfPanel extends JPanel {
    private Client client;
    private List<String> players;
    private List<JLabel> playersLab;

    public TurnOfPanel(Client client){
        this.client = client;
        setLayout(new BorderLayout());

        createElements();
    }

    public void createElements(){

        JLabel title = new JLabel("TURN OF", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 15));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        players = client.getPlayerList();

        JPanel playersPanel = new JPanel(new GridBagLayout());
        playersLab = new ArrayList<>();
        playersPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        int i = 0;
        for (String p : players) {

            JLabel playerLabel = new JLabel(p.toUpperCase());
            playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerLabel.addMouseListener(new boardMouseListener(i, client));
            playersPanel.add(playerLabel, gbc);
            playersLab.add(playerLabel);
            gbc.gridx++;
            i++;

        }

        add(playersPanel, BorderLayout.CENTER);

        /*
        We create the panel to see the point tracker with the current situation
        And we may add there the possibility to look at the card the player may draw
        without making him actually draw
         */


    }

    private static class boardMouseListener extends MouseAdapter {
        private final int index;
        private final Client client;

        public boardMouseListener(int index, Client client) {

            this.index = index;
            this.client = client;

        }

        @Override
        public void mouseClicked(MouseEvent e) {

            BoardFrame boardFrame = new BoardFrame(client.getGameBoards(index), client.getPlayerList().get(index));
            boardFrame.getBoard().scrollToMiddle();
            boardFrame.setVisible(true);

        }
    }

    public List<JLabel> getPlayersLab() {
        return playersLab;
    }
}
