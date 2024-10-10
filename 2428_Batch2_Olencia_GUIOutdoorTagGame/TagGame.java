
/*
 * Author: Olencia Fernandes
 * Roll no: 2428
 * Topic: GUI Outdoor Game
 * Start Date: 16 September 2024
 * Modified Date: 30 September 2024
 * Description: To implement GUI based Outdoor Game applying OOP concepts using Java
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TagGame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 50;
    private static final int TAGGER_SPEED = 10;
    private static final int TARGET_SPEED = 5;
    private static final int GAME_TIME = 30; // 30 seconds

    private StickFigure tagger;
    private StickFigure target;
    private JLabel scoreLabel;
    private JLabel timerLabel;
    private Timer gameTimer;
    private Timer targetMovementTimer;
    private Random random = new Random();
    private int score = 0;

    private int timeRemaining = GAME_TIME;
    private boolean up, down, left, right;

    public TagGame() {
        super("Game of Tag - Stick Figures");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize GUI components
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);
                tagger.draw(g);
                target.draw(g);
            }
        };
        panel.setLayout(null);

        // Initialize tagger and target
        tagger = new StickFigure(50, 50, Color.RED);
        target = new StickFigure(600, 400, Color.BLUE);

        // Score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 100, 30);
        panel.add(scoreLabel);

        // Timer label
        timerLabel = new JLabel("Time: " + GAME_TIME);
        timerLabel.setBounds(700, 10, 100, 30);
        panel.add(timerLabel);

        // Add panel to frame
        add(panel);

        // Key listener for movement
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), false);
            }
        });

        // Timer for target movement
        targetMovementTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveTagger();
                moveTarget();
                if (checkCollision()) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                    resetTargetPosition();
                }
                repaint(); // Repaint the panel to reflect new positions
            }
        });
        targetMovementTimer.start();

        // Game timer
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time: " + timeRemaining);
                if (timeRemaining <= 0) {
                    gameOver();
                }
            }
        });
        gameTimer.start();

        // Display the frame
        setVisible(true);
    }

    // Handle key presses for continuous movement
    private void handleKeyPress(int keyCode, boolean pressed) {
        switch (keyCode) {
            case KeyEvent.VK_UP -> up = pressed;
            case KeyEvent.VK_DOWN -> down = pressed;
            case KeyEvent.VK_LEFT -> left = pressed;
            case KeyEvent.VK_RIGHT -> right = pressed;
        }
    }

    // Move the tagger (controlled by player)
    private void moveTagger() {
        int x = tagger.getX();
        int y = tagger.getY();

        if (up)
            y = Math.max(y - TAGGER_SPEED, 0);
        if (down)
            y = Math.min(y + TAGGER_SPEED, HEIGHT - PLAYER_SIZE);
        if (left)
            x = Math.max(x - TAGGER_SPEED, 0);
        if (right)
            x = Math.min(x + TAGGER_SPEED, WIDTH - PLAYER_SIZE);

        tagger.setPosition(x, y);
    }

    // Move the target (randomly, but flee if too close to the tagger)
    private void moveTarget() {
        int taggerX = tagger.getX();
        int taggerY = tagger.getY();
        int targetX = target.getX();
        int targetY = target.getY();

        // If tagger is too close, move away
        if (Math.abs(taggerX - targetX) < 100 && Math.abs(taggerY - targetY) < 100) {
            if (taggerX < targetX)
                targetX += TARGET_SPEED;
            if (taggerX > targetX)
                targetX -= TARGET_SPEED;
            if (taggerY < targetY)
                targetY += TARGET_SPEED;
            if (taggerY > targetY)
                targetY -= TARGET_SPEED;
        } else {
            // Move randomly
            targetX += random.nextInt(11) - 5; // Move target within [-5, 5] range
            targetY += random.nextInt(11) - 5;
        }

        // Ensure target stays within bounds
        targetX = Math.max(0, Math.min(targetX, WIDTH - PLAYER_SIZE));
        targetY = Math.max(0, Math.min(targetY, HEIGHT - PLAYER_SIZE));

        target.setPosition(targetX, targetY);
    }

    // Check if the tagger has collided with the target
    private boolean checkCollision() {
        Rectangle taggerBounds = tagger.getBounds();
        Rectangle targetBounds = target.getBounds();
        return taggerBounds.intersects(targetBounds);
    }

    // Reset the target to a random position after being tagged
    private void resetTargetPosition() {
        int x = random.nextInt(WIDTH - PLAYER_SIZE);
        int y = random.nextInt(HEIGHT - PLAYER_SIZE);
        target.setPosition(x, y);
    }

    // End the game when time is up
    private void gameOver() {
        gameTimer.stop();
        targetMovementTimer.stop();
        JOptionPane.showMessageDialog(this, "Game Over! Final Score: " + score);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TagGame::new);
    }

    // Stick Figure class
    class StickFigure {
        private int x, y;
        private Color color;

        public StickFigure(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public void draw(Graphics g) {
            g.setColor(color);

            // Head (circle)
            g.drawOval(x + 15, y, 20, 20);

            // Body (line)
            g.drawLine(x + 25, y + 20, x + 25, y + 45);

            // Arms (lines)
            g.drawLine(x + 10, y + 30, x + 40, y + 30);

            // Legs (lines)
            g.drawLine(x + 25, y + 45, x + 10, y + 60);
            g.drawLine(x + 25, y + 45, x + 40, y + 60);
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, PLAYER_SIZE, PLAYER_SIZE);
        }
    }
}