package it.polimi.ingsw.client.view.GUI.Frames;

import it.polimi.ingsw.client.view.GUI.Panels.BoardPanel;
import it.polimi.ingsw.server.model.Card;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {

    private Card[][] gameBoard;
    private BoardPanel board;
    private static final int DIM_X = 650;
    private static final int DIM_Y = 450;

    public BoardFrame(Card[][] gameBoard, String name){

        this.gameBoard = gameBoard;
        setSize(new Dimension(DIM_X,DIM_Y));
        setTitle(name.toUpperCase() + "'S BOARD");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initializePanel();

    }

    private void initializePanel() {

        board = new BoardPanel(gameBoard);

        add(board, BorderLayout.CENTER);
        board.scrollToMiddle();

    }

    public BoardPanel getBoard() {
        return board;
    }
}
