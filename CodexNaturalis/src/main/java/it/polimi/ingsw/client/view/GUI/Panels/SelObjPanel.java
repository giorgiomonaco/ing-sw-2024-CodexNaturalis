package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionObjCard;

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

public class SelObjPanel extends JPanel {

    private final List<BufferedImage> cardImages = new ArrayList<>();

    public SelObjPanel(Client client){
        setLayout(new BorderLayout());

        JLabel title = new JLabel("SELECT YOUR OBJECTIVE CARD", SwingConstants.CENTER);
        title.setFont(new Font("Papyrus", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

        add(title, BorderLayout.NORTH);

        // We create a panel for the cards
        JPanel cardPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        try {
            cardImages.add(ImageIO.read(new File(client.getPlayerObjective().getFirst().getImage())));
            cardImages.add(ImageIO.read(new File(client.getPlayerObjective().getLast().getImage())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 1;

        // Add cards to the panel
        for (BufferedImage image : cardImages) {

            JLabel tokenLabel = new JLabel(new ImageIcon(image));
            tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tokenLabel.addMouseListener(new SelObjListener(i, client));
            tokenLabel.setIcon(new ImageIcon(image));
            cardPanel.add(tokenLabel);
            i++;

        }

        add(cardPanel, BorderLayout.CENTER);

    }

    private static class SelObjListener extends MouseAdapter {
        private final int sel;
        private final Client client;

        public SelObjListener(int sel, Client client) {
            this.sel = sel;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                client.sendMessage(new SelectionObjCard(client.getUsername(), sel));
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
