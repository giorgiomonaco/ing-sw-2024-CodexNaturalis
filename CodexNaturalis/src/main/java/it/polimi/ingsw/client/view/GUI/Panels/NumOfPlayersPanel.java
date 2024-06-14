package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class NumOfPlayersPanel extends JPanel {
    Client client;
    GridBagConstraints gbc;
    public NumOfPlayersPanel(){
        this.client = client;

        /*
        Create the text for the message
        Create the field for answer
        Create the button to submit
         */
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //create the label
        JLabel txt = new JLabel("Insert num of players:");

        //create the response field
        JTextField txtField = new JTextField(10);

        //Create the button
        JButton button = new JButton("Submit");
        add(txt);
        add(txtField);
        add(button);


    }
}
