package jv.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyListener;
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

    public Game() {
        this.gameBoard = new GameBoard(400, 400);
        this.snake = new Snake("RIGHT", new ArrayList<>());
        this.apple = new Apple(new Point(8, 10), gameBoard, snake);

        gameTimer = new Timer(GAME_SPEED, e -> moveSnake());
        gameTimer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    private void moveSnake() {
        // vérifie si la tête touche la pomme avant de bouger
        Point head = snake.getBody().get(0);
        if (head.equals(apple.getPosition())) {
            snake.setHasEaten(true);
            apple.respawn();
        }

        snake.move();

        // vérifie la collision avec le serpent lui-même
        if (snake.checkSelfCollision()) {
            System.out.println("Tu t'es mangé toi-même !");
            gameTimer.stop();
            return;
        }

        checkCollision();
        repaint();
    }

    public void checkCollision() {
        // collision avec les murs
        Point head = snake.getBody().get(0);
        int collisionX = gameBoard.getWidth() / TILE_SIZE;
        int collisionY = gameBoard.getHeight() / TILE_SIZE;
        if (head.x >= collisionX || head.x < 0 || head.y >= collisionY || head.y < 0) {
            System.out.println("Position de la tête : (" + head.x + ", " + head.y + ")");
            System.out.println("Tu as perdu.");
            gameTimer.stop();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameBoard.draw(g, snake, apple);
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
