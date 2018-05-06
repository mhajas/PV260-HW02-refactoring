package pv260.hw02.snake.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.Direction;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.InputHandlers.AbstractHandler;
import pv260.hw02.engine.Point;
import pv260.hw02.engine.entity.MovablePlayer;

import java.awt.Color;

/**
 * @author mhajas
 */
public class SnakePlayer extends MovablePlayer {

    Logger logger = LoggerFactory.getLogger(SnakePlayer.class);


    private int skipRemoving = 0;

    public SnakePlayer(String name, Point startPosition, Direction startDirection, Color color, AbstractHandler handler) {
        super(name, startPosition, startDirection, color, handler);
    }

    @Override
    public void executeStep(Point newPosition) {
        playerPath.add(newPosition);
        removeLast();
    }

    public void removeLast() {
        if(skipRemoving == 0) {
            playerPath.remove(0);
        } else {
            skipRemoving--;
        }
    }

    @Override
    public void hitSomebody(Element otherParty) {
        if (otherParty instanceof SnakeFood) {
            logger.info("Player hit food");
            playerPath.add(otherParty.getElementsAllPoints().get(0));
            skipRemoving++;
        } else if (otherParty instanceof SnakePlayer) {
            System.out.println("Player " + getName() + " lost");
            System.exit(0);
        }
    }

    @Override
    public void gotHit(Element otherParty) {

    }
}
