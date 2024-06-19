package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AccessoryPanel extends JPanel {
    private Client client;
    private List<String> players;
    private List<JLabel> playersLab;

    public AccessoryPanel(Client client){
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
        gbc.insets = new Insets(10, 10, 10, 10);

        for (String p : players) {

            JLabel playerLabel = new JLabel(p.toUpperCase());
            playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playersPanel.add(playerLabel, gbc);
            playersLab.add(playerLabel);
            gbc.gridx++;

        }

        add(playersPanel, BorderLayout.CENTER);

        /*
        We create the panel to see the point tracker with the current situation
        And we may add there the possibility to look at the card the player may draw
        without making him actually draw
         */


    }

    public List<JLabel> getPlayersLab() {
        return playersLab;
    }
}
