package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.SelectionNumPlayers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Scanner;

public class NumOfPlayersPanel extends JPanel {
    private Client client;
    private GridBagConstraints gbc;
    private Image backgroundImage;

    public NumOfPlayersPanel(Client client){
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

        button.addActionListener(e -> {
            //getting the text from text field
            String txt1 = txtField.getText();

            //now we use scanner as usual
            Scanner scan = new Scanner(txt1);
            scan.close();

            if(txt1.isEmpty() || !isNumeric(txt1)) {
                client.getUI().printErrorMessage("WRONG SELECTION! You have to insert a number, not a string.");
            } else {

                int sel = Integer.parseInt(txt1);

                if (sel > 1 && sel < 5) {
                    try {
                        //send message with the number of the players
                        client.sendMessage(new SelectionNumPlayers(client.getUsername(), sel));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    client.getUI().printErrorMessage("WRONG SELECTION! You have to select a number of player between 2 and 4.");
                }
            }

        });

        txtField.addActionListener(e -> {
            //getting the text from text field
            String txt1 = txtField.getText();

            //now we use scanner as usual
            Scanner scan = new Scanner(txt1);
            scan.close();

            if(txt1.isEmpty() || !isNumeric(txt1)) {
                client.getUI().printErrorMessage("WRONG SELECTION! You have to insert a number, not a string.");
            } else {

                int sel = Integer.parseInt(txt1);

                if (sel > 1 && sel < 5) {
                    try {
                        //send message with the number of the players
                        client.sendMessage(new SelectionNumPlayers(client.getUsername(), sel));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    client.getUI().printErrorMessage("WRONG SELECTION! You have to select a number of player between 2 and 4.");
                }
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

    public static boolean isNumeric(String str) {
        // Check if the string contains numbers or special characters
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
