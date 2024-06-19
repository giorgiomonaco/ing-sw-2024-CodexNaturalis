package it.polimi.ingsw.client.view.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class AlreadyStartedPanel extends JPanel {

    public AlreadyStartedPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>Sorry, the game you are trying to join is already started,<br>or the max number of player is already reached, wait for this game to finish.<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 38));
        title.setForeground(Color.RED);

        add(title, BorderLayout.CENTER);

    }
}
