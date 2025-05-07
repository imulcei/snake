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
    private int currentSpeed = GAME_SPEED;
    private GameState gameState = GameState.START_MENU;

    private enum GameState {
        START_MENU, PLAYING, GAME_OVER
    };

    /**
     * Création d'une nouvelle partie
     */
    public Game() {
        this.gameBoard = new GameBoard(400, 400);
        this.snake = new Snake("RIGHT", new ArrayList<>());
        this.apple = new Apple(new Point(0, 0), gameBoard, snake);

        gameTimer = new Timer(currentSpeed, e -> moveSnake());

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
            gameState = GameState.GAME_OVER;
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
            gameState = GameState.GAME_OVER;
            repaint();
        }
    }

    /**
     * Gère le lancement d'une nouvelle partie après une partie perdue
     */
    public void restart() {
        this.snake = new Snake("RIGHT", new ArrayList<>());
        this.apple = new Apple(new Point(8, 10), gameBoard, snake);
        this.score = 0;
        this.currentSpeed = GAME_SPEED;
        gameTimer.setDelay(currentSpeed);
        gameTimer.restart();
        this.gameState = GameState.PLAYING;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            switch (gameState) {
                case START_MENU:
                    gameBoard.drawStartMenu(g);
                    break;
                case PLAYING:
                    gameBoard.draw(g, snake, apple, score);
                    break;
                case GAME_OVER:
                    gameBoard.drawGameOver(g, score);
                    break;
                default:
                    break;
            }
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

        // gestion du menu : menu, jouer, game over
        if (gameState == GameState.START_MENU && e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameState = GameState.PLAYING;
            gameTimer.start();
            repaint();
            return;
        }

        // gestion du game over et du restart avec la touche r
        if (gameState == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                restart();
                gameState = GameState.PLAYING;
                return;
            } else if (e.getKeyCode() == KeyEvent.VK_Q) {
                System.exit(0);
            }
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
