package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionToken;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.List;

public class SelectTokenPanel extends JPanel {

    private List<String> tokens;

    public SelectTokenPanel(Client client){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>SELECT<br>YOUR TOKEN<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        tokens = client.getAvailableTokens();

        // We create a panel for the tokens
        JPanel tokenPanel = new JPanel(new GridBagLayout());
        tokenPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);


        // Add tokens to the panel
        for (String token : tokens) {

            ClassLoader cl = this.getClass().getClassLoader();
            BufferedImage tokenImage = null;
            String item = "images/token/CODEX_pion_" + token + ".png";
            InputStream is = cl.getResourceAsStream(item);

            if(is != null) {
                try {
                    tokenImage = ImageIO.read(is);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(tokenImage != null) {
                BufferedImage imgOut = new BufferedImage(200, 200, tokenImage.getType());
                Graphics2D g2d = imgOut.createGraphics();
                g2d.drawImage(tokenImage, 0, 0, 200, 200, null);
                g2d.dispose();

                JLabel tokenLabel = new JLabel(new ImageIcon(imgOut));
                tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tokenLabel.addMouseListener(new TokenMouseListener(token, client));
                tokenLabel.setIcon(new ImageIcon(imgOut));
                tokenPanel.add(tokenLabel, gbc);
                gbc.gridx++;
            }
        }

        add(tokenPanel, BorderLayout.CENTER);

    }

    private static class TokenMouseListener extends MouseAdapter {
        private final String token;
        private final Client client;

        public TokenMouseListener(String label, Client client) {
            this.token = label;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                client.sendMessage(new SelectionToken(client.getUsername(), token));
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
