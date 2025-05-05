package jv.model;

import java.awt.Point;

public class Apple {
    private Point position;

    public Apple(Point position) {
        setPosition(position);
    }

    public void respawn(int width, int height) {

    }

    // public Point getPosition() {

    // }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
