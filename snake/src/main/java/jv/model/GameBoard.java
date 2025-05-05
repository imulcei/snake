package jv.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GameBoard {
    private int width;
    private int height;
    private static final int TILE_SIZE = 15;

    public GameBoard(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void draw(Graphics g, Snake snake, Apple apple) {
        if (g == null)
            return;

        // fond
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 700, 700);

        // dessin du serpent
        g.setColor(Color.GREEN);
        if (snake.getBody().isEmpty()) {
            System.out.println("Le serpent est vide !");
        }
        for (Point p : snake.getBody()) {
            System.out.println("Dessiner le serpent à : " + p.x + ", " + p.y); // Pour debug
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // dessin de la pomme
        g.setColor(Color.RED);
        Point applePosition = apple.getPosition();
        g.fillRect(applePosition.x * TILE_SIZE, applePosition.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
