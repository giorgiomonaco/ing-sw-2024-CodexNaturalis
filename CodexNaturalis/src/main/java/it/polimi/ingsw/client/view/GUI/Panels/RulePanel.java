package it.polimi.ingsw.client.view.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class RulePanel extends JPanel {
    private final Image backGroundImage;
    public RulePanel(Image image, int x, int y){

        setSize(new Dimension(x, y));
        backGroundImage = image;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backGroundImage != null) {
            g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }



}
