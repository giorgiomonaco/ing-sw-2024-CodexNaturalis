package it.polimi.ingsw.client.view.GUI;

import com.sun.tools.javac.Main;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.states.stateEnum;
import it.polimi.ingsw.client.view.GUI.Panels.*;
import it.polimi.ingsw.client.view.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Gui implements UserInterface {

    private final Client client;

    private MyFrame frame;
    // private JLayeredPane layeredPane;
    private JPanel glassPane;
    private JPanel message;
    private boolean permission;
    private MainPanel mainPanel;


    public Gui (Client client){
        this.client = client;
        client.setUI(this);
        permission = true;
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
                if(glassPane.isVisible()){
                    glassPane.setVisible(false);
                }
                addSelTokenPanel();
                break;
            case SEL_FIRST_CARD_SIDE:
                addSelFirstPanel();
                break;
            case SELECT_OBJECTIVE:
                addSelObjPanel();
                break;
            case PLAY_CARD:
                if(glassPane.isVisible()){
                    glassPane.setVisible(false);
                }
                addMainPanel();
                break;
            case WAITING_TURN:
                glassPane.setVisible(true);
                frame.setVisible(true);
                break;
            case GAME_STOPPED:
                manageStop();
                break;
            case ALREADY_STARTED:
                break;
            case DISCONNECTION:
                break;
            case REJECTED:
                break;
            case SHOW_WINNER:
                break;
            case DRAW_CARD:
                break;
            default:
                break;
        }

    }
    @Override
    public void printChat() {
        if (mainPanel != null){
            mainPanel.getChat().addMessage1();
        }
    }

    @Override
    public void endGame() {

    }

    @Override
    public void printErrorMessage(String s) {
        if(message != null){
            frame.getContentPane().remove(message);
            frame.repaint();
        }
        message = new JPanel();
        message.setSize(new Dimension(400, 200));
        JLabel label = new JLabel(s);
        label.setForeground(Color.red);
        label.setOpaque(false);
        message.add(label);
        frame.add(message, BorderLayout.AFTER_LAST_LINE);
        frame.setVisible(true);
    }

    public void printMessage(String s) {
        if(message != null){
            frame.getContentPane().remove(message);
            frame.repaint();
        }
        message = new JPanel();
        message.setSize(new Dimension(400, 200));
        JLabel label = new JLabel(s);
        label.setOpaque(false);
        message.add(label);
        frame.add(message, BorderLayout.AFTER_LAST_LINE);
        frame.setVisible(true);
    }


    private void createFrame() {
        frame = new MyFrame();
        //BufferedImage image = null;
        //try {
        //    image = ImageIO.read(new File("src/main/resources/images/backGround.png"));
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
        //layeredPane = frame.getLayeredPane();
        //layeredPane.add(new BackGroundPanel(image), JLayeredPane.DEFAULT_LAYER);
        glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the semi-transparent cover
                g.setColor(new Color(0, 0, 0, 150)); // Colore nero con trasparenza
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                String text = "Wait for your turn...";
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                int x = (getWidth() - metrics.stringWidth(text)) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

                // Draw the text
                g.drawString(text, x, y);
            }
        };

        glassPane.setLayout(new GridBagLayout());
        glassPane.setOpaque(false);

        // Set the glassPane as the frame GlassPane
        frame.setGlassPane(glassPane);
        glassPane.setVisible(false);

        // Set the frame visible
        frame.setVisible(true);
    }

    private void addLoginPanel(){
        LoginPanel logPanel = new LoginPanel(client);
        //now we add it to the frame
        frame.add(logPanel, BorderLayout.CENTER);
        //we make the panel visible to be displaced
        frame.setVisible(true);
    }

    private void addNumOfPlayersPanel(){
        //we want to clean the frame
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new NumOfPlayersPanel(client), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addLobbyPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new LobbyPanel(client), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addGameStartedPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new GameStartedPanel(client), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addSelTokenPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new SelectTokenPanel(client), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addSelFirstPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new SelFirstCardPanel(client), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addSelObjPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new SelObjPanel(client), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addMainPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        mainPanel = new MainPanel(client);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void manageStop(){
        JPanel stopPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the semi-transparent cover
                g.setColor(new Color(0, 0, 0, 200));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                String[] lines = {
                        "-- GAME STOPPED --",
                        "If nobody rejoin the game in 30 seconds,",
                        "you will win the game."
                };
                FontMetrics metrics = g.getFontMetrics(g.getFont());

                int totalHeight = 0;
                for (String line : lines) {
                    totalHeight += metrics.getHeight();
                }
                int y = (getHeight() - totalHeight) / 2;

                for (String line : lines) {
                    int x = (getWidth() - metrics.stringWidth(line)) / 2;
                    g.drawString(line, x, y + metrics.getAscent());
                    y += metrics.getHeight();
                }
            }
        };

        stopPane.setLayout(new GridBagLayout());
        stopPane.setOpaque(false);
        permission = false;

        // Set the stopPane as the frame GlassPane
        frame.setGlassPane(stopPane);
        stopPane.setVisible(true);
    }

}
