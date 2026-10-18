package com.example.final_tic_tac_toe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private TextView statusTextView;

    private String[] board = new String[9];
    private String currentPlayer = "X";
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = findViewById(R.id.statusTextView);

        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8
        };

        for (int i = 0; i < 9; i++) {
            board[i] = "";
            buttons[i] = findViewById(buttonIds[i]);

            final int index = i;

            buttons[i].setOnClickListener(v -> playMove(index));
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void playMove(int index) {

        if (!board[index].isEmpty() || gameOver) {
            return;
        }

        board[index] = currentPlayer;
        buttons[index].setText(currentPlayer);

        if (checkWin()) {
            statusTextView.setText("Player " + currentPlayer + " wins!");
            gameOver = true;
        } else if (isDraw()) {
            statusTextView.setText("It's a Draw!");
            gameOver = true;
        } else {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            statusTextView.setText("Player " + currentPlayer + "'s Turn");
        }
    }

    private boolean checkWin() {

        int[][] patterns = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] p : patterns) {
            if (board[p[0]].equals(currentPlayer)
                    && board[p[1]].equals(currentPlayer)
                    && board[p[2]].equals(currentPlayer)) {
                return true;
            }
        }

        return false;
    }

    private boolean isDraw() {

        for (int i = 0; i < 9; i++) {
            if (board[i].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private void resetGame() {

        for (int i = 0; i < 9; i++) {
            board[i] = "";
            buttons[i].setText("");
        }

        currentPlayer = "X";
        gameOver = false;
        statusTextView.setText("Player X's Turn");
    }
}
