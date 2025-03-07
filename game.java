// Programming I, Project 2
// Goran Delic
// 89201217


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main extends JFrame {
    private int[][] field;
    private int targetValue;
    private int moveCounter;
    private int previous;
    private int current;

    private JButton[][] buttons;
    private JLabel targetLabel;
    private JLabel currentSumLabel;
    private JLabel movesLeftLabel;

    public Main() {
        setTitle("More or Less, Less is More Game by Goran Delic");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLookAndFeel();

        initializeGame();

        JPanel buttonPanel = new JPanel(new GridLayout(field.length, field[0].length));
        buttons = new JButton[field.length][field[0].length];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                buttons[i][j] = new JButton(Integer.toString(field[i][j]));
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(new Color(76, 166, 25)); // Light gray background
                buttons[i][j].setForeground(Color.BLACK);
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> buttonClicked(finalI, finalJ));
                buttonPanel.add(buttons[i][j]);
            }
        }

        targetLabel = new JLabel("Target Value: " + targetValue);
        currentSumLabel = new JLabel("Current Sum: 0");
        movesLeftLabel = new JLabel("Moves Left: " + moveCounter);

        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.add(targetLabel);
        infoPanel.add(currentSumLabel);
        infoPanel.add(movesLeftLabel);

        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        saveMenuItem.addActionListener(e -> saveGameToFile());
        loadMenuItem.addActionListener(e -> loadGameFromFile());
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);

        // Game control menu
        JMenu gameControlMenu = new JMenu("Game Control");

        JMenuItem restartMenuItem = new JMenuItem("Restart");
        JMenuItem setFieldSizeMenuItem = new JMenuItem("Set Field Size");
        JMenuItem setNumMovesMenuItem = new JMenuItem("Set Number of Moves");
        JMenuItem setTargetValueMenuItem = new JMenuItem("Set Target Value");

        restartMenuItem.addActionListener(e -> restartGame());
        setFieldSizeMenuItem.addActionListener(e -> setFieldSize());
        setNumMovesMenuItem.addActionListener(e -> setNumberOfMoves());
        setTargetValueMenuItem.addActionListener(e -> setTargetValue());

        gameControlMenu.add(restartMenuItem);
        gameControlMenu.add(setFieldSizeMenuItem);
        gameControlMenu.add(setNumMovesMenuItem);
        gameControlMenu.add(setTargetValueMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(gameControlMenu);

        setJMenuBar(menuBar);

        add(buttonPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    private void restartGame() {
        initializeGame();
        resetUI();
    }

    private void setFieldSize() {
        String input = JOptionPane.showInputDialog(this, "Enter the new field size (e.g., 5 for a 5x5 grid):");
        try {
            int newSize = Integer.parseInt(input);
            if (newSize > 0) {
                field = initializeField(newSize);
                targetValue = generateTargetValue();
                moveCounter = newSize * newSize;
                resetUI();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a positive integer for the field size.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer for the field size.");
        }
    }

    private void setNumberOfMoves() {
        String input = JOptionPane.showInputDialog(this, "Enter the new number of moves:");
        try {
            int newMoves = Integer.parseInt(input);
            if (newMoves > 0) {
                moveCounter = newMoves;
                resetUI();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a positive integer for the number of moves.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer for the number of moves.");
        }
    }

    private void setTargetValue() {
        String input = JOptionPane.showInputDialog(this, "Enter the new target value:");
        try {
            int newTarget = Integer.parseInt(input);
            if (newTarget > 0) {
                targetValue = newTarget;
                resetUI();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a positive integer for the target value.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer for the target value.");
        }
    }

    private void initializeGame() {
        int n = 10; // Field size
        field = initializeField(n);
        targetValue = generateTargetValue();
        moveCounter = n * n;
        previous = -1;
        current = field[0][0];
    }

    private int[][] initializeField(int n) {
        int[][] field = new int[n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = random.nextInt(9) + 1;
            }
        }

        return field;
    }

    private int generateTargetValue() {
        Random random = new Random();
        return random.nextInt(9 * 9) + 1; // Maximum possible sum
    }

    private void buttonClicked(int rowIndex, int colIndex) {
        int chosenDigit = field[rowIndex][colIndex];

        if (isValidDigit(chosenDigit)) {
            int[] result = chooseCandidates(rowToColArray(field, colIndex), previous, current, chosenDigit);
            if (result != null) {
                previous = current;
                current = result[0];

                updateField(rowIndex, colIndex, current);

                int currentSum = calculateSum(field);
                moveCounter--;

                targetLabel.setText("Target Value: " + targetValue);
                currentSumLabel.setText("Current Sum: " + currentSum);
                movesLeftLabel.setText("Moves Left: " + moveCounter);

                buttons[rowIndex][colIndex].setEnabled(false);

                if (currentSum == targetValue) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You reached the target value.");
                    initializeGame();
                    resetUI();
                } else if (moveCounter == 0) {
                    JOptionPane.showMessageDialog(this, "Game over. You ran out of moves.");
                    initializeGame();
                    resetUI();
                } else if (!hasValidMoves()) {
                    JOptionPane.showMessageDialog(this, "Game over. No more valid moves left :(", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    initializeGame();
                    resetUI();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid choice. Please choose a valid button.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid digit. Please choose a digit between 1 and 9.");
        }
    }

    private boolean hasValidMoves() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                int chosenDigit = field[i][j];
                if (isValidDigit(chosenDigit) && chooseCandidates(rowToColArray(field, j), previous, current, chosenDigit) != null) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isValidDigit(int digit) {
        return digit >= 1 && digit <= 9;
    }

    private int[] chooseCandidates(int[] column, int previous, int current, int chosenDigit) {
        for (int i = Math.min(previous, current); i <= Math.max(previous, current); i++) {
            if (i % chosenDigit == 0) {
                for (int j = 0; j < column.length; j++) {
                    if (column[j] != 0) {
                        return new int[]{column[j], j};
                    }
                }
            }
        }
        return null;
    }

    private void updateField(int rowIndex, int colIndex, int current) {
        field[rowIndex][colIndex] = 0; // Set the selected button as inactive
    }

    private int calculateSum(int[][] field) {
        int sum = 0;
        for (int[] row : field) {
            for (int value : row) {
                sum += value;
            }
        }
        return sum;
    }

    private int[] rowToColArray(int[][] field, int colIndex) {
        int[] column = new int[field.length];
        for (int i = 0; i < field.length; i++) {
            column[i] = field[i][colIndex];
        }
        return column;
    }

    private void resetUI() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
        targetLabel.setText("Target Value: " + targetValue);
        currentSumLabel.setText("Current Sum: 0");
        movesLeftLabel.setText("Moves Left: " + moveCounter);
    }

    private void saveGameToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile())) {
                writer.println(targetValue);
                writer.println(moveCounter);
                writer.println(previous);
                writer.println(current);

                for (int[] row : field) {
                    for (int value : row) {
                        writer.print(value + " ");
                    }
                    writer.println();
                }

                JOptionPane.showMessageDialog(this, "Game saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving the game: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadGameFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try (Scanner scanner = new Scanner(fileChooser.getSelectedFile())) {
                targetValue = scanner.nextInt();
                moveCounter = scanner.nextInt();
                previous = scanner.nextInt();
                current = scanner.nextInt();

                field = new int[10][10];
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        field[i][j] = scanner.nextInt();
                    }
                }

                JOptionPane.showMessageDialog(this, "Game loaded successfully!");
                resetUI();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
