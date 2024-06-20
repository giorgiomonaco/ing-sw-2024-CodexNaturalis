package it.polimi.ingsw.client.view.GUI.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class RejectedPanel extends JPanel {

    private Image backgroundImage;

    public RejectedPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("<html><div style='text-align: center;'>Sorry, your request<br>to join the game has been rejected,<br>the game may is already full.<div></html>", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 38));
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
