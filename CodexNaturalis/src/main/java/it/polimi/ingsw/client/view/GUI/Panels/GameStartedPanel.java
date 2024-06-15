package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

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
            BufferedImage frontImage = client.getPlayerHand().get(i).getFrontImage();
            BufferedImage backImage = client.getPlayerHand().get(i).getBackImage();
            JLabel cardLabel = new JLabel(new ImageIcon(frontImage));
            cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardLabel.addMouseListener(new CardMouseListener(cardLabel, frontImage, backImage));
            cardPanel.add(cardLabel);
        }

        add(cardPanel, BorderLayout.CENTER);

    }

    private static class CardMouseListener extends MouseAdapter {
        private final JLabel cardLabel;
        private boolean isFront = true;
        private final BufferedImage frontImage;
        private final BufferedImage backImage;

        public CardMouseListener(JLabel cardLabel, BufferedImage front, BufferedImage back) {
            this.cardLabel = cardLabel;
            this.frontImage = front;
            this.backImage = back;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isFront) {
                cardLabel.setIcon(new ImageIcon(backImage));
            } else {
                cardLabel.setIcon(new ImageIcon(frontImage));
            }
            isFront = !isFront;
        }
    }

}
