package jv.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GameBoard {
    private int width;
    private int height;
    private static final int TILE_SIZE = 20;
    private BufferedImage startImage;

    public GameBoard(int width, int height) {
        setWidth(width);
        setHeight(height);

        // chargement de l'image du snake
        try {
            URL imageUrl = getClass().getClassLoader().getResource("snake.png");
            if (imageUrl == null) {
                throw new IllegalArgumentException("Image non trouvée !");
            }
            startImage = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics g, Snake snake, Apple apple, int score)
            throws FontFormatException, IOException {
        if (g == null)
            return;

        // fond
        Color greenNokia = new Color(169, 224, 0);
        g.setColor(greenNokia);
        g.fillRect(0, 0, 400, 400);

        // dessin du serpent
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < snake.getBody().size(); i++) {
            Point p = snake.getBody().get(i);
            if (i == 0) {
                g.fillRoundRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, 8, 8);
            } else {
                g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // dessin de la pomme
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("SansSerif", Font.PLAIN, 26));
        g.drawString("✸", apple.getPosition().x * TILE_SIZE, (apple.getPosition().y + 1) * TILE_SIZE);

        // dessin du score
        Font retroFont12 = Font.createFont(Font.TRUETYPE_FONT, new File("snake/resources/PressStart2P-Regular.ttf"))
                .deriveFont(12f);
        g.setFont(retroFont12);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Score : " + score, 10, 20);
    }

    public void drawStartMenu(Graphics g) throws FontFormatException, IOException {
        Color greenNokia = new Color(169, 224, 0);
        g.setColor(greenNokia);
        g.fillRect(0, 0, 400, 400);
        Font retroFont32 = Font.createFont(Font.TRUETYPE_FONT, new File("snake/resources/PressStart2P-Regular.ttf"))
                .deriveFont(32f);
        g.setColor(Color.DARK_GRAY);
        g.setFont(retroFont32);
        g.drawString("snake", (getWidth() / 2) - 80, getHeight() - 160);
        if (startImage != null) {
            int newWidth = 200;
            int newHeight = 121;
            g.drawImage(startImage, width - startImage.getWidth() / 2, 60, newWidth, newHeight, null);
        }
        Font retroFont14 = Font.createFont(Font.TRUETYPE_FONT, new File("snake/resources/PressStart2P-Regular.ttf"))
                .deriveFont(14f);
        g.setFont(retroFont14);
        g.drawString("Appuie sur ENTREE", width / 2 - 120, height - 80);
    }

    public void drawGameOver(Graphics g, int score) throws FontFormatException, IOException {
        Color greenNokia = new Color(169, 224, 0);
        g.setColor(greenNokia);
        g.fillRect(0, 0, 400, 400);
        Font retroFont32 = Font.createFont(Font.TRUETYPE_FONT, new File("snake/resources/PressStart2P-Regular.ttf"))
                .deriveFont(32f);
        g.setColor(Color.RED);
        g.setFont(retroFont32);
        g.drawString("Game Over", (getWidth() / 2) - 140, getHeight() / 2);
        Font retroFont12 = Font.createFont(Font.TRUETYPE_FONT, new File("snake/resources/PressStart2P-Regular.ttf"))
                .deriveFont(12f);
        g.setFont(retroFont12);
        g.drawString("Appuie sur R pour recommencer", (getWidth() / 2) - 170, getHeight() / 2 + 30);

        g.drawString("Score : " + score, 10, 20);
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
