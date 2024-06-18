package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    private final static int CARD_X = 210;
    private final static int CARD_Y = 140;
    private final static int GAP_X = -45; // Negative gap for overlap in X direction
    private final static int GAP_Y = -55; // Negative gap for overlap in Y direction
    private JScrollPane scrollPane;
    private Client client;
    private JLayeredPane layeredPane;
    int rows = 100;
    int cols = 100;
    private MainPanel mainPanel;

    /**
     * Constructor for BoardPanel.
     * @param client The client instance used for communication.
     */
    public BoardPanel(Client client, MainPanel mainPanel) {
        this.client = client;
        this.mainPanel = mainPanel;
        initializeBoard();
    }

    /**
     * Initializes the board layout and components.
     */
    private void initializeBoard() {
        setLayout(new BorderLayout());

        // Create a JLayeredPane to hold the cards
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(cols * (CARD_X + GAP_X), rows * (CARD_Y + GAP_Y)));

        // Add cards to the layeredPane
        for (int i = 0; i < rows * cols; i++) {

            JLabel cardLabel = null;
            int x = (i % cols) * (CARD_X + GAP_X);
            int y = (i / cols) * (CARD_Y + GAP_Y);

            if (client.getBoards().gameBoard[i / rows][i % cols] != null) {
                Card card = client.getBoards().gameBoard[i / rows][i % cols];
                ImageIcon originalIcon = null;
                if (card.getSide()) {
                    originalIcon = new ImageIcon(card.getFrontImage());
                } else {
                    originalIcon = new ImageIcon(card.getBackImage());
                }
                Image image0 = originalIcon.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon0 = new ImageIcon(image0);
                cardLabel = new JLabel(resizedIcon0);
                cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cardLabel.setVerticalAlignment(SwingConstants.CENTER);

                // Set the position and size of the cardLabel
                cardLabel.setBounds(x, y, CARD_X, CARD_Y);
                // Add to the layer related to the turn
                layeredPane.add(cardLabel, (Integer) card.getTurn());

            } else if (client.getBoards().checkBoard[i / rows][i % cols] == 0) {

                cardLabel = new JLabel();
                cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cardLabel.setVerticalAlignment(SwingConstants.CENTER);
                cardLabel.setBounds(x, y, CARD_X, CARD_Y);

                cardLabel.addMouseListener(new cardMouseListener(cardLabel, (i / rows), (i % cols), mainPanel));
                cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cardLabel.setBackground(Color.green);
                cardLabel.setOpaque(true);

                layeredPane.add(cardLabel, JLayeredPane.DEFAULT_LAYER); // Add to the default layer
            }

            // matrix.add(cardLabel);
        }

        // Create a JScrollPane that contains the layeredPane
        scrollPane = new JScrollPane(layeredPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Add the JScrollPane to the BoardPanel
        add(scrollPane, BorderLayout.CENTER);
    }

    public void scrollToMiddle() {
        int maxVertical = scrollPane.getVerticalScrollBar().getMaximum();
        int extentVertical = scrollPane.getVerticalScrollBar().getModel().getExtent();
        int middleVertical = (maxVertical - extentVertical) / 2;
        scrollPane.getVerticalScrollBar().setValue(middleVertical);

        int maxHorizontal = scrollPane.getHorizontalScrollBar().getMaximum();
        int extentHorizontal = scrollPane.getHorizontalScrollBar().getModel().getExtent();
        int middleHorizontal = (maxHorizontal - extentHorizontal) / 2;
        scrollPane.getHorizontalScrollBar().setValue(middleHorizontal);
    }

    private static class cardMouseListener extends MouseAdapter {
        private int x;
        private int y;
        private MainPanel mainPanel;
        private boolean isFront = true;
        private JLabel label;

        public cardMouseListener(JLabel label, int x, int y, MainPanel mainPanel) {
            this.label = label;
            this.x = x;
            this.y = y;
            this.mainPanel = mainPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isFront) {
                if(mainPanel.getyCoord() == -1 && mainPanel.getxCoord() == -1) {
                    mainPanel.setxCoord(x);
                    mainPanel.setyCoord(y);
                    label.setBackground(Color.blue);
                    isFront = !isFront;
                }
            } else {
                mainPanel.setxCoord(-1);
                mainPanel.setyCoord(-1);
                label.setBackground(Color.green);
                isFront = !isFront;
            }
        }
    }
}
