package pv260.hw02.tron;

import pv260.hw02.engine.Element;
import pv260.hw02.engine.Core;
import pv260.hw02.engine.Direction;
import pv260.hw02.engine.Point;
import pv260.hw02.tron.InputHandlers.TronMouseHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class TronCore extends Core {

    public static final int MOVE_AMOUNT = 5;

    @Override
    public void addElements(List<Element> elements) {
        elements.add(new TronPlayer("PLAYER3", new Point(800,800), Direction.LEFT, Color.RED, new TronMouseHandler()));
    }

    @Override
    public boolean isConflict(Point currentPosition, List<Point> alreadyExists) {
        return alreadyExists.stream().filter(point -> point.equals(currentPosition)).toArray().length != 0;
    }

    public Point nextStep(TronPlayer tronPlayer) {

        Point currentPosition = tronPlayer.getCurrentPosition();
        if (currentPosition == null) {
            return null;
        }

        Point newPosition = new Point(currentPosition);
        switch (tronPlayer.getCurrentDirection()) {
            case UP:
                if (currentPosition.getY() > 0) {
                    newPosition.setY(newPosition.getY() - MOVE_AMOUNT);
                } else {
                    newPosition.setY(sm.getHeight());
                }
                break;
            case RIGHT:
                if (currentPosition.getX() < sm.getWidth()) {
                    newPosition.setX(newPosition.getX() + MOVE_AMOUNT);
                } else {
                    newPosition.setX(0);
                }
                break;
            case DOWN:
                if (currentPosition.getY() < sm.getHeight()) {
                    newPosition.setY(newPosition.getY() + MOVE_AMOUNT);
                } else {
                    newPosition.setY(0);
                }
                break;
            case LEFT:
                if (currentPosition.getX() > 0) {
                    newPosition.setX(newPosition.getX() - MOVE_AMOUNT);
                } else {
                    newPosition.setX(sm.getWidth());
                }
                break;
        }
        return newPosition;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public void gameLogic(Element element) {
        if (!(element instanceof TronPlayer)) {
            return;
        }

        TronPlayer tronPlayer = (TronPlayer) element;

        Point newPosition = nextStep(tronPlayer);

        elements.forEach(p1 -> {
            if (isConflict(newPosition, p1.getElementsAllPoints())) {
                tronPlayer.resolveConflict(element);
            }
        });

        tronPlayer.addPoint(newPosition);
    }

    @Override
    public void draw(Graphics2D g, Element player) {
            g.setColor(player.getColor());
            player.getElementsAllPoints().forEach(point -> g.fillRect(point.getX(), point.getY(), 10, 10));
    }
}
