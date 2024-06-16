package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel {
    /*
    In this panel there will be only the cards the player got in hand
    So 3 cards
    Clickable if in play phase
    (getting bright if overed by mouse)
    when clicked -> "Do you want to play it?"
    then "send" card to the board panel
     */
    private Client client;
    public HandPanel(Client client) {
        this.client = client;
        //setting layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        //Starting from creating labels for the cards
        JLabel firstCard = new JLabel("prima");
        JLabel secondCard = new JLabel("seconda");
        JLabel thirdCard = new JLabel("terza");

        //Fill up the cards for debugging -------- DEBUG
        firstCard.setBackground(Color.gray);
        secondCard.setBackground(Color.gray);
        thirdCard.setBackground(Color.gray);


        //

        //We add a padding to the labels to separate the different cards
        gbc.insets = new Insets(5,5, 5, 5);
        //now we set that the labels have to fill up the whole space
        gbc.fill = GridBagConstraints.BOTH;

        //we set all parameters that have to be same for all
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        //add every label
        gbc.gridx = 0;
        add(firstCard, gbc);

        gbc.gridx = 1;
        add(secondCard, gbc);

        gbc.gridx = 2;
        add(thirdCard, gbc);






    }
}
