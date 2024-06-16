package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
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
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        try {
            cardImages.add(ImageIO.read(new File(client.getPlayerObjective().getFirst().getImage())));
            cardImages.add(ImageIO.read(new File(client.getPlayerObjective().getLast().getImage())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 1;

        // Add cards to the panel
        for (BufferedImage image : cardImages) {

            BufferedImage imgOut = new BufferedImage(300, 200, image.getType());
            Graphics2D g2d = imgOut.createGraphics();
            g2d.drawImage(image, 0, 0, 300, 200, null);
            g2d.dispose();

            JLabel objLabel = new JLabel(new ImageIcon(imgOut));
            objLabel.setHorizontalAlignment(SwingConstants.CENTER);
            objLabel.addMouseListener(new SelObjListener(i, client));
            objLabel.setIcon(new ImageIcon(imgOut));
            cardPanel.add(objLabel, gbc);
            i++;
            gbc.gridx++;

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
                if(client.getCurrentState().equals(stateEnum.SELECT_OBJECTIVE)) {
                    try {
                     client.sendMessage(new SelectionObjCard(client.getUsername(), sel));
                  } catch (RemoteException ex) {
                     throw new RuntimeException(ex);
                 }
                }
            }

    }

}
