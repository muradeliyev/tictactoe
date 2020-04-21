package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToe extends JFrame implements MouseListener {
    boolean isX = true;
    JButton[][] buttons = new JButton[3][3];
    String[][] states = new String[3][3];

    int[][] rows = {
            {0, 0, 0},
            {1, 1, 1},
            {2, 2, 2},
            {0, 1, 2},
            {0, 1, 2},
            {0, 1, 2},
            {0, 1, 2},
            {0, 1, 2}
    };
    int[][] cols = {
            {0, 1, 2},
            {0, 1, 2},
            {0, 1, 2},
            {0, 0, 0},
            {1, 1, 1},
            {2, 2, 2},
            {0, 1, 2},
            {2, 1, 0}
    };

    TicTacToe() {
        super("TicTacToe");

        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        createElements();
        setVisible(true);
    }


    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
    // creating buttons
    private void createElements() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = new JButton("");
                b.setSize(100, 100);
                b.addMouseListener(this);
                b.setFont(new Font("Arial", Font.BOLD, 50));

                add(b);
                buttons[i][j] = b;
            }
        }
    }
    private void restartGame() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                states[i][j] = "";
                buttons[i][j].setText("");
            }
    }
    private boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (states[i][j] == "") return false;

        return true;
    }
    // checking if one of the players has won the game.
    private String theWinner() {
        /*
        *  00 01 02
        *  10 11 12
        *  20 21 22
        */

        for (int i = 0; i < rows.length; i++) {
            int[] row = rows[i];
            int[] col = cols[i];
            if (
                    states[row[0]][col[0]].equals(states[row[1]][col[1]]) &&
                    states[row[1]][col[1]].equals(states[row[2]][col[2]]) &&
                    !states[row[0]][col[0]].equals("")) {
                return states[row[0]][col[0]];
            }
        }
        return "";
    }
    // updating the States[][] array
    private void updateStates() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                states[i][j] = buttons[i][j].getText();
    }

    // overriding MouseListener methods
    @Override
    public void mouseClicked(MouseEvent event) {
        JButton clicked = (JButton) event.getSource();
        if (clicked.getText() == "") {
            if (isX) {
                clicked.setText("X");
                updateStates();
            } else {
                clicked.setText("O");
                updateStates();
            }
            isX = !isX;

            // winner dialog ---------------------------------------------
            String winner = theWinner();

            if (winner != "") {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        String.format("%s is winner\nDo you want to play againg?", winner),
                        "TicTacToe",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    restartGame(); // this restarts the game
                } else if (choice == JOptionPane.NO_OPTION) {
                    dispose(); // this closes the game
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
