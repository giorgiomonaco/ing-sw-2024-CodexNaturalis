package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.model.*;

import javax.swing.*;
import java.awt.*;

public class ObjectivePanel extends JPanel {
    private final static int CARD_X = 150;
    private final static int CARD_Y = 100;
    /*
    In this panel we want to show the objective cards the player has
     */
    private Client client;
    private ObjectiveCard firstPersonal;
    private ObjectiveCard secondPersonal;
    private ObjectiveCard globalObj;
    private GridBagConstraints gbc;
    public ObjectivePanel(Client c){
        this.client = c;
        //setting layout to gbl
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        //create and displace the cards
        displaceObjectiveCards();

    }
    private void displaceObjectiveCards(){
        /*
        We create the icons
        We extract image
        We resize it
        We recreate the icons
        we add icons o labels
         */
        ImageIcon personalObj = new ImageIcon(client.getObjective().getImage());
        ImageIcon firstGlobalObj = new ImageIcon(client.getCommonObjectives().get(0).getImage());
        ImageIcon secondGlobalObj = new ImageIcon(client.getCommonObjectives().get(1).getImage());


        Image resizedPersonal = personalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image resizedFirstCommon = firstGlobalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image resizedSecondCommon = secondGlobalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);

        ImageIcon officialObj = new ImageIcon(resizedPersonal);
        ImageIcon officialFirstGlobal = new ImageIcon(resizedFirstCommon);
        ImageIcon officialSecondGlobal = new ImageIcon(resizedSecondCommon);

        JLabel firsCommonObjCard = new JLabel(officialFirstGlobal);
        JLabel secondCommonObjCard = new JLabel(officialSecondGlobal);
        JLabel personalCard = new JLabel(officialObj);

        //now we place the cards into the panel
        //first common parameters
        gbc.weighty = 1.0;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        //then for each

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(personalCard,gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(firsCommonObjCard,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(secondCommonObjCard,gbc);

    }
}
