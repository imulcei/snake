package jv.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class GameBoard {
    private int width;
    private int height;
    private static final int TILE_SIZE = 20;

    public GameBoard(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void draw(Graphics g, Snake snake, Apple apple, int score, boolean gameOver)
            throws FontFormatException, IOException {
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

        // dessin du score
        Font retroFont12 = Font.createFont(Font.TRUETYPE_FONT, new File("snake/resources/PressStart2P-Regular.ttf"))
                .deriveFont(12f);
        g.setFont(retroFont12);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Score : " + score, 10, 20);

        // dessin du gameOver
        if (gameOver) {
            g.setColor(greenNokia);
            g.fillRect(0, 0, 400, 400);
            Font retroFont32 = Font.createFont(Font.TRUETYPE_FONT, new File("snake/resources/PressStart2P-Regular.ttf"))
                    .deriveFont(32f);
            g.setColor(Color.RED);
            g.setFont(retroFont32);
            g.drawString("Game Over", (getWidth() / 2) - 140, getHeight() / 2);

            g.setFont(retroFont12);
            g.drawString("Appuie sur R pour recommencer", (getWidth() / 2) - 170, getHeight() / 2 + 30);

            g.drawString("Score : " + score, 10, 20);
        }
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
