package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
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

    //constants:
    private static final int DRAWABLE_CARDS_FRAME_X = 650;
    private static final int DRAWABLE_CARDS_FRAME_Y = 450;

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
        cardsSituationButton.addActionListener(e-> showCards());
    }

    private void showCards(){
        //first we create the frame to contain the selection of cards
        JFrame drawableCards = new JFrame();
        drawableCards.setSize(new Dimension(DRAWABLE_CARDS_FRAME_X, DRAWABLE_CARDS_FRAME_Y));
        drawableCards.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        drawableCards.setVisible(true);
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
                PointTrackerFrame scoreFrame = new PointTrackerFrame(client);
            }
        });
        add(pointsLooker);
    }

}
