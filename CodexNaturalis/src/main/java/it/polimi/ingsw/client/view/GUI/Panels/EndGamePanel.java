package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import javax.swing.*;
import java.awt.*;

public class EndGamePanel extends JPanel {

    private String winner;

    public EndGamePanel(Client client) {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("END GAME", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        winner = client.getWinnerName();

        // We create a panel for the winner and the track-board
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel text = new JLabel("<html><div style='text-align: center;'>THE WINNER IS:<br>" + client.getWinnerName() + "<div></html>", SwingConstants.CENTER);
        text.setFont(new Font("San Francisco", Font.BOLD, 25));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.add(panel, gbc);

        gbc.gridx = 1;

        // Add the point-tracker
        // ...

        add(panel, BorderLayout.CENTER);

    }
}
