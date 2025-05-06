package jv.model;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener {
    private static final int TILE_SIZE = 20;
    private static final int GAME_SPEED = 300;

    private GameBoard gameBoard;
    private Snake snake;
    private Apple apple;
    private Timer gameTimer;
    private int score = 0;
    private boolean gameOver = false;
    private int currentSpeed = GAME_SPEED;

    /**
     * Création d'une nouvelle partie
     */
    public Game() {
        this.gameBoard = new GameBoard(400, 400);
        this.snake = new Snake("RIGHT", new ArrayList<>());
        this.apple = new Apple(new Point(0, 0), gameBoard, snake);

        gameTimer = new Timer(currentSpeed, e -> moveSnake());
        gameTimer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * Gère les mouvements du serpent
     */
    private void moveSnake() {
        // vérifie si la tête touche la pomme avant de bouger
        Point head = snake.getBody().get(0);
        if (head.equals(apple.getPosition())) {
            snake.setHasEaten(true);
            apple.respawn();
            score += 10;

            // augmenter la vitesse
            if (currentSpeed > 100) {
                currentSpeed -= 5;
                gameTimer.setDelay(currentSpeed);
            }
        }

        snake.move();

        // vérifie la collision avec le serpent lui-même
        if (snake.checkSelfCollision()) {
            System.out.println("Tu t'es mangé toi-même !");
            gameTimer.stop();
            gameOver = true;
            repaint();
            return;
        }

        // vérifie les collisions avec les murs
        checkCollision();
        repaint();
    }

    /**
     * Gère les collisions avec les murs
     */
    public void checkCollision() {
        // collision avec les murs
        Point head = snake.getBody().get(0);
        int collisionX = gameBoard.getWidth() / TILE_SIZE;
        int collisionY = gameBoard.getHeight() / TILE_SIZE;
        if (head.x >= collisionX || head.x < 0 || head.y >= collisionY || head.y < 0) {
            System.out.println("Tu as perdu.");
            gameTimer.stop();
            gameOver = true;
            repaint();
        }
    }

    /**
     * Gère le lancement d'une nouvelle partie après une partie perdue
     */
    public void restart() {
        this.snake = new Snake("RIGHT", new ArrayList<>());
        this.apple = new Apple(new Point(8, 10), gameBoard, snake);
        this.gameOver = false;
        this.score = 0;
        this.currentSpeed = GAME_SPEED;
        gameTimer.setDelay(currentSpeed);
        gameTimer.restart();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            gameBoard.draw(g, snake, apple, score, gameOver);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.setDirection("UP");
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                snake.setDirection("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection("RIGHT");
                break;
        }

        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restart();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // setters
    public void setScore(int score) {
        this.score = score;
    }
}
