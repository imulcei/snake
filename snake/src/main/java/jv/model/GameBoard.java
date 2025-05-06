package jv.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class GameBoard {
    private int width;
    private int height;
    private static final int TILE_SIZE = 20;

    public GameBoard(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void draw(Graphics g, Snake snake, Apple apple) {
        if (g == null)
            return;

        // fond
        Color greenNokia = new Color(169, 224, 0);
        g.setColor(greenNokia);
        g.fillRect(0, 0, 400, 400);

        // dessin du serpent
        g.setColor(Color.DARK_GRAY);
        if (snake.getBody().isEmpty()) {
            System.out.println("Le serpent est vide !");
        }
        for (Point p : snake.getBody()) {
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // dessin de la pomme
        // Point applePosition = apple.getPosition();
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("SansSerif", Font.PLAIN, 26));
        g.drawString("✸", apple.getPosition().x * TILE_SIZE, (apple.getPosition().y + 1) * TILE_SIZE);

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
