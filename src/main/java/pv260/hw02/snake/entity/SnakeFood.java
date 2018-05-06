package pv260.hw02.snake.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.ScreenManager;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.Point;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author mhajas
 */
public class SnakeFood implements Element {

    Logger logger = LoggerFactory.getLogger(SnakeFood.class);

    private String name = null;
    private List<Point> foodPoints = new ArrayList<>();
    private Color color;
    private ScreenManager sm;

    public SnakeFood(String name, Color color, Point p, ScreenManager sm) {
        this.name = name;
        this.color = color;
        foodPoints.add(p);
        this.sm = sm;
    }

    public void changePosition() {
        Random rand = new Random();
        foodPoints.clear();
        int x = rand.nextInt(sm.getWidth());
        x = x - (x % 10);
        int y = rand.nextInt(sm.getHeight());
        y = y - (y % 10);
        foodPoints.add(new Point(x, y));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void handleEvent(InputEvent e) {

    }

    @Override
    public List<Point> getElementsAllPoints() {
        return foodPoints;
    }

    @Override
    public void hitSomebody(Element otherParty) {

    }

    @Override
    public void gotHit(Element otherParty) {
        logger.info("Food got hit");
        changePosition();
    }
}
