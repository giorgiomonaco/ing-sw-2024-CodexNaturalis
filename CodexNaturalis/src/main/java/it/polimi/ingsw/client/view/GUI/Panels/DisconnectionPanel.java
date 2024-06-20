package it.polimi.ingsw.client.view.GUI.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class DisconnectionPanel extends JPanel {

    private Image backgroundImage;

    public DisconnectionPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>GAME ABORTED<br>The game was closed because the connection was lost!<br>Someone lost the connection during the setup phase, when the game wasn't even started,<br>or the server crashed for some reason.<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 28));
        title.setForeground(Color.RED);

        add(title, BorderLayout.CENTER);

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

}