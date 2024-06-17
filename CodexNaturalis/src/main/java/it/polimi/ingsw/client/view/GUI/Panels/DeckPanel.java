package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeckPanel extends JPanel {

    private Client client;
    private DrawPanel dp;
    private final static int CARD_X = 210;
    private final static int CARD_Y = 140;

    public DeckPanel(Client client, DrawPanel dp){
        this.client = client;
        this.dp = dp;
        initializePanel();
    }

    private void initializePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //we retrieve the images of the cards
        ImageIcon originalIcon0 = new ImageIcon(client.getDeckPath().get(1));
        ImageIcon originalIcon1 = new ImageIcon(client.getVisibleResourceCards().get(0).getFrontImage());
        ImageIcon originalIcon2 = new ImageIcon(client.getVisibleResourceCards().get(1).getFrontImage());
        ImageIcon originalIcon3 = new ImageIcon(client.getDeckPath().get(0));
        ImageIcon originalIcon4 = new ImageIcon(client.getVisibleGoldCards().get(0).getFrontImage());
        ImageIcon originalIcon5 = new ImageIcon(client.getVisibleGoldCards().get(1).getFrontImage());

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
        JLabel deckRes = new JLabel(resizedIcon0);
        JLabel firstRes = new JLabel(resizedIcon1);
        JLabel secondRes = new JLabel(resizedIcon2);
        JLabel deckGold = new JLabel(resizedIcon3);
        JLabel firstGold = new JLabel(resizedIcon4);
        JLabel secondGold = new JLabel(resizedIcon5);

        //Add the listeners to the labels
        deckRes.addMouseListener(new cardMouseListener(deckRes, 6, dp));
        firstRes.addMouseListener(new cardMouseListener(firstRes, 3, dp));
        secondRes.addMouseListener(new cardMouseListener(secondRes, 4, dp));
        deckGold.addMouseListener(new cardMouseListener(deckGold, 5, dp));
        firstGold.addMouseListener(new cardMouseListener(firstGold, 1, dp));
        secondGold.addMouseListener(new cardMouseListener(secondGold, 2, dp));


        // Set insets to reduce space between cards
        gbc.insets = new Insets(0, 0, 0, 0);

        // Set all parameters that have to be same for all
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;

        // Add every label
        gbc.gridx = 0;
        add(deckRes, gbc);

        gbc.gridx = 1;
        add(firstRes, gbc);

        gbc.gridx = 2;
        add(secondRes, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(deckGold, gbc);

        gbc.gridx = 1;
        add(firstGold, gbc);

        gbc.gridx = 2;
        add(secondGold, gbc);
    }

    private static class cardMouseListener extends MouseAdapter {
        private int index;
        private DrawPanel dp;
        private boolean isFront = true;
        private JLabel label;

        public cardMouseListener(JLabel label, int index, DrawPanel dp) {
            this.label = label;
            this.index = index;
            this.dp = dp;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isFront) {
                if(dp.getSelection() == -1) {
                    dp.setSelection(index);
                    // highlight the label
                    label.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
                    isFront = !isFront;
                }
            } else {
                dp.setSelection(-1);
                // remove the highlight
                label.setBorder(null);
                isFront = !isFront;
            }
        }
    }


}
