package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class EndGamePanel extends JPanel {

    private String winner;

    private Image backgroundImage;
    private JPanel winnerAndTrackerPanel;
    private PointTrackerPanel pointTrackerPanel;
    private JLabel winnerLabel;

    private Client client;

    public EndGamePanel(Client client) {


        this.client = client;
        //set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //we set the dimension of the panel like the frame
        setSize(new Dimension(1100, 650));
        setOpaque(false);
        //paint the panel
        paintPanel();

        JLabel title = new JLabel("END GAME", SwingConstants.CENTER);
        title.setFont(new Font("San Francisco", Font.BOLD, 54));
        title.setForeground(Color.BLACK);

        //adding the text in "NORTH" position
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(title, gbc);

        winner = client.getWinnerName();

        // We create a panel for the winner and the track-board
        winnerAndTrackerPanel = new JPanel(new BorderLayout());
        winnerAndTrackerPanel.setPreferredSize(new Dimension(480, 480));
        winnerAndTrackerPanel.setMaximumSize(new Dimension(480, 480));
        winnerAndTrackerPanel.setOpaque(false);

        System.out.println("the winner is: " + winner);
        JLabel text = new JLabel("<html><div style='text-align: center;'>THE WINNER IS:<br>" + winner + "<div></html>", SwingConstants.CENTER);
        text.setFont(new Font("San Francisco", Font.BOLD, 25));
        text.setForeground(Color.BLACK);
        text.setHorizontalAlignment(SwingConstants.CENTER);
       // text.setBorder(BorderFactory.createLineBorder(Color.blue)); //maybe can be changed = border of the winner panel

        // we add the text to the winner and tracker panel
        winnerAndTrackerPanel.add(text, BorderLayout.WEST);
        //we instance the point tracker panel
        pointTrackerPanel = new PointTrackerPanel(client);
        pointTrackerPanel.setPreferredSize(new Dimension(230,480));
        //we add the point tracker panel to the displacer
        winnerAndTrackerPanel.add(pointTrackerPanel, BorderLayout.EAST);

        try {
            ClassLoader cl1 = this.getClass().getClassLoader();
            InputStream is = cl1.getResourceAsStream("images/backGround2.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //finally we add the panel with the name of the winner and the point tracker
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(winnerAndTrackerPanel,gbc);
        setVisible(true);


    }
    private void paintPanel(){
        try {
            ClassLoader cl1 = this.getClass().getClassLoader();
            InputStream is = cl1.getResourceAsStream("images/backGround2.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    private void paintPanel(){
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("images/backGround2.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                System.err.println("Background image not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

 */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
