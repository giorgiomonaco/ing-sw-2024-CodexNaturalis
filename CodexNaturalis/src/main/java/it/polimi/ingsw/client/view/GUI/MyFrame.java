package it.polimi.ingsw.client.view.GUI;
import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    /*
    layout and constraints ot the layout
    used to place in right place all the elements of the frame
     */
    private GridBagLayout layout;
    private GridBagConstraints gbc;

    //Dimensions of the frame
    private static final int DIM_X = 1000;
    private static final int DIM_Y = 600;
    public MyFrame(GridBagLayout layout){
        this.layout = layout;
        gbc = new GridBagConstraints();
        setSize(new Dimension(DIM_X,DIM_Y));
        setTitle("CODEX NATURALIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}