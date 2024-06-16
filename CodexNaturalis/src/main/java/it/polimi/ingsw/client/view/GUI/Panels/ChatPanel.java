package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {

    /*
    The chat panel is composed by:
    text input
    button to submit input
    panel to displace the chat
    and a bar to scroll
     */
    private GridBagConstraints gbc;
    private GridBagConstraints gbcChat;
    private int mexNum;
    private JPanel writingPanel;
    private JTextField textField;
    private JButton sendButton;
    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private Client client;

    public ChatPanel(Client client){
        this.client = client;
    }

}
