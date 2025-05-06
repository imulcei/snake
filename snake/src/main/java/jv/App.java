package jv;

import javax.swing.JFrame;
import jv.model.Game;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        Game game = new Game();

        // création de la fenêtre
        frame.add(game);
        // dimensionne la fenêtre
        frame.setSize(400, 428);
        // empêche l'agrandissement de la fenêtre
        frame.setResizable(false);

        // comportement à la fermeture
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // centre la fenêtre
        frame.setLocationRelativeTo(null);
        // rend la fenêtre visible
        frame.setVisible(true);
    }
}
