package it.polimi.ingsw.client.view.GUI.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public ChatPanel(){

        setSize(new Dimension(200, 500));
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //create the "sending panel"
        writingPanel = new JPanel();
        writingPanel.setLayout(new GridBagLayout());

        //Create text field and button
        textField = new JTextField(20);
        sendButton = new JButton("Send");

        //adding to panel
        gbc.gridx = 0;
        writingPanel.add(textField,gbc);
        gbc.gridx = 1;
        writingPanel.add(sendButton,gbc);


        //now we proceed with the chat history part
        chatPanel = new JPanel();
        gbcChat = new GridBagConstraints();
        gbcChat.gridx = 0;
        gbcChat.gridy = GridBagConstraints.RELATIVE;
        gbcChat.weightx = 1.0;
        gbcChat.fill = GridBagConstraints.HORIZONTAL;
        gbcChat.anchor = GridBagConstraints.PAGE_END;
        mexNum = 0; //Keep track of num of mex = to place in right row mexs

        //We add the chat panel to a scrolling pane
        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Add everything to the main panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(writingPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMessage(textField.getText());
                textField.setText("");
            }
        });

    }

    private void addMessage(String mex){
        JPanel mexPanel = new JPanel();
        mexPanel.add(new JLabel(mex));

        gbcChat.gridy = mexNum;
        chatPanel.add(mexPanel, gbcChat);
        mexNum++;

        chatPanel.revalidate();
        chatPanel.repaint();
    }
}
