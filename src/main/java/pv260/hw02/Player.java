package pv260.hw02;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mhajas
 */
public class Player {
    private List<Point> playerPath = new ArrayList<Point>();
    private Direction currentDirection;
    private Color color;

    public Player(Point startPosition, Direction startDirection, Color color) {
        playerPath.add(startPosition);
        this.currentDirection = startDirection;
        this.color = color;
    }

    public Point getCurrentPosition() {
        return playerPath.get(playerPath.size() - 1);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void addPoint(Point p) {
        playerPath.add(p);
    }

    public List<Point> getPlayerPath() {
        return playerPath;
    }

    public void setPlayerPath(List<Point> playerPath) {
        this.playerPath = playerPath;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
