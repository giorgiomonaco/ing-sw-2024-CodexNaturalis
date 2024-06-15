package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionToken;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class SelectTokenPanel extends JPanel {

    private List<String> tokens;

    public SelectTokenPanel(Client client){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("SELECT YOUR TOKEN", SwingConstants.CENTER);
        title.setFont(new Font("Papyrus", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        tokens = client.getAvailableTokens();

        // We create a panel for the tokens
        JPanel tokenPanel = new JPanel(new GridLayout(1, tokens.size(), 10, 10));

        // Add cards to the panel
        for (String token : tokens) {
            BufferedImage tokenImage = null;
            switch (token) {
                case "red":
                    try {
                        tokenImage = ImageIO.read(new File("src/main/resources/images/token/CODEX_pion_rouge.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "blue":
                    try {
                        tokenImage = ImageIO.read(new File("src/main/resources/images/token/CODEX_pion_bleu.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "yellow":
                    try {
                        tokenImage = ImageIO.read(new File("src/main/resources/images/token/CODEX_pion_jaune.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "green":
                    try {
                        tokenImage = ImageIO.read(new File("src/main/resources/images/token/CODEX_pion_vert.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            if(tokenImage != null) {
                JLabel tokenLabel = new JLabel(new ImageIcon(tokenImage));
                tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tokenLabel.addMouseListener(new TokenMouseListener(token, client));
                tokenLabel.setIcon(new ImageIcon(tokenImage));
                tokenPanel.add(tokenLabel);
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
