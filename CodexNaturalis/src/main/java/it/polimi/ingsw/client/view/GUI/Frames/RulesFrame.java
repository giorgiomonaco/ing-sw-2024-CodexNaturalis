package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Panels.RulePanel;
import it.polimi.ingsw.server.model.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class RulesFrame extends JFrame {
    private static final int FRAME_X = 550;
    private static final int FRAME_Y = 550;
    private static final int BUTTONS_HEIGHT = 75;
    private static final int NUM_RULE_PAGES = 9;

    private Client client;
    private JPanel rulesContainerPanel;
    private Image[] images = new Image[NUM_RULE_PAGES];
    private int currentRulePage = 0;
    private JButton goNextButton;
    private JButton goPreviousButton;
    private JPanel buttonsPanel;

    public RulesFrame(Client c) {
        this.client = c;
        setTitle("GAME RULES");
        setSize(new Dimension(FRAME_X, FRAME_Y));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //retrieve images from the server
        retrieveImages();

        //Creating the panel with card layout to manage different panels displacement
        rulesContainerPanel = new JPanel(new CardLayout());

        //We add the panels to the "card manager"
        addRulesToPanel();

        //Creating buttons for going ahead or back
        buttonsCreation();

        //creating button panel and adding the 2 buttons
        createButtonPanel();

        //Adding of the panels to the frame
        add(rulesContainerPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void retrieveImages() {
        for (int i = 0; i < NUM_RULE_PAGES; i++) {
            String imagePath = "images/rulesPages/RulePage" + i + ".png";
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(imagePath)) {
                if (is != null) {
                    images[i] = ImageIO.read(is);
                } else {
                    System.err.println("Image not found: " + imagePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showPreviousPanel() {
        if(currentRulePage > 0) {
            currentRulePage = currentRulePage - 1;
            CardLayout cardLayout = (CardLayout) rulesContainerPanel.getLayout();
            cardLayout.show(rulesContainerPanel, String.valueOf(currentRulePage));
        }
    }

    private void showNextPanel() {
        if(currentRulePage < NUM_RULE_PAGES - 1) {
            currentRulePage = currentRulePage + 1;
            CardLayout cardLayout = (CardLayout) rulesContainerPanel.getLayout();
            cardLayout.show(rulesContainerPanel, String.valueOf(currentRulePage));
        }
    }

    private void addRulesToPanel(){
        for (int i = 0; i < NUM_RULE_PAGES; i++) {
            RulePanel rulePanel = new RulePanel(images[i], FRAME_X, FRAME_Y - BUTTONS_HEIGHT);
            rulesContainerPanel.add(rulePanel, String.valueOf(i));
        }
    }

    private void buttonsCreation(){
        goPreviousButton  = new JButton("< Previous");
        goPreviousButton.addActionListener(e -> showPreviousPanel());

        goNextButton = new JButton("Next >");
        goNextButton.addActionListener(e -> showNextPanel());
    }

    private void createButtonPanel(){
        buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(goPreviousButton, BorderLayout.WEST);
        buttonsPanel.add(goNextButton, BorderLayout.EAST);
    }


}