package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.LoginRequest;
import it.polimi.ingsw.network.message.messEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Scanner;

public class LoginPanel extends JPanel {

    private GridBagConstraints gbc;
    //Prepare elements: label, text field and button
    private JLabel label1;
    private JTextField textField;
    private JButton button;
    private Image backgroundImage;

    public LoginPanel(Client client){

        this.gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());
        setOpaque(false);

        createElements();

        //Add event listener to the button
        button.addActionListener(e -> {
            //getting the text from text field
            String inputTxt = textField.getText();

            //now we use scanner as usual
            Scanner scan = new Scanner(inputTxt);
            scan.close();
            if(isAlphabetic(inputTxt) && !inputTxt.isEmpty()) {
                try {
                    //send message with the name of the player
                    client.sendMessage(new LoginRequest(messEnum.LOGIN_REQUEST, inputTxt));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                client.getUI().printErrorMessage("WRONG INSERTION! You have to insert a string of characters!");
            }

        });

        //Add event listener to the Enter button
        textField.addActionListener(e -> {
            //getting the text from text field
            String inputTxt = textField.getText();

            //now we use scanner as usual
            Scanner scan = new Scanner(inputTxt);
            scan.close();
            if(isAlphabetic(inputTxt) && !inputTxt.isEmpty()) {
                try {
                    //send message with the name of the player
                    client.sendMessage(new LoginRequest(messEnum.LOGIN_REQUEST, inputTxt));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                client.getUI().printErrorMessage("WRONG INSERTION! You have to insert a string of characters!");
            }

        });

        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("images/backGround1.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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
        gbc.anchor = GridBagConstraints.WEST;
        add(label1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(textField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(button, gbc);

    }

    public static boolean isAlphabetic(String str) {
        // Check if the string contains numbers or special characters
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
