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
        ImageIcon firstPersonalObj = new ImageIcon(client.getPlayerObjective().get(0).getImage());
        ImageIcon secondPersonalObj = new ImageIcon(client.getPlayerObjective().get(1).getImage());
        ImageIcon firstGlobalObj = new ImageIcon(client.getCommonObjectives().get(0).getImage());
        ImageIcon secondGlobalObj = new ImageIcon(client.getCommonObjectives().get(1).getImage());


        Image resizedFirstPersonal = firstPersonalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image resizedSecondPersonal = secondPersonalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image resizedfirstCommon = firstGlobalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image resizedSecondCommon = secondGlobalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);

        ImageIcon officialFirstObj = new ImageIcon(resizedFirstPersonal);
        ImageIcon officialSecondObj = new ImageIcon(resizedSecondPersonal);
        ImageIcon officialFirstGlobal = new ImageIcon(resizedfirstCommon);
        ImageIcon officialSecondGlobal = new ImageIcon(resizedSecondCommon);

        JLabel firsCommonObjCard = new JLabel(officialFirstGlobal);
        JLabel secondGlobalObjCard = new JLabel(officialSecondObj);
        JLabel firstPersonalCard = new JLabel(officialFirstObj);
        JLabel secondPersonalCard = new JLabel(officialSecondObj);

        //now we place the cards into the panel
        //first common parameters
        gbc.weighty = 1.0;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        //then for each

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(firstPersonalCard,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(secondPersonalCard, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(firsCommonObjCard,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(secondGlobalObjCard,gbc);

    }
}
