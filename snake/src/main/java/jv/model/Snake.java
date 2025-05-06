package jv.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private String direction;
    private List<Point> body;
    private boolean hasEaten = false;

    public Snake(String direction, List<Point> body) {
        setDirection(direction);
        if (body == null || body.isEmpty()) {
            body = new ArrayList<>();
            body.add(new Point(5, 5));
        }
        setBody(body);
    }

    /**
     * Gère les mouvements du serpent
     */
    public void move() {
        Point head = body.get(0);
        switch (direction) {
            case "UP":
                head = new Point(head.x, head.y - 1);
                break;
            case "DOWN":
                head = new Point(head.x, head.y + 1);
                break;
            case "LEFT":
                head = new Point(head.x - 1, head.y);
                break;
            case "RIGHT":
                head = new Point(head.x + 1, head.y);
                break;
        }

        body.add(0, head);
        if (!hasEaten) {
            body.remove(body.size() - 1);
        } else {
            hasEaten = false;
        }
    }

    /**
     * Vérifie s'il y a collision entre le tête du serpent et son corps
     * 
     * @return Retourne vrai si la tête touche le corps, faux si non
     */
    public boolean checkSelfCollision() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    // getters et setters
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<Point> getBody() {
        return body;
    }

    public void setBody(List<Point> body) {
        this.body = body;
    }

    public void setHasEaten(boolean hasEaten) {
        this.hasEaten = hasEaten;
    }
}
