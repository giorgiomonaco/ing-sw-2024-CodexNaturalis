package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class LobbyPanel extends JPanel {

    private Image backgroundImage;

    public LobbyPanel(Client client) {
        setLayout(new GridBagLayout());
        setOpaque(false);

        JLabel lobbyTitle = new JLabel("LOBBY", SwingConstants.CENTER);
        lobbyTitle.setFont(new Font("San Francisco", Font.BOLD, 54));
        lobbyTitle.setForeground(Color.BLACK);
        lobbyTitle.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        add(lobbyTitle, gbc);

        JList<String> playerList = new JList<>(new DefaultListModel<>());
        DefaultListModel<String> listModel = (DefaultListModel<String>) playerList.getModel();
        for (String player : client.getPlayerList()) {
            listModel.addElement(player);
        }
        playerList.setFont(new Font("Serif", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(playerList);
        playerList.setCellRenderer(new CenteredListCellRenderer(client.getUsername()));
        scrollPane.setPreferredSize(new Dimension(200,200));

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        gbc1.gridheight = 1;
        gbc1.gridwidth = 1;
        gbc1.insets = new Insets(10, 10, 10, 10);
        gbc1.anchor = GridBagConstraints.CENTER;

        add(scrollPane, gbc1);

        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("images/backGround2.png");
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

    private static class CenteredListCellRenderer extends DefaultListCellRenderer {
        private String clientUsername;

        public CenteredListCellRenderer(String clientUsername) {
            this.clientUsername = clientUsername;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.PLAIN, 18));

            // Adding border to the cells
            label.setBorder(new EmptyBorder(10, 10, 10, 10));
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            } else if (value.equals(clientUsername)) {
                // Color the cell with the client username
                label.setBackground(Color.YELLOW);
                label.setForeground(list.getForeground());
            } else {
                label.setBackground(list.getBackground());
                label.setForeground(list.getForeground());
            }

            label.setOpaque(true);
            label.setBorder(new LineBorder(Color.GRAY, 1));

            return label;
        }
    }

}
