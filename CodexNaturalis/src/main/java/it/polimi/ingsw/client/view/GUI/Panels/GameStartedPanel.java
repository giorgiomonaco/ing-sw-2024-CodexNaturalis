package it.polimi.ingsw.client.view.GUI.Panels;
import it.polimi.ingsw.client.Client;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GameStartedPanel extends JPanel {

    private Image backgroundImage;

    public GameStartedPanel(Client client){
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel title = new JLabel("GAME SET UP", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 54));
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

            ClassLoader cl = this.getClass().getClassLoader();
            BufferedImage frontImage = null;
            BufferedImage backImage = null;
            String pathFront = client.getPlayerHand().get(i).getFrontImage();
            String pathBack = client.getPlayerHand().get(i).getBackImage();
            InputStream isf = cl.getResourceAsStream(pathFront);
            InputStream isb = cl.getResourceAsStream(pathBack);

            try {
                frontImage = ImageIO.read(Objects.requireNonNull(isf, "Couldn't read the image."));
                backImage = ImageIO.read(Objects.requireNonNull(isb, "Couldn't read the image."));
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

        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("images/backGround3.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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