import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class drawBoard extends JPanel {
    private JButton[] buttons = new JButton[9];
    private String playerSymbol1;
    private String playerSymbol2;
    private String winner;
    private JMenuBar topBar;
    private boolean p2Turn;

    drawBoard() {
        choosePlayerSymbol();
        setLayout(new GridLayout(3, 3));
        drawButtons();
    }

    public void choosePlayerSymbol() {
        playerSymbol1 = "X";
        playerSymbol2 = "O";
    }

    public void drawButtons() {
        class button implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                JButton area = (JButton) evt.getSource();
                if (!p2Turn) {
                    area.setText(String.valueOf(playerSymbol1));
                    area.setForeground(Color.orange);
                    p2Turn = true;
                } else if (p2Turn) {
                    area.setText(String.valueOf(playerSymbol2));
                    area.setForeground(Color.CYAN);
                    p2Turn = false;
                }
                checkVictor();
            }
        }
        for (int i = 0; i < buttons.length; i++){
            buttons[i] = new JButton();
            buttons[i].setText(" ");
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 50));
            buttons[i].setBackground(new Color(238, 238, 238));
            buttons[i].addActionListener(new button());
            add(buttons[i]);
        }
    }

    public void checkVictor() {
        if(winner()) {
            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. \n" + winner + " player wins. \n"
                    + "Would you like to play again?","Game over.", JOptionPane.YES_NO_OPTION);

            if(dialogResult == JOptionPane.YES_OPTION) resetButtons();
            else System.exit(0);
        }
        else if(draw()) {
            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. \nPlay again?","Game over.",
                    JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION)  resetButtons();
            else System.exit(0);
        }
    }

    public boolean winner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    public boolean draw() {
        boolean fullBoard = true;
        for (JButton button : buttons) {
            if (button.getText().isBlank()) {
                fullBoard = false;
            }
        }
        return fullBoard;
    }

    private void resetButtons() {
        choosePlayerSymbol();
        for (JButton button : buttons) {
            button.setText(" ");
        }
        checkRows();
        checkColumns();
        checkDiagonals();
    }

    public boolean checkRows() {
        int i = 0;
        for(int j = 0; j < 3; j++) {
            if( buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText())
                    && !buttons[i].getText().isBlank()) {
                winner = buttons[i].getText();
                return true;
            }
            i = i+3;
        }
        return false;
    }

    public boolean checkColumns() {
        int i = 0;
        for(int j = 0; j < 3; j++) {
            if( buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText())
                    && !buttons[i].getText().isBlank()) {
                winner = buttons[i].getText();
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean checkDiagonals() {
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
                && !buttons[0].getText().isBlank()) {
            winner = buttons[0].getText();
            return true;
        } else if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
                    && !buttons[2].getText().isBlank()) {
            winner = buttons[2].getText();
            return true;
        } else return false;
    }

    public JMenuBar menuBar() {
        class option implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                switch (evt.getActionCommand()) {
                    case "Reset Game" -> {
                        resetButtons();
                    }
                }
            }
        }
        if (topBar == null) {
            topBar = new JMenuBar();

            JMenuItem resetGame = new JMenuItem("Reset Game");
            resetGame.addActionListener(new option());
            topBar.add(resetGame);
        }
        return topBar;
    }

}
