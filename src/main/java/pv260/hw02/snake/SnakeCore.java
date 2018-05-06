package pv260.hw02.snake;

import pv260.hw02.engine.InputHandlers.MovableKeyboardHandler;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.Core;
import pv260.hw02.engine.Direction;
import pv260.hw02.engine.Point;
import pv260.hw02.snake.entity.SnakeFood;
import pv260.hw02.snake.entity.SnakePlayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

public class SnakeCore extends Core {

    public static final int MOVE_AMOUNT = 5;

    @Override
    public void addElements(List<Element> elements) {
        elements.add(new SnakePlayer("PLAYER2", new Point(40,40), Direction.RIGHT, Color.GREEN,
                new MovableKeyboardHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT)));
        elements.add(new SnakeFood("Food", Color.RED, new Point(100, 100), sm));
    }

    @Override
    public boolean isConflict(Point currentPosition, List<Point> alreadyExists) {
        return alreadyExists.stream().filter(point -> point.equals(currentPosition)).toArray().length != 0;
    }

    public static void main(String[] args) {
        new SnakeCore().run();
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
                logger.info("is Conflict");
                element.hitSomebody(p1);
                p1.gotHit(element);
            }
        });
    }

    @Override
    public void draw(Graphics2D g, Element player) {
            g.setColor(player.getColor());
            player.getElementsAllPoints().forEach(point -> g.fillRect(point.getX(), point.getY(), 10, 10));
    }
}
