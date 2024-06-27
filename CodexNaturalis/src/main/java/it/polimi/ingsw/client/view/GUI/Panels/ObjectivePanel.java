package it.polimi.ingsw.client.view.GUI.Panels;

import it.polimi.ingsw.client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ObjectivePanel extends JPanel {
    private final static int CARD_X = 150;
    private final static int CARD_Y = 100;
    /*
    In this panel we want to show the objective cards the player has
     */
    private Client client;
    private GridBagConstraints gbc;
    public ObjectivePanel(Client c){
        this.client = c;
        //setting layout to gbl
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(350, 300));
        setMinimumSize(new Dimension(350, 300));
        gbc = new GridBagConstraints();
        //create and displace the cards
        displaceObjectiveCards();

    }

    private void displaceObjectiveCards(){
        /*
        We create the icons
        We extract image
        We resize it
        We recreate the icons
        we add icons o labels
         */

        ClassLoader cl = this.getClass().getClassLoader();
        String path0 = client.getObjective().getImage();
        String path1 = client.getCommonObjectives().get(0).getImage();
        String path2 = client.getCommonObjectives().get(1).getImage();
        InputStream is0 = cl.getResourceAsStream(path0);
        InputStream is1 = cl.getResourceAsStream(path1);
        InputStream is2 = cl.getResourceAsStream(path2);


        //we retrieve the images of the cards
        ImageIcon personalObj = null;
        ImageIcon firstGlobalObj = null;
        ImageIcon secondGlobalObj = null;
        try {
            personalObj = new ImageIcon(ImageIO.read(Objects.requireNonNull(is0, "Couldn't read the image.")));
            firstGlobalObj = new ImageIcon(ImageIO.read(Objects.requireNonNull(is1, "Couldn't read the image.")));
            secondGlobalObj = new ImageIcon(ImageIO.read(Objects.requireNonNull(is2, "Couldn't read the image.")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Image resizedPersonal = personalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image resizedFirstCommon = firstGlobalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);
        Image resizedSecondCommon = secondGlobalObj.getImage().getScaledInstance(CARD_X, CARD_Y, Image.SCALE_SMOOTH);


        ImageIcon officialObj = new ImageIcon(resizedPersonal);
        ImageIcon officialFirstGlobal = new ImageIcon(resizedFirstCommon);
        ImageIcon officialSecondGlobal = new ImageIcon(resizedSecondCommon);


        JLabel firsCommonObjCard = new JLabel(officialFirstGlobal);
        JLabel secondCommonObjCard = new JLabel(officialSecondGlobal);
        JLabel personalCard = new JLabel(officialObj);

        //now we place the cards into the panel
        //first common parameters
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        //then for each

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(firsCommonObjCard,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(secondCommonObjCard,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(personalCard,gbc);
    }
}