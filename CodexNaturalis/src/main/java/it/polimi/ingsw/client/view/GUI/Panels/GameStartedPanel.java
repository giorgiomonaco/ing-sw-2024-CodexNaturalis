package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class GameStartedPanel extends JPanel {

    public GameStartedPanel(Client client){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("GAME STARTED", SwingConstants.CENTER);
        title.setFont(new Font("Papyrus", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        // We create a panel for the cards
        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        // Add cards to the panel
        for (int i = 0; i < 3; i++) {
            JLabel cardLabel = new JLabel(new ImageIcon(cardFrontImage));
            cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardLabel.addMouseListener(new CardMouseListener(cardLabel));
            cardPanel.add(cardLabel);
        }


    }
}
