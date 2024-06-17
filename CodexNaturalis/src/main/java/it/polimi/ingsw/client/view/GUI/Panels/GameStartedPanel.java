package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameStartedPanel extends JPanel {

    public GameStartedPanel(Client client){
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel title = new JLabel("GAME SET UP", SwingConstants.CENTER);
        title.setFont(new Font("Papyrus", Font.BOLD, 54));
        title.setForeground(Color.BLACK);
        title.setOpaque(false);

        add(title, BorderLayout.NORTH);

        // We create a panel for the cards
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);


        // Add cards to the panel
        for (int i = 0; i < 3; i++) {
            BufferedImage frontImage = null;
            BufferedImage backImage = null;
            try {
                frontImage = ImageIO.read(new File(client.getPlayerHand().get(i).getFrontImage()));
                backImage = ImageIO.read(new File(client.getPlayerHand().get(i).getBackImage()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            BufferedImage frontOut = new BufferedImage(300, 200, frontImage.getType());
            Graphics2D g2dF = frontOut.createGraphics();
            g2dF.drawImage(frontImage, 0, 0, 300, 200, null);
            g2dF.dispose();

            BufferedImage backOut = new BufferedImage(300, 200, backImage.getType());
            Graphics2D g2dB = backOut.createGraphics();
            g2dB.drawImage(backImage, 0, 0, 300, 200, null);
            g2dB.dispose();

            JLabel cardLabel = new JLabel(new ImageIcon(frontOut));
            cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardLabel.addMouseListener(new CardMouseListener(cardLabel, frontOut, backOut));
            cardPanel.add(cardLabel, gbc);
            gbc.gridx++;
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
