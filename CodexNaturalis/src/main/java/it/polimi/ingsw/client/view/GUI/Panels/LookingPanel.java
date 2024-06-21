package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Frames.PointTrackerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LookingPanel extends JPanel {

    //we want to add button to look at point tracker
    //and to look at drawing possibilities
    private final Client client;
    public LookingPanel(Client c){
        this.client = c;
        createElements();
    }
    private void createElements(){
        //first we create the looker of the point tracker
        JButton pointsLooker = new JButton("Points");

        //now we determine the action to be performed at button click
        pointsLooker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //we want to displace the point tracker frame
                try {
                    PointTrackerFrame scoreFrame = new PointTrackerFrame(client);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(pointsLooker);
    }

}
