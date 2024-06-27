package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Frames.DrawFrame;
import it.polimi.ingsw.client.view.GUI.Frames.PointTrackerFrame;
import it.polimi.ingsw.client.view.GUI.Frames.RulesFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookingPanel extends JPanel {

    /*
    we want to add button to look at point tracker
    and to look at drawing possibilities
    and to rules of the game
     */

    //Attributes
    private final Client client;
    private JButton pointsLooker;
    private JButton rulesButton;
    private JButton cardsSituationButton;

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
        //creation of the button to look at which cards are on the table to be drawn
        createCardsSituationButton();

    }

    private void createCardsSituationButton(){
        cardsSituationButton = new JButton("Drawable Cards");

        //action to be performed by clicking it
        cardsSituationButton.addActionListener(e -> showCards());
        add(cardsSituationButton);
    }

    private void showCards(){

        if(!client.getVisibleResourceCards().isEmpty()) {
            DrawFrame drawFrame = new DrawFrame(client);
            drawFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            drawFrame.setVisible(true);
        } else {
            client.getUI().printErrorMessage("Wait for the other player to finish the setup phase.");
        }

    }

    private void createRulesButton(){
        rulesButton = new JButton("Rules");

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
        pointsLooker = new JButton("Points");

        //now we determine the action to be performed at button click
        pointsLooker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //we want to displace the point tracker frame
                if (!client.getPlayersToken().contains(null)) {
                    PointTrackerFrame scoreFrame = new PointTrackerFrame(client);
                } else {
                    client.getUI().printErrorMessage("Wait for the other player to finish the setup phase.");
                }
            }
        });

        add(pointsLooker);
    }

}
