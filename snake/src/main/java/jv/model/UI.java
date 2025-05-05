package jv.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class UI extends JPanel implements KeyListener {
    private static final int TILE_SIZE = 15;
    private static final int GAME_SPEED = 100;

    private GameBoard gameBoard;
    private Snake snake;
    private Apple apple;
    private Timer gameTimer;

    public UI() {
        this.gameBoard = new GameBoard(700, 700);
        this.snake = new Snake("RIGHT", new ArrayList<>());
        this.apple = new Apple(new Point(TILE_SIZE, TILE_SIZE));

        gameTimer = new Timer(GAME_SPEED, e -> moveSnake());
        gameTimer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    private void moveSnake() {
        snake.move();
        repaint();
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
