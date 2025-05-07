package jv.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GameBoard {
    private int width;
    private int height;
    private static final int TILE_SIZE = 20;
    private BufferedImage startImage;

    private Font retroFont12;
    private Font retroFont14;
    private Font retroFont32;
    private static final Color GREEN_NOKIA = new Color(169, 224, 0);

    /**
     * Chargement du plateau de jeu
     * 
     * @param width  Largeur du plateau
     * @param height Hauteur du plateau
     */
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

        loadFonts();
    }

    /**
     * Centralize le chargement et le stockage des fonts dans des variables
     */
    private void loadFonts() {
        try {
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream("PressStart2P-Regular.ttf");
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            retroFont12 = baseFont.deriveFont(12f);
            retroFont14 = baseFont.deriveFont(14f);
            retroFont32 = baseFont.deriveFont(32f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            retroFont12 = new Font("SansSerif", Font.PLAIN, 12); // fallback
            retroFont14 = new Font("SansSerif", Font.PLAIN, 14);
            retroFont32 = new Font("SansSerif", Font.PLAIN, 32);
        }
    }

    /**
     * Permet de dessiner les différents éléments
     * 
     * @param g
     * @param snake Le serpent
     * @param apple La pomme
     * @param score Le score
     * @throws FontFormatException Gère les exceptions des polices
     * @throws IOException         Gère les exceptions des images
     */
    public void draw(Graphics g, Snake snake, Apple apple, int score)
            throws FontFormatException, IOException {
        if (g == null)
            return;

        // fond
        g.setColor(GREEN_NOKIA);
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
        g.setFont(retroFont12);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Score : " + score, 10, 20);
    }

    public void drawStartMenu(Graphics g) throws FontFormatException, IOException {
        g.setColor(GREEN_NOKIA);
        g.fillRect(0, 0, 400, 400);

        g.setFont(retroFont32);
        g.setColor(Color.DARK_GRAY);
        g.drawString("snake", (width / 2) - 80, height - 160);

        if (startImage != null) {
            int newWidth = 200;
            int newHeight = 121;
            g.drawImage(startImage, width - startImage.getWidth() / 2, 60, newWidth, newHeight, null);
        }

        g.setFont(retroFont14);
        g.drawString("Appuie sur ENTREE", width / 2 - 120, height - 80);
    }

    public void drawGameOver(Graphics g, int score) throws FontFormatException, IOException {
        g.setColor(GREEN_NOKIA);
        g.fillRect(0, 0, 400, 400);

        g.setColor(Color.RED);
        g.setFont(retroFont32);
        g.drawString("Game Over", (getWidth() / 2) - 140, getHeight() / 2);

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
