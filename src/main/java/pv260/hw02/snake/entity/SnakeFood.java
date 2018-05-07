package pv260.hw02.snake.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.GameContext;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.presentation.Point;

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

    private String name;
    private List<Point> foodPoints = new ArrayList<>();
    private Color color;

    public SnakeFood(String name, Color color, Point p) {
        this.name = name;
        this.color = color;
        foodPoints.add(p);
    }

    public void changePosition() {
        Random rand = new Random();
        foodPoints.clear();

        Point newPoint = new Point(rand.nextInt(GameContext.getInstance().getWidth()),
                rand.nextInt(GameContext.getInstance().getHeight()));

        foodPoints.add(newPoint);
        GameContext.getInstance().changeColor(newPoint, color);
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
