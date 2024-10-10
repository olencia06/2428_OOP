package Minesweeper;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {

    private final Date startDate = new Date();
    private final int spacing = 2;
    private final int smileyX = 605;
    private final int smileyY = 5;
    private final int smileyCenterX = smileyX + 35;
    private final int smileyCenterY = smileyY + 35;
    private final int timeX = 1130;
    private final int timeY = 5;
    private int sec = 0;
    private int mx = -100;
    private int my = -100;
    private boolean happiness = true;
    private boolean victory = false;
    private boolean defeat = false;
    private final Random rand = new Random();
    private final int[][] mines = new int[16][8];
    private final int[][] neighbours = new int[16][8];
    private final boolean[][] revealed = new boolean[16][8];
    private final boolean[][] flagged = new boolean[16][8];
    private boolean timerRunning = true;

    public GUI() {
        setTitle("Minesweeper");
        setSize(1286, 829);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);

        initializeBoard();
        calculateNeighbors();

        Board board = new Board();
        setContentPane(board);

        Move move = new Move();
        addMouseMotionListener(move);

        Click click = new Click();
        addMouseListener(click);
    }

    private void initializeBoard() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 8; j++) {
                mines[i][j] = rand.nextInt(100) < 10 ? 1 : 0; // 10% chance of placing a mine
                revealed[i][j] = false; // Set all cells to unrevealed
                flagged[i][j] = false; // No cells are flagged initially
            }
        }
    }

    private void calculateNeighbors() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 8; j++) {
                int count = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    for (int n = j - 1; n <= j + 1; n++) {
                        if (m >= 0 && m < 16 && n >= 0 && n < 8 && !(m == i && n == j) && mines[m][n] == 1) {
                            count++;
                        }
                    }
                }
                neighbours[i][j] = count; // Store the number of neighboring mines
            }
        }
    }

    private void revealCells(int x, int y) {
        // Check if the cell is out of bounds, already revealed, or flagged
        if (x < 0 || x >= 16 || y < 0 || y >= 8 || revealed[x][y] || flagged[x][y]) {
            return;
        }

        // Reveal the current cell
        revealed[x][y] = true;

        // Stop revealing if the cell contains a mine
        if (mines[x][y] == 1) {
            return;
        }

        // If the cell has no adjacent mines, recursively reveal its neighbors
        if (neighbours[x][y] == 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    // Make sure not to call revealCells on the current cell or out-of-bounds cells
                    if (i != x || j != y) {
                        revealCells(i, j);
                    }
                }
            }
        }
    }

    public class Board extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBackground(g);
            drawCells(g);
            drawSmiley(g);
            drawTimeCounter(g);
        }

        private void drawBackground(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 1280, 800);
        }

        private void drawCells(Graphics g) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 8; j++) {
                    g.setColor(Color.GRAY);
                    if (revealed[i][j] || defeat) { // Reveal all cells if the game is over
                        if (mines[i][j] == 1) {
                            g.setColor(Color.RED); // Mines are shown in red
                        } else {
                            g.setColor(Color.WHITE); // Revealed cells are white
                        }
                    } else if (flagged[i][j]) {
                        g.setColor(Color.YELLOW); // Color for flagged cells
                    }

                    // Highlight cell on hover
                    if (mx >= spacing + i * 80 && mx < i * 80 + 80 - spacing
                            && my >= spacing + j * 80 + 106 && my < spacing + j * 80 + 186 - spacing) {
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);

                    // Draw mine count or mine
                    if (revealed[i][j] || defeat) { // Show all mines if the game is over
                        g.setColor(Color.BLACK);
                        if (mines[i][j] == 0 && neighbours[i][j] != 0) {
                            g.setColor(getColorForMineCount(neighbours[i][j]));
                            g.setFont(new Font("Montserrat", Font.CENTER_BASELINE, 30));
                            g.drawString(Integer.toString(neighbours[i][j]), i * 80 + 30, j * 80 + 133);
                        } else if (mines[i][j] == 1) {
                            if (flagged[i][j]) {
                                drawFlaggedMine(g, i, j); // Draw an "X" for flagged mines
                            } else {
                                drawMine(g, i, j); // Draw the mine
                            }
                        }
                    } else if (flagged[i][j]) {
                        drawFlag(g, i, j);
                    }
                }
            }
        }

        private void drawFlaggedMine(Graphics g, int i, int j) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Montserrat", Font.BOLD, 30));
            g.drawString("X", i * 80 + 30, j * 80 + 120); // Draw an "X" in the center of the cell
        }

        private void drawFlag(Graphics g, int i, int j) {
            g.setColor(Color.BLACK);
            g.fillRect(i * 80 + 30, j * 80 + 100, 20, 40); // Flagpole
            g.setColor(Color.RED);
            g.fillRect(i * 80 + 15, j * 80 + 100, 30, 20); // Flag
        }

        private Color getColorForMineCount(int count) {
            switch (count) {
                case 1:
                    return Color.BLUE;
                case 2:
                    return Color.GREEN;
                case 3:
                    return Color.RED;
                case 4:
                    return new Color(0, 0, 128);
                case 5:
                    return new Color(178, 34, 34);
                case 6:
                    return new Color(72, 209, 204);
                case 7:
                    return Color.BLACK;
                default:
                    return Color.DARK_GRAY;
            }
        }

        private void drawMine(Graphics g, int i, int j) {
            g.fillRect(i * 80 + 30, j * 80 + 100, 20, 40);
            g.fillRect(i * 80 + 20, j * 80 + 110, 40, 20);
            g.fillRect(i * 80 + 25, j * 80 + 105, 30, 30);
            g.fillRect(i * 80 + 38, j * 80 + 95, 4, 50);
            g.fillRect(i * 80 + 15, j * 80 + 118, 50, 4);
        }

        private void drawSmiley(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillOval(smileyX, smileyY, 70, 70); // Draw the face
            g.setColor(Color.BLACK);
            g.fillOval(smileyX + 15, smileyY + 20, 10, 10); // Left eye
            g.fillOval(smileyX + 45, smileyY + 20, 10, 10); // Right eye

            if (happiness) {
                // Draw a smiling mouth using an arc
                g.drawArc(smileyX + 15, smileyY + 30, 40, 20, 0, -180); // Smile arc
            } else if (defeat) {
                // Draw a sad face when the game is lost
                g.drawArc(smileyX + 15, smileyY + 40, 40, 20, 0, 180); // Frown arc
            }
        }

        private void drawTimeCounter(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(timeX, timeY, 140, 70);

            if (timerRunning) {
                sec = (int) ((new Date().getTime() - startDate.getTime()) / 1000);
                if (sec > 999) {
                    sec = 999;
                }
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Montserrat", Font.PLAIN, 73));
            String timeString = String.format("%03d", sec); // Ensures leading zeros
            g.drawString(timeString, timeX, timeY + 65);
        }

    }

    public class Move implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            // Not used, but required by the interface
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
        }
    }

    public class Click implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = inBoxX();
            int y = inBoxY();

            if (e.getButton() == MouseEvent.BUTTON1) { // Left click
                if (!defeat && !victory && x != -1 && y != -1 && !flagged[x][y]) {
                    if (mines[x][y] == 0) {
                        revealCells(x, y); // Reveal all connected empty cells
                    } else {
                        revealed[x][y] = true;
                        if (mines[x][y] == 1) { // Check if the clicked cell contains a mine
                            defeat = true; // Player clicked on a mine, game over
                            happiness = false; // Change the smiley face to a sad expression
                            timerRunning = false; // Stop the timer
                            repaint(); // Repaint to update the GUI and show all mines

                            // Display Game Over message with an option to reset the game
                            int option = JOptionPane.showConfirmDialog(null,
                                    "Game Over! You hit a mine. Do you want to reset the game?",
                                    "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                            if (option == JOptionPane.YES_OPTION) {
                                resetGame(); // Reset the game if the player chooses "Yes"
                            }
                        } else {
                            checkVictoryStatus(); // Check if the player has won
                            repaint(); // Repaint to update the GUI
                        }
                    }
                    System.out.println("The mouse is in the [" + x + "," + y + "], number of mine neighbours: "
                            + neighbours[x][y]);
                } else {
                    System.out.println("The mouse is not in the grid, the cell is flagged, or the game is over.");
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) { // Right click
                if (!defeat && !victory && x != -1 && y != -1 && !revealed[x][y]) {
                    flagged[x][y] = !flagged[x][y]; // Toggle flag on right click
                    repaint(); // Repaint to update the GUI
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (Math.sqrt(Math.pow(smileyCenterX - e.getX(), 2) + Math.pow(smileyCenterY - e.getY(), 2)) < 35) {
                resetGame(); // Reset the game when smiley face is clicked
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Not used, but required by the interface
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Not used, but required by the interface
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Not used, but required by the interface
        }

        private int inBoxX() {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 8; j++) {
                    if (mx >= spacing + i * 80 && mx < i * 80 + 80 - spacing
                            && my >= spacing + j * 80 + 106 && my < spacing + j * 80 + 186 - spacing) {
                        return i;
                    }
                }
            }
            return -1;
        }

        private int inBoxY() {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 8; j++) {
                    if (mx >= spacing + i * 80 && mx < i * 80 + 80 - spacing
                            && my >= spacing + j * 80 + 106 && my < spacing + j * 80 + 186 - spacing) {
                        return j;
                    }
                }
            }
            return -1;
        }
    }

    private void resetGame() {
        happiness = true;
        victory = false;
        defeat = false;
        timerRunning = true; // Restart the timer
        startDate.setTime(new Date().getTime()); // Reset start time
        initializeBoard();
        calculateNeighbors();
        repaint(); // Repaint to update the GUI
    }

    public void checkVictoryStatus() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 8; j++) {
                if (mines[i][j] != 0 && !revealed[i][j]) {
                    return; // Game continues if there are any unrevealed non-mine cells
                }
            }
        }
        victory = true; // All non-mine cells revealed
        happiness = true; // Display happy smiley

        if (victory == true) {
            JOptionPane.showMessageDialog(null, "Congratulations, you won!");
        }
    }
}
