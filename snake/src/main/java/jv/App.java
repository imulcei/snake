package jv;

import javax.swing.JFrame;
import jv.model.Game;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        Game game = new Game();

        // création de la fenêtre
        frame.add(game);
        frame.setSize(400, 430);

        // comportement à la fermeture
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // centre la fenêtre
        frame.setLocationRelativeTo(null);
        // rend la fenêtre visible
        frame.setVisible(true);
    }
}
