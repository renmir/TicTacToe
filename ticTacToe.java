import javax.swing.*;
import java.awt.*;

public class ticTacToe {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tic Tac Toe");
        drawBoard ticTacToeBoard = new drawBoard();
        window.setContentPane(ticTacToeBoard);
        window.setJMenuBar(ticTacToeBoard.menuBar());
        window.setPreferredSize(new Dimension(300,300));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
