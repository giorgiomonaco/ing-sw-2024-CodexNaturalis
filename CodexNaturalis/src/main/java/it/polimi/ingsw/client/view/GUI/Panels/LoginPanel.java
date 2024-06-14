package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.allMessages.LoginResponse;
import it.polimi.ingsw.network.message.messEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Scanner;

public class LoginPanel extends JPanel {

    private Client client;

    //layout and constraints
    GridBagLayout gbl;
    GridBagConstraints gbc;
    //Prepare elements: label, text field and button
    JLabel label1;
    JTextField textField;
    JButton button;


    public LoginPanel(GridBagLayout gbl, Client client){

        this.client = client;
        this.gbl = gbl;
        this.gbc = new GridBagConstraints();

        setSize(new Dimension(400, 300));


        createElements();

        //Add event listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getting the text from text field
                String inputTxt = textField.getText();

                //now we use scanner as usual
                Scanner scan = new Scanner(inputTxt);
                scan.close();

                try {
                    //send message with the name of the player
                    client.sendMessage(new LoginRequest(messEnum.LOGIN_REQUEST, inputTxt));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    }

    private void createElements(){
        //first we want to ask to insert the username:
        label1= new JLabel("Insert your Username:");
        //create a j text file to catch user writing
        textField = new JTextField(20);
        //create the button to submit the username
        button = new JButton("Submit");

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
