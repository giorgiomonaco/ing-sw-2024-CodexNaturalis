package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.view.GUI.Panels.LoginPanel;
import it.polimi.ingsw.client.view.UserInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.InitialCard;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gui implements UserInterface {

    //layout and constraints
    private GridBagLayout gbl;
    private GridBagConstraints gbc;

    private final Client client;

    private MyFrame frame;

    public Gui (Client client){
        this.client = client;
        client.setUI(this);
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

    }

    @Override
    public void run() {
        switch (client.getCurrentState()){
            case LOGIN:
                createFrame();
                addLoginPanel();
                break;
            case LOGIN_SUCCESSFUL:
                printMessage("Login successful!");
                break;
            default:
                break;
        }

    }
    @Override
    public void printChat(){

    }

    @Override
    public void endGame() {

    }

    @Override
    public void printErrorMessage(Message msg) {

    }

    @Override
    public void printMessage(Message msg) {

    }

    public void printMessage(String s) {
        gbc = new GridBagConstraints();
        JPanel message = new JPanel();
        message.setSize(new Dimension(400, 200));
        JLabel label = new JLabel(s);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        message.add(label);
        frame.add(message, gbc);
        frame.setVisible(true);
    }


    private void createFrame(){
        frame = new MyFrame();
    }

    private void addLoginPanel(){
        LoginPanel logPanel = new LoginPanel(gbl, client);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        //now we add it to the frame
        frame.add(logPanel,gbc);
        //we make the panel visible to be displaced
        frame.setVisible(true);
    }

}
