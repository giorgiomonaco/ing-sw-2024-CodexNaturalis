package it.polimi.ingsw.client.view.GUI;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Frames.DrawFrame;
import it.polimi.ingsw.client.view.GUI.Frames.MyFrame;
import it.polimi.ingsw.client.view.GUI.Panels.*;
import it.polimi.ingsw.client.view.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Gui implements UserInterface {

    private final Client client;
    private MyFrame frame;
    private JPanel glassPane;
    private JPanel stopPane;
    private JPanel message;
    private MainPanel mainPanel;
    private boolean gameSetUp;


    public Gui (Client client){
        this.client = client;
        client.setUI(this);
        gameSetUp = true;
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
            case GAME_SETUP:
                addGameSetUpPanel();
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
                gameSetUp = false;
                break;
            case PLAY_CARD:
                managePlay();
                break;
            case WAITING_TURN:
                manageWait();
                break;
            case GAME_STOPPED:
                manageStop();
                break;
            case ALREADY_STARTED:
                addAlreadyStartedPanel();
                break;
            case DISCONNECTION:
                addDisconnectionPanel();
                break;
            case REJECTED:
                addRejectedPanel();
                break;
            case END_GAME: //Refine this!!
                try {
                    addEndGamePanel();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case DRAW_CARD:
                addDrawFrame();
                break;
            default:
                break;
        }

    }
    @Override
    public void printChat() {
        if (mainPanel != null){
            mainPanel.getChat().addMessage();
        }
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

    private void addGameSetUpPanel(){
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
        mainPanel = new MainPanel(client, client.getTurn());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        mainPanel.getBoard().scrollToMiddle();
    }

    private void addDrawFrame() {
        DrawFrame drawFrame = new DrawFrame(client);
        drawFrame.setVisible(true);
    }

    private void addAlreadyStartedPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new AlreadyStartedPanel());
        frame.setVisible(true);
    }

    private void addRejectedPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new RejectedPanel());
        frame.setVisible(true);
    }

    private void addDisconnectionPanel(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new DisconnectionPanel());
        frame.setVisible(true);
    }

    private void addEndGamePanel() throws IOException {
        if(stopPane != null && stopPane.isVisible()){
            stopPane.setVisible(false);
        }

        frame.getContentPane().removeAll();
        frame.repaint();
        frame.add(new EndGamePanel(client));
        frame.setVisible(true);
    }

    private void manageStop(){
        if(stopPane != null){
            stopPane.setVisible(true);

            // block every action on the main panel
            if(mainPanel != null) {
                mainPanel.setStop(true);
            }

        } else {
            stopPane = new JPanel() {
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

            // block every action on the main panel
            if(mainPanel != null) {
                mainPanel.setStop(true);
            }

            // Set the stopPane as the frame GlassPane
            frame.setGlassPane(stopPane);
            stopPane.setVisible(true);
        }
    }

    public void manageWait() {

        // Clean the screen if there is a visible message
        if(message != null){
            frame.getContentPane().remove(message);
            frame.repaint();
        }

        // If we are in the first turn of the game set up, only cover with a glass pane
        if(gameSetUp){
            glassPane.setVisible(true);
            frame.setVisible(true);
            return;
        }

        // Check if it's a message of the client end turn and build or re-build the main panel
        if(client.isEndTurn()) {
            if (mainPanel == null) {
                addMainPanel();
            } else {
                mainPanel.updatePanel();
                frame.setVisible(true);
                mainPanel.getBoard().scrollToMiddle();
            }
        }

        // Update turns
        if(mainPanel != null) {
            mainPanel.setYourTurn(false);
            mainPanel.setIndex(client.getCurrIndex());
        }

    }

    public void managePlay() {

        // Clean the screen if there is a visible message
        if(message != null){
            frame.getContentPane().remove(message);
            frame.repaint();
        }

        // Remove the glass pane to start the turn
        if(glassPane.isVisible()){
            glassPane.setVisible(false);
        }

        // If the game was stopped, clean the screen and reactivate the main panel buttons
        if(stopPane != null && stopPane.isVisible()){
            stopPane.setVisible(false);
            if (mainPanel != null) {
                mainPanel.setStop(false);
            }
        }


        if(mainPanel == null) {
            addMainPanel();
        }
        mainPanel.setYourTurn(true);
        mainPanel.setIndex(client.getPlayerList().indexOf(client.getUsername()));
        if(gameSetUp){
            gameSetUp = false;
        }

    }

}
