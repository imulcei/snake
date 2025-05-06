package jv.model;

import java.awt.Point;

public class Apple {
    private Point position;
    private GameBoard gameBoard;
    private Snake snake;
    private static final int TILE_SIZE = 20;

    public Apple(Point position, GameBoard gameBoard, Snake snake) {
        this.position = position;
        this.gameBoard = gameBoard;
        this.snake = snake;
        this.respawn();
    }

    public void respawn() {
        int maximumX = gameBoard.getWidth() / TILE_SIZE;
        int maximumY = gameBoard.getHeight() / TILE_SIZE;

        Point newPosition;
        do {
            int x = (int) (Math.random() * (maximumX - 2) + 1);
            int y = (int) (Math.random() * (maximumY - 2) + 1);
            newPosition = new Point(x, y);
        } while (snake.getBody().contains(newPosition));

        this.setPosition(newPosition);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
