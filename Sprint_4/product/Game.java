package my_java_sos_project.Sprint_4.product;

import java.awt.Color;
import java.util.*;
import javax.swing.*;

public class Game {

    public Set<String> sosCombinations = new HashSet<String>();

    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static int TOTALCOLUMNS;
    static int TOTALROWS;

    public JButton[][] buttons;
    public String turn;

    public enum GameState {
        PLAYING, DRAW, BLUE_WON, RED_WON
    }

    GameState currentGameState;

    public Game() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the size of the grid");
            System.out.print("Row : ");
            TOTALROWS = sc.nextInt();
            System.out.print("Col : ");
            TOTALCOLUMNS = sc.nextInt();
        }
        buttons = new JButton[TOTALROWS][TOTALCOLUMNS];
        initGame();
    }
    

    public Game(int totalRows, int totalColumns) {
        buttons = new JButton[totalRows][totalColumns];
        initGame();
    }

    public void initGame() {
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                buttons[row][col] = new JButton();
            }
        }
        currentGameState = GameState.PLAYING;
        turn = "Blue";
    }

    public void resetGame() {
        initGame();
    }

    public int getTotalRows() {
        return TOTALROWS;
    }

    public int getTotalColumns() {
        return TOTALCOLUMNS;
    }

    public JButton getCell(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
            return buttons[row][column];
        } else {
            return null;
        }
    }

    public String getTurn() {
        return turn;
    }

    public void makeMove(int row, int column) {
        if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
                && buttons[row][column].getText() == "") {
        	buttons[row][column].setText("S");
            updateGameState(turn, row, column);
            turn = (turn == "Blue") ? "Red" : "Blue";
        }
    }
    
    public void blueMove() {
    	for (int i = 0; i < getTotalRows(); i++) {
    	    for (int j = 0; j <getTotalColumns(); j++) {
                if (buttons[i][j].getText() == "") {
                    if (getTurn() == "Blue") {
                        if (buttons[i][j].getText().equals("")) {
                            buttons[i][j].setForeground(Color.BLUE);
                            buttons[i][j].setText("O");
                            turn = (turn == "Blue") ? "Red" : "Blue";
                        }
                    }
                }
    	    }
    	}
    }
    	                    
    public void redMove() {
    	for (int i = 0; i < getTotalRows(); i++) {
    	    for (int j = 0; j < getTotalColumns(); j++) {
    	         if (buttons[i][j].getText() == "") {
    	        	 if (getTurn() == "Red") {
                        if (buttons[i][j].getText() == "") {
                            buttons[i][j].setForeground(Color.RED);
                            buttons[i][j].setText("S");
                           turn = (turn == "Red") ? "Blue" : "Red";
                        }
    	        	 }
    	         }
    	    }
    	}
    }
    	                    
    	                        
    private void updateGameState(String turn, int row, int column) {
        if (hasWon(buttons)) { // check for win
            currentGameState = (turn == "Blue") ? GameState.BLUE_WON : GameState.RED_WON;
        } else if (isDraw()) {
            currentGameState = GameState.DRAW;
        }
    }

    public int getNumberOfEmptyCells() {
        int numberOfEmptyCells = 0;
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText() == "") {
                    numberOfEmptyCells++;
                }
            }
        }
        return numberOfEmptyCells;
    }
    

    public boolean isDraw() {
        for (int row = 0; row < TOTALROWS; ++row) {
            for (int col = 0; col < TOTALCOLUMNS; ++col) {
                if (buttons[row][col].getText().equals("")) {
                    return false; // an empty cell found, not draw
                }
            }
        }
        return true;
    }

    static boolean search(JButton[][] buttons, int row, int col, String word) {
        if (!buttons[row][col].getText().equals(word.substring(0, 1)))
            return false; 
        int len = word.length();
        for (int dir = 0; dir < 8; dir++) { // Search word in all 8 directions
            int k, rd = row + x[dir], cd = col + y[dir]; // Initialize starting point

            // First character is already checked, match remaining characters
            for (k = 1; k < len; k++) {
                if (rd >= TOTALROWS || rd < 0 || cd >= TOTALCOLUMNS || cd < 0)
                    break; 

                if (!buttons[rd][cd].getText().equals(word.substring(k, k + 1))) 
                    break;

                if (buttons[rd][cd].getText().equals(word.substring(k, k + 1))) {
                }
                rd += x[dir]; 
                cd += y[dir];
            }

            if (k == len) // If all character matched, then value == length of word
                return true;
        }
        return false;
    }

    // Searches SOS
    public boolean hasWon(JButton[][] buttons) {
        for (int row = 0; row < TOTALROWS; row++) {
            for (int col = 0; col < TOTALCOLUMNS; col++) {
                if (search(buttons, row, col, "SOS")) {
                    if (!sosCombinations.contains(row + "" + col)) {
                        sosCombinations.add(row + "" + col);
                        return true;
                    }
                   
                }
            }
        }
        return false;
    }
    

    public GameState getGameState() {
        return currentGameState;
    }

}