package it.polimi.ingsw.client.view.GUI.Panels;

import com.sun.tools.javac.Main;
import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class HandPanel extends JPanel {

    private final static int CARD_X = 210;
    private final static int CARD_Y = 140;

    /*
    In this panel there will be only the cards the player got in hand
    So 3 cards
    Clickable if in play phase
    (getting bright if hovered by mouse)
    when clicked -> "Do you want to play it?"
    then "send" card to the board panel
     */

    private Client client;
    JLabel firstCard;
    JLabel secondCard;
    JLabel thirdCard;

    public HandPanel(Client client, MainPanel mainPanel) {
        this.client = client;
        //setting layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //we retrieve the images of the cards
        ImageIcon originalIcon0 = new ImageIcon(client.getPlayerHand().get(0).getFrontImage());
        ImageIcon originalIcon1 = new ImageIcon(client.getPlayerHand().get(1).getFrontImage());
        ImageIcon originalIcon2 = new ImageIcon(client.getPlayerHand().get(2).getFrontImage());
        ImageIcon originalIcon3 = new ImageIcon(client.getPlayerHand().get(0).getBackImage());
        ImageIcon originalIcon4 = new ImageIcon(client.getPlayerHand().get(1).getBackImage());
        ImageIcon originalIcon5 = new ImageIcon(client.getPlayerHand().get(2).getBackImage());

        //then we get the images rescaled
        Image image0 = originalIcon0.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image1 = originalIcon1.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image2 = originalIcon2.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image3 = originalIcon3.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image4 = originalIcon4.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image image5 = originalIcon5.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);

        //Add them to the right icons
        ImageIcon resizedIcon0 = new ImageIcon(image0);
        ImageIcon resizedIcon1 = new ImageIcon(image1);
        ImageIcon resizedIcon2 = new ImageIcon(image2);
        ImageIcon resizedIcon3 = new ImageIcon(image3);
        ImageIcon resizedIcon4 = new ImageIcon(image4);
        ImageIcon resizedIcon5 = new ImageIcon(image5);

        //Create the labels with the image icons
        JLabel firstCard = new JLabel(resizedIcon0);
        firstCard.addMouseListener(new cardMouseListener(firstCard, 0, mainPanel, client));
        JLabel secondCard = new JLabel(resizedIcon1);
        secondCard.addMouseListener(new cardMouseListener(secondCard, 1, mainPanel, client));
        JLabel thirdCard = new JLabel(resizedIcon2);
        thirdCard.addMouseListener(new cardMouseListener(thirdCard, 2, mainPanel, client));

        List<JLabel> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.add(secondCard);
        cards.add(thirdCard);

        List<ImageIcon> frontIcons = new ArrayList<>();
        frontIcons.add(resizedIcon0);
        frontIcons.add(resizedIcon1);
        frontIcons.add(resizedIcon2);

        List<ImageIcon> backIcons = new ArrayList<>();
        backIcons.add(resizedIcon3);
        backIcons.add(resizedIcon4);
        backIcons.add(resizedIcon5);

        // Create the button
        JButton actionButton = new JButton("Swap");
        actionButton.addMouseListener(new buttonListener(cards, frontIcons, backIcons, mainPanel));

        // Set insets to reduce space between cards
        gbc.insets = new Insets(0, 0, 0, 0);

        // Set all parameters that have to be same for all
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;

        // Add the button first
        gbc.gridx = 0;
        add(actionButton, gbc);

        // Add every label
        gbc.gridx = 1;
        add(firstCard, gbc);

        gbc.gridx = 2;
        add(secondCard, gbc);

        gbc.gridx = 3;
        add(thirdCard, gbc);
    }

    private static class buttonListener extends MouseAdapter {
        private final List<JLabel> cardLabel;
        private boolean isFront = true;
        private final List<ImageIcon> frontImage;
        private final List<ImageIcon> backImage;
        private MainPanel mainPanel;

        public buttonListener(List<JLabel> cardLabel, List<ImageIcon> front, List<ImageIcon> back, MainPanel mainPanel) {
            this.cardLabel = cardLabel;
            this.frontImage = front;
            this.backImage = back;
            this.mainPanel = mainPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isFront) {
                cardLabel.get(0).setIcon(backImage.get(0));
                cardLabel.get(1).setIcon(backImage.get(1));
                cardLabel.get(2).setIcon(backImage.get(2));
                mainPanel.setSide(false);
            } else {
                cardLabel.get(0).setIcon(frontImage.get(0));
                cardLabel.get(1).setIcon(frontImage.get(1));
                cardLabel.get(2).setIcon(frontImage.get(2));
                mainPanel.setSide(true);
            }
            isFront = !isFront;
        }
    }

    private static class cardMouseListener extends MouseAdapter {
        private int index;
        private MainPanel mainPanel;
        private boolean isFront = true;
        private JLabel label;
        private Client client;

        public cardMouseListener(JLabel label, int index, MainPanel mainPanel, Client client) {
            this.label = label;
            this.index = index;
            this.mainPanel = mainPanel;
            this.client = client;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isFront) {
                if(mainPanel.getCard() == null) {
                    mainPanel.setCard(client.getPlayerHand().get(index));
                    // highlight the label
                    label.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
                    isFront = !isFront;
                }
            } else {
                mainPanel.setCard(null);
                // remove the highlight
                label.setBorder(null);
                isFront = !isFront;
            }
        }
    }
}
