package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Panels.*;
import it.polimi.ingsw.client.view.UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gui implements UserInterface {

    private final Client client;

    private MyFrame frame;
    private int y;

    public Gui (Client client){
        this.client = client;
        client.setUI(this);
        y = 1;
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
            case SELECT_NUM_PLAYERS:
                addNumOfPlayersPanel();
                break;
            case WAITING_LOBBY:
                printMessage("Waiting for the admin to create the lobby...");
                break;
            case LOBBY:
                addLobbyPanel();
                break;
            case GAME_STARTED:
                addGameStartedPanel();
                break;
            case SELECT_TOKEN:
                break;
            case SEL_FIRST_CARD_SIDE:
                break;
            case SELECT_OBJECTIVE:
                break;
            case WAITING_TURN:
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
    public void printErrorMessage(String s) {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel message = new JPanel();
        message.setSize(new Dimension(400, 200));
        JLabel label = new JLabel(s);
        label.setForeground(Color.red);
        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.anchor = GridBagConstraints.CENTER;
        message.add(label);
        frame.add(message, gbc);
        frame.setVisible(true);
    }

    public void printMessage(String s) {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel message = new JPanel();
        message.setSize(new Dimension(400, 200));
        JLabel label = new JLabel(s);
        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.anchor = GridBagConstraints.CENTER;
        message.add(label);
        frame.add(message, gbc);
        frame.setVisible(true);
    }


    private void createFrame() {
        frame = new MyFrame();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/resources/images/backGround.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame.setContentPane(new BackGroundPanel(image));
    }

    private void addLoginPanel(){
        LoginPanel logPanel = new LoginPanel(client);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;


        //now we add it to the frame
        frame.add(logPanel,gbc);
        //we make the panel visible to be displaced
        frame.setVisible(true);
    }

    private void addNumOfPlayersPanel(){
        //we want to clean the frame
        frame.getContentPane().remove(0);
        y--;
        frame.repaint();
        frame.add(new NumOfPlayersPanel(client));
        frame.setVisible(true);
    }

    private void addLobbyPanel(){
        frame.getContentPane().removeAll();
        y--;
        frame.repaint();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new LobbyPanel(client), gbc);
        frame.setVisible(true);
    }

    private void addGameStartedPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new GameStartedPanel(client), gbc);
        frame.setVisible(true);
    }

}
