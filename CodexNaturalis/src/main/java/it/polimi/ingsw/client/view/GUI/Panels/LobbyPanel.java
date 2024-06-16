package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LobbyPanel extends JPanel {

    public LobbyPanel(Client client) {
        setLayout(new GridBagLayout());
        setOpaque(false);

        JLabel lobbyTitle = new JLabel("LOBBY", SwingConstants.CENTER);
        lobbyTitle.setFont(new Font("Papyrus", Font.BOLD, 54));
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
        scrollPane.setPreferredSize(new Dimension(200,350));

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        gbc1.gridheight = 1;
        gbc1.gridwidth = 1;
        gbc1.insets = new Insets(10, 10, 10, 10);
        gbc1.anchor = GridBagConstraints.CENTER;

        add(scrollPane, gbc1);
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

            // Aggiungi un bordo alle celle
            label.setBorder(new EmptyBorder(10, 10, 10, 10));
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            } else if (value.equals(clientUsername)) {
                label.setBackground(Color.YELLOW);  // Colora la cella dell'utente corrente in giallo
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
