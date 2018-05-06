package pv260.hw02.tron;

import pv260.hw02.engine.InputHandlers.MovableKeyboardHandler;
import pv260.hw02.engine.InputHandlers.MovableMouseHandler;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.Core;
import pv260.hw02.engine.enums.Direction;
import pv260.hw02.engine.entity.Point;
import pv260.hw02.tron.entity.TronPlayer;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;

public class TronCore extends Core {

    @Override
    public void addElements(List<Element> elements) {
        elements.add(new TronPlayer("PLAYER2", new Point(10,10), Direction.RIGHT, Color.GREEN,
                new MovableKeyboardHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT)));
        elements.add(new TronPlayer("PLAYER3", new Point(50,50), Direction.LEFT, Color.RED, new MovableMouseHandler()));
    }

    @Override
    public boolean isConflict(Point currentPosition, List<Point> alreadyExists) {
        return alreadyExists.stream().filter(point -> point.equals(currentPosition)).toArray().length != 0;
    }

    public static void main(String[] args) {
        new TronCore().run();
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    protected void checkValidity(Element element, Point newPosition) {
        super.checkValidity(element, newPosition);

        elements.forEach(p1 -> {
            if (isConflict(newPosition, p1.getElementsAllPoints())) {
                element.hitSomebody(p1);
                p1.gotHit(element);
            }
        });
    }
}
