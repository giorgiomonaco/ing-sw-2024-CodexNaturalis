package it.polimi.ingsw.client.view.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    //layout and constraints
    GridBagLayout gbl;
    GridBagConstraints gbc;

    public LoginPanel(GridBagLayout gbl){
        this.gbl = gbl;
        this.gbc = new GridBagConstraints();

        setSize(new Dimension(400, 300));

    }

    private void createElements(){
        //first we want to ask to insert the username:
        JLabel label1= new JLabel("Insert your Username:");
        //create a j text file to catch user writing
        JTextField textField = new JTextField(20);
        //create the button to submit the username
        JButton button = new JButton("Submit");

        //Now we add them to the panel with their constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(textField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(button,gbc);

    }
}
