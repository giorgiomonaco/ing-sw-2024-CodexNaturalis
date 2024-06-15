package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionFirstCardSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
        JPanel cardPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        cardImages.add(client.getInit().getFrontImage());
        cardImages.add(client.getInit().getBackImage());
        int i = 0;

        // Add cards to the panel
        for (BufferedImage image : cardImages) {

            String selection = "front";
            if(i == 1){
                selection = "back";
            }

            JLabel tokenLabel = new JLabel(new ImageIcon(image));
            tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tokenLabel.addMouseListener(new SelCardListener(selection, client));
            tokenLabel.setIcon(new ImageIcon(image));
            cardPanel.add(tokenLabel);
            i++;
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
