package it.polimi.ingsw.client.view.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class RejectedPanel extends JPanel {

    public RejectedPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>Sorry, your request<br>to join the game has been rejected,<br>the game may is already full.<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 38));
        title.setForeground(Color.RED);

        add(title, BorderLayout.CENTER);

    }

}
