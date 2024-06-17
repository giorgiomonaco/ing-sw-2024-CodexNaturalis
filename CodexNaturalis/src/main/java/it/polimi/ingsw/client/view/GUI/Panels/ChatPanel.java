package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.message.allMessages.ChatMessage;
import it.polimi.ingsw.server.model.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ChatPanel extends JPanel {

    /*
    The chat panel is composed by:
    text input
    button to submit input
    panel to display the chat
    and a bar to scroll
     */
    private GridBagConstraints gbc;
    private JPanel writingPanel;
    private JTextField textField;
    private JButton sendButton;
    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private Client client;

    public ChatPanel(Client client) {
        this.client = client;
        initComponents();
    }

    private void initComponents() {
        // Set the layout
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // Chat panel
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.9;
        add(scrollPane, gbc);

        // Writing panel
        writingPanel = new JPanel();
        writingPanel.setLayout(new BorderLayout());

        textField = new JTextField();
        sendButton = new JButton("Send");

        writingPanel.add(textField, BorderLayout.CENTER);
        writingPanel.add(sendButton, BorderLayout.EAST);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        add(writingPanel, gbc);

        // Add listener to the button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText().trim();
                String destination = null;
                if (!message.isEmpty()) {
                    if(!message.startsWith("/")){
                        destination = "all";
                    } else {
                        message = message.substring(1).trim();
                        String[] words = message.split("\\s+");
                        destination = words[0];
                        int destLength = words[0].length();

                        if (message.length() > destLength) {
                            message = message.substring(destLength).trim();
                        } else {
                            message = "";
                        }
                    }
                    try {
                        client.sendMessage(new ChatMessage(client.getUsername(), destination, message));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    textField.setText("");
                }
            }
        });

        // Add listener to press Enter on the textField
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText().trim();
                String destination = null;
                if (!message.isEmpty()) {
                    if(!message.startsWith("/")){
                        destination = "all";
                    } else {
                        message = message.substring(1).trim();
                        String[] words = message.split("\\s+");
                        destination = words[0];
                        int destLength = words[0].length();

                        if (message.length() > destLength) {
                            message = message.substring(destLength).trim();
                        } else {
                            message = "";
                        }
                    }
                    try {
                        client.sendMessage(new ChatMessage(client.getUsername(), destination, message));
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    textField.setText("");
                }
            }
        });
    }

    public void addMessage() {
        Chat lastChat = client.getChat().getLast();

        String message = lastChat.getSender() + ": " + lastChat.getMsg();
        JLabel messageLabel = null;

        if(lastChat.isPrivate()){
            message = message +  " (to "+ lastChat.getReceiver() + ")";
            messageLabel = new JLabel(message);
            messageLabel.setForeground(Color.blue);
        } else {
            messageLabel = new JLabel(message);
        }

        chatPanel.add(messageLabel);
        chatPanel.revalidate();
        chatPanel.repaint();

        // Scroll
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}
