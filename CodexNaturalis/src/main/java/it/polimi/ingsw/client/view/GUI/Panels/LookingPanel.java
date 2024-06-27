package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Frames.PointTrackerFrame;
import it.polimi.ingsw.client.view.GUI.Frames.RulesFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookingPanel extends JPanel {

    //we want to add button to look at point tracker
    //and to look at drawing possibilities
    private final Client client;
    private JButton pointsLooker;
    private JButton rulesButton;
    public LookingPanel(Client c){
        this.client = c;
        createElements();
        setOpaque(false);
        setVisible(true);
    }
    private void createElements(){
        //we create the button for the rules
        createRulesButton();
        //Creation of the points button
        createPointsButton();

    }

    private void createRulesButton(){
        rulesButton = new JButton("How To Play");

        //now we determine the action to be performed at button click
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //we want to displace the point tracker frame
                RulesFrame rulesFrame = new RulesFrame(client);
            }
        });
        add(rulesButton);
    }

    private void createPointsButton(){
        //first we create the looker of the point tracker
        pointsLooker = new JButton("Current Points");

        //now we determine the action to be performed at button click
        pointsLooker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //we want to displace the point tracker frame
                PointTrackerFrame scoreFrame = new PointTrackerFrame(client);
            }
        });
        add(pointsLooker);
    }

}
