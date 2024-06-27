package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Frames.BoardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TurnOfPanel extends JPanel {
    private final Client client;
    private List<JButton> playersLab;
    private final MainPanel mp;

    public TurnOfPanel(Client client, MainPanel mp){
        this.client = client;
        this.mp = mp;
        setLayout(new BorderLayout());

        createElements();
    }

    public void createElements(){

        JLabel title = new JLabel("TURN OF", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 15));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        List<String> players = client.getPlayerList();

        JPanel playersPanel = new JPanel(new GridBagLayout());
        playersLab = new ArrayList<>();
        playersPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        int i = 0;
        for (String p : players) {

            JButton playerButton = new JButton(p.toUpperCase());
            playerButton.setHorizontalAlignment(SwingConstants.CENTER);
            if (!p.equals(client.getUsername())) {
                playerButton.addMouseListener(new boardMouseListener(i, client, mp));
            }
            playersPanel.add(playerButton, gbc);
            playersLab.add(playerButton);
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
        private final MainPanel mp;

        public boardMouseListener(int index, Client client, MainPanel mp) {

            this.index = index;
            this.client = client;
            this.mp = mp;

        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if(!mp.isStop() && client.getGameBoards(index) != null) {
                BoardFrame boardFrame = new BoardFrame(client.getGameBoards(index), client.getPlayerList().get(index));
                boardFrame.getBoard().scrollToMiddle();
                boardFrame.setVisible(true);
            }

        }
    }

    public List<JButton> getPlayersLab() {
        return playersLab;
    }
}
