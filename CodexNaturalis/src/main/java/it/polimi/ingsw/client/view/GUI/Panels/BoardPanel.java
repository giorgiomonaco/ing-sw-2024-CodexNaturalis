package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    private final static int CARD_X = 210;
    private final static int CARD_Y = 140;
    private final static int GAP_X = -30; // Negative gap for overlap in X direction
    private final static int GAP_Y = -30; // Negative gap for overlap in Y direction
    private JScrollPane scrollPane;
    private Client client;
    private List<JLabel> matrix;
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
        matrix = new ArrayList<>();
        layeredPane.setPreferredSize(new Dimension(cols * (CARD_X + GAP_X), rows * (CARD_Y + GAP_Y)));

        // Add cards to the layeredPane
        for (int i = 0; i < rows * cols; i++) {
            JLabel cardLabel = null;
            int x = (i % cols) * (CARD_X + GAP_X);
            int y = (i / cols) * (CARD_Y + GAP_Y);

            if (client.getBoards().gameBoard[i / rows][i % cols] != null) {
                ImageIcon originalIcon = null;
                if (client.getBoards().gameBoard[i / rows][i % cols].getSide()) {
                    originalIcon = new ImageIcon(client.getBoards().gameBoard[i / rows][i % cols].getFrontImage());
                } else {
                    originalIcon = new ImageIcon(client.getBoards().gameBoard[i / rows][i % cols].getBackImage());
                }
                Image image0 = originalIcon.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon0 = new ImageIcon(image0);
                cardLabel = new JLabel(resizedIcon0);
                cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cardLabel.setVerticalAlignment(SwingConstants.CENTER);

                // Set the position and size of the cardLabel
                cardLabel.setBounds(x, y, CARD_X, CARD_Y);
                layeredPane.add(cardLabel, JLayeredPane.MODAL_LAYER); // Add to a higher layer
            } else {
                cardLabel = new JLabel();
                cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cardLabel.setVerticalAlignment(SwingConstants.CENTER);
                cardLabel.setBounds(x, y, CARD_X, CARD_Y);

                if (client.getBoards().checkBoard[i / rows][i % cols] == 0) {
                    cardLabel.addMouseListener(new cardMouseListener(cardLabel, matrix, mainPanel));
                    cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    cardLabel.setBackground(Color.green);
                    cardLabel.setOpaque(true);
                }

                layeredPane.add(cardLabel, JLayeredPane.DEFAULT_LAYER); // Add to the default layer
            }

            matrix.add(cardLabel);
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
        private int index;
        private MainPanel mainPanel;
        private boolean isFront = true;
        private JLabel label;

        public cardMouseListener(JLabel label, List<JLabel> matrix, MainPanel mainPanel) {
            this.label = label;
            this.index = matrix.indexOf(label);
            this.mainPanel = mainPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (isFront) {
                if(mainPanel.getY() == -1 && mainPanel.getX() == -1) {
                    mainPanel.setX(index / 100);
                    mainPanel.setY(index % 100);
                    label.setBackground(Color.blue);
                    isFront = !isFront;
                }
            } else {
                mainPanel.setX(-1);
                mainPanel.setY(-1);
                label.setBackground(Color.green);
                isFront = !isFront;
            }
        }
    }
}
