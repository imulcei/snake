package jv.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private String direction;
    private List<Point> body;

    public Snake(String direction, List<Point> body) {
        setDirection(direction);
        if (body == null || body.isEmpty()) {
            body = new ArrayList<>();
            body.add(new Point(5, 5));
        }
        setBody(body);
    }

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
        body.remove(body.size() - 1);
    }

    public void grow() {

    }

    public boolean checkSelfCollision() {
        return false;
    }

    // public Point getHead() {

    // }

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
}
