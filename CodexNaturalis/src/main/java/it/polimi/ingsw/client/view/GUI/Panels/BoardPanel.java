package it.polimi.ingsw.client.view.GUI.Panels;

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
    private JLayeredPane layeredPane;
    int rows = 100;
    int cols = 100;
    private Image backgroundImage;
    private Card[][] gameBoard;

    private MouseAdapter mouseAdapter;


    public BoardPanel(Card[][] gameBoard) {
        this.gameBoard = gameBoard;
        initializeBackgroundImage();
        initializeBoard();
    }


    private void initializeBackgroundImage() {
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("images/backGround.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the board layout and components.
     */
    private void initializeBoard() {
        setLayout(new BorderLayout());

        // Create a JLayeredPane to hold the cards
        layeredPane = new JLayeredPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage,0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        layeredPane.setPreferredSize(new Dimension((int) (cols * (CARD_X + GAP_X)), (int) (rows * (CARD_Y + GAP_Y))));
        layeredPane.setOpaque(true);
        ClassLoader cl = this.getClass().getClassLoader();

        // Add cards to the layeredPane
        for (int i = 0; i < rows * cols; i++) {

            JLabel cardLabel = null;
            int x = (int) ((i % cols) * (CARD_X + GAP_X));
            int y = (int) ((i / cols) * (CARD_Y + GAP_Y));

            if (gameBoard[i % rows][i / cols] != null) {
                Card card = gameBoard[i % rows][i / cols];

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

            }

        }

        // Create a JScrollPane that contains the layeredPane
        scrollPane = new JScrollPane(layeredPane);

        scrollPane.setPreferredSize(new Dimension(700, 550));
        scrollPane.setMinimumSize(new Dimension(700, 550));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add the possibility to drag the panel with mouse -> mouse adapter
        addDragToScrollPane();

        // Add the JScrollPane to the BoardPanel
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addDragToScrollPane(){
        mouseAdapter = new MouseAdapter() {
            private Point lastPoint;
            private boolean dragging = false;
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), layeredPane);
                dragging = false;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), layeredPane);
                JViewport viewport = scrollPane.getViewport();
                Point viewPosition = viewport.getViewPosition();
                viewPosition.translate(lastPoint.x - point.x, lastPoint.y - point.y);
                layeredPane.scrollRectToVisible(new Rectangle(viewPosition, viewport.getSize()));
                lastPoint = point;
                dragging = true;
            }

            public void mouseReleased(MouseEvent e) {
                if (dragging) {
                    // Consume the event if dragging
                    e.consume();
                }
            }
        };


        //now we add the listeners to the scrollpane
        scrollPane.getViewport().addMouseListener(mouseAdapter);
        scrollPane.getViewport().addMouseMotionListener(mouseAdapter);
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

}