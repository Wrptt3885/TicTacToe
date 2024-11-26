import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private static final int SIZE = 3; // ขนาดของตาราง 3x3
    private static JButton[][] buttons = new JButton[SIZE][SIZE];  // ปุ่ม 3x3 สำหรับตารางเกม
    private static char currentPlayer = 'X';  // ผู้เล่นเริ่มต้น (X)
    private static char[][] board = new char[SIZE][SIZE];  // ตารางเกม 3x3

    public static void main(String[] args) {
        // สร้างหน้าต่าง GUI
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(SIZE, SIZE));

        // สร้างปุ่ม 3x3
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                frame.add(buttons[i][j]);
            }
        }

        // แสดงหน้าต่าง
        frame.setVisible(true);
        initializeBoard();
    }

    // ฟังก์ชันในการเริ่มต้นตารางเกม
    public static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    // ฟังก์ชันตรวจสอบการชนะ
    public static boolean checkWin() {
        // เช็คแถว
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        // เช็คคอลัมน์
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        // เช็คแนวทแยง
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    // ฟังก์ชันเช็คว่าเต็มแล้วหรือยัง
    public static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    return false;  // ยังมีช่องว่าง
                }
            }
        }
        return true;  // ไม่มีช่องว่าง
    }

    // ฟังก์ชันเปลี่ยนผู้เล่น
    public static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    // คลาสสำหรับจัดการการคลิกปุ่ม
    static class ButtonClickListener implements ActionListener {
        int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // ถ้าช่องว่างและเกมยังไม่จบ
            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                buttons[row][col].setText(String.valueOf(currentPlayer));

                // เช็คว่าใครชนะ
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (isBoardFull()) { // เช็คว่าเต็มแล้ว
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    switchPlayer(); // เปลี่ยนผู้เล่น
                }
            }
        }
    }

    // ฟังก์ชันรีเซ็ตเกม
    public static void resetGame() {
        initializeBoard();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("-");
            }
        }
        currentPlayer = 'X';  // ผู้เล่นเริ่มต้น
    }
}
