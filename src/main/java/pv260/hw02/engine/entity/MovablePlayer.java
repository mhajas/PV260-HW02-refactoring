package pv260.hw02.engine.entity;

import pv260.hw02.engine.GameContext;
import pv260.hw02.engine.enums.Direction;
import pv260.hw02.engine.InputHandlers.AbstractHandler;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mhajas
 */
public abstract class MovablePlayer implements Element {
    protected String name = null;
    protected List<Point> playerPath = new ArrayList<>();
    protected Direction currentDirection;
    protected Color color;
    protected AbstractHandler handler;

    public MovablePlayer(String name, Point startPosition, Direction startDirection, Color color, AbstractHandler handler) {
        this.name = name;
        playerPath.add(startPosition);
        this.currentDirection = startDirection;
        this.color = color;
        this.handler = handler;
    }

    public String getName() {
        return name;
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

    public Point computeNextStep() {
        GameContext gm = GameContext.getInstance();
        Point currentPosition = getCurrentPosition();
        if (currentPosition == null) {
            return null;
        }

        Point newPosition = new Point(currentPosition);
        switch (getCurrentDirection()) {
            case UP:
                if (currentPosition.getY() > 0) {
                    newPosition.setY(newPosition.getY() - 1);
                } else {
                    newPosition.setY(gm.getHeight());
                }
                break;
            case RIGHT:
                if (currentPosition.getX() < gm.getWidth()) {
                    newPosition.setX(newPosition.getX() + 1);
                } else {
                    newPosition.setX(0);
                }
                break;
            case DOWN:
                if (currentPosition.getY() < gm.getHeight()) {
                    newPosition.setY(newPosition.getY() + 1);
                } else {
                    newPosition.setY(0);
                }
                break;
            case LEFT:
                if (currentPosition.getX() > 0) {
                    newPosition.setX(newPosition.getX() - 1);
                } else {
                    newPosition.setX(gm.getWidth());
                }
                break;
        }
        return newPosition;
    }

    public abstract void executeStep(Point newPosition);
}
