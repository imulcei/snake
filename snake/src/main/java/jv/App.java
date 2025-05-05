package jv;

import javax.swing.JFrame;
import jv.model.UI;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        UI ui = new UI();

        // création de la fenêtre
        frame.add(ui);
        frame.setSize(720, 720);

        // comportement à la fermeture
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // centre la fenêtre
        frame.setLocationRelativeTo(null);
        // rend la fenêtre visible
        frame.setVisible(true);
    }
}
