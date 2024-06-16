package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionFirstCardSide;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class SelFirstCardPanel extends JPanel {

    private final List<BufferedImage> cardImages = new ArrayList<>();

    public SelFirstCardPanel(Client client){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("SELECT THE SIDE OF THE INITIAL CARD", SwingConstants.CENTER);
        title.setFont(new Font("Papyrus", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

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

        try {
            cardImages.add(ImageIO.read(new File(client.getInit().getFrontImage())));
            cardImages.add(ImageIO.read(new File(client.getInit().getBackImage())));
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
