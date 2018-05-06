package pv260.hw02.tron;

import pv260.hw02.engine.Direction;
import pv260.hw02.engine.InputHandlers.AbstractHandler;
import pv260.hw02.engine.Element;
import pv260.hw02.engine.Point;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mhajas
 */
public class TronPlayer implements Element {
    private String name = null;
    private List<Point> playerPath = new ArrayList<>();
    private Direction currentDirection;
    private Color color;
    private AbstractHandler handler;

    public TronPlayer(String name, Point startPosition, Direction startDirection, Color color, AbstractHandler handler) {
        this.name = name;
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

    @Override
    public List<Point> getElementsAllPoints() {
        return getPlayerPath();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void handleEvent(InputEvent e){
        handler.handleEvent(e, this);
    }

    @Override
    public void resolveConflict(Element conflictingParty) {
        System.out.println("Player " + getName() + " lost the game :(.");
        System.exit(0);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", currentDirection=" + currentDirection +
                ", color=" + color +
                '}';
    }
}
