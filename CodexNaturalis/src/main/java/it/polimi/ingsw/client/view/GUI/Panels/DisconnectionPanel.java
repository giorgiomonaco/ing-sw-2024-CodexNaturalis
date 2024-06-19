package it.polimi.ingsw.client.view.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class DisconnectionPanel extends JPanel {

    public DisconnectionPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>GAME ABORTED<br>The game was closed because the connection was lost!<br>>Someone lost the connection during the setup phase, when the game wasn't even started,<br>or the server crashed for some reason.<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 28));
        title.setForeground(Color.RED);

        add(title, BorderLayout.CENTER);

    }

}
