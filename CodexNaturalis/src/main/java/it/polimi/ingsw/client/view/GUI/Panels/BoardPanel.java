package it.polimi.ingsw.client.view.GUI.Panels;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.server.model.Card;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class BoardPanel extends JPanel {
    private final static int CARD_X = 150;
    private final static int CARD_Y = 100;
    private final static double GAP_X = -(CARD_X/4.67); // Negative gap for overlap in X direction
    private final static double GAP_Y = -(CARD_Y/2.55); // Negative gap for overlap in Y direction
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
        layeredPane.setPreferredSize(new Dimension((int) (cols * (CARD_X + GAP_X)), (int) (rows * (CARD_Y + GAP_Y))));

        ClassLoader cl = this.getClass().getClassLoader();

        // Add cards to the layeredPane
        for (int i = 0; i < rows * cols; i++) {

            JLabel cardLabel = null;
            int x = (int) ((i % cols) * (CARD_X + GAP_X));
            int y = (int) ((i / cols) * (CARD_Y + GAP_Y));

            if (client.getBoards().gameBoard[i % rows][i / cols] != null) {
                Card card = client.getBoards().gameBoard[i % rows][i / cols];

                String pathF = card.getFrontImage();
                String pathB = card.getBackImage();

                InputStream is = null;
                if (card.getSide()) {
                    is = cl.getResourceAsStream(pathF);
                } else {
                    is = cl.getResourceAsStream(pathB);
                }

                ImageIcon originalIcon = null;
                try {
                    originalIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(is, "Couldn't read the image.")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
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

            } else if (client.getBoards().checkBoard[i % rows][i / cols] == 0) {

                cardLabel = new JLabel();
                cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cardLabel.setVerticalAlignment(SwingConstants.CENTER);
                cardLabel.setBounds(x, y, CARD_X, CARD_Y);

                cardLabel.addMouseListener(new cardMouseListener(cardLabel, (i % rows), (i / cols), mainPanel));
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
                    System.out.println(x + " " + y);
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