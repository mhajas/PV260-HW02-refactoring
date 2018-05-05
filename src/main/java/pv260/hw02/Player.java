package pv260.hw02;

import pv260.hw02.InputHandlers.AbstractHandler;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mhajas
 */
public class Player {
    private List<Point> playerPath = new ArrayList<Point>();
    private Direction currentDirection;
    private Color color;
    private AbstractHandler handler;

    public Player(Point startPosition, Direction startDirection, Color color, AbstractHandler handler) {
        playerPath.add(startPosition);
        this.currentDirection = startDirection;
        this.color = color;
        this.handler = handler;
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

    public void handleEvent(InputEvent e){
        handler.handleEvent(e, this);
    }
}
