package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionFirstCardSide;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelFirstCardPanel extends JPanel {

    private final List<BufferedImage> cardImages = new ArrayList<>();
    private Image backgroundImage;

    public SelFirstCardPanel(Client client){
        setLayout(new BorderLayout());

        // We use html to write the text on different levels
        JLabel title = new JLabel("<html><div style='text-align: center;'>SELECT THE SIDE<br>OF THE INITIAL CARD<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 54));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
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

        ClassLoader cl = this.getClass().getClassLoader();
        String pathFront = client.getInit().getFrontImage();
        String pathBack = client.getInit().getBackImage();
        InputStream isf = cl.getResourceAsStream(pathFront);
        InputStream isb = cl.getResourceAsStream(pathBack);

        try {
            cardImages.add(ImageIO.read(Objects.requireNonNull(isf, "Couldn't read the image.")));
            cardImages.add(ImageIO.read(Objects.requireNonNull(isb, "Couldn't read the image.")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 0;

        // Add cards to the panel
        for (BufferedImage image : cardImages) {

            String selection = "front";
            if(i == 1){
                selection = "back";
            }

            BufferedImage imgOut = new BufferedImage(300, 200, image.getType());
            Graphics2D g2d = imgOut.createGraphics();
            g2d.drawImage(image, 0, 0, 300, 200, null);
            g2d.dispose();

            JLabel tokenLabel = new JLabel(new ImageIcon(imgOut));
            tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tokenLabel.addMouseListener(new SelCardListener(selection, client));
            tokenLabel.setIcon(new ImageIcon(imgOut));
            cardPanel.add(tokenLabel, gbc);
            i++;
            gbc.gridx++;
        }

        add(cardPanel, BorderLayout.CENTER);

        try {
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

    private static class SelCardListener extends MouseAdapter {
        private final String sel;
        private final Client client;

        public SelCardListener(String sel, Client client) {
            this.sel = sel;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                client.sendMessage(new SelectionFirstCardSide(client.getUsername(), sel));
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}