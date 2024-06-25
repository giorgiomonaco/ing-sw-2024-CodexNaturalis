package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class PointTrackerPanel extends JPanel {

    private Client client;
    public PointTrackerPanel(Client c, int dim_X, int dim_Y){
        this.client = c;
        setPreferredSize(new Dimension(dim_X, dim_Y));
        setLayout(null);
        setOpaque(true);

    }

    //method to add any element into teh panel
    public void addLabelIntoLocation(JLabel label, int label_X, int label_Y, int label_Width, int label_Height){
        //setting bounds
        label.setBounds(label_X, label_Y, label_Width, label_Height);
        label.setOpaque(false);
        add(label);
        revalidate();
        repaint();

    }
}
