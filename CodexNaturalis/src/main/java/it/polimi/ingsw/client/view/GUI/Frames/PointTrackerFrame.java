package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.view.GUI.Panels.LegendPanel;
import it.polimi.ingsw.client.view.GUI.Panels.PointTrackerPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PointTrackerFrame extends JFrame {
    /*
    This class manages the displacement of the point tracker,
    and it places the tokens on top of the right cell
     */

    //We define dimensions of tokens
    private final static int token_X = 25;
    private final static int token_Y = 25;

    //dimensions of frame
    private final static int frame_X = 250;
    private final static  int frame_Y = 570;

    //height of legend panel
    private final static int legend_Y = 50;


    //We need to store the coordinates on the image of every box
    private Client client;

    //panel to displace the point tracker
    private PointTrackerPanel backgroundPanel;

    //Panel to displace relation between player name and token chosen
    private JPanel legend;

    //Array of images to store the tokens so not retrieve them every time
    private List <JLabel> tokenList = new ArrayList<>();
    private Image backgroundImage;


    //Constructor
    public PointTrackerFrame(Client c){

        this.client = c;
        setTitle("POINTS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(frame_X, frame_Y));
        setLayout(new BorderLayout());


        backgroundPanel = new PointTrackerPanel(client);
        add(backgroundPanel, BorderLayout.CENTER);

        legend = new LegendPanel(client);
        add(legend,BorderLayout.NORTH);
        setVisible(true);

    }
}