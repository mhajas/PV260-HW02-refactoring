package pv260.hw02.snake.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.GameContext;
import pv260.hw02.engine.enums.Direction;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.InputHandlers.AbstractHandler;
import pv260.hw02.engine.entity.Point;
import pv260.hw02.engine.entity.MovablePlayer;

import java.awt.Color;

/**
 * @author mhajas
 */
public class SnakePlayer extends MovablePlayer {

    Logger logger = LoggerFactory.getLogger(SnakePlayer.class);

    private int piecesOfFoodEaten = 0;

    public SnakePlayer(String name, Point startPosition, Direction startDirection, Color color, AbstractHandler handler) {
        super(name, startPosition, startDirection, color, handler);
    }

    @Override
    public void executeStep(Point newPosition) {
        playerPath.add(newPosition);
        GameContext.getInstance().changeColor(newPosition, this.color);
        removeLast();
    }

    public void removeLast() {
        if(piecesOfFoodEaten == 0) { // no food, just move
            Point p = playerPath.remove(0);
            GameContext.getInstance().makePointDefault(p);
        } else {
            piecesOfFoodEaten--;
        }
    }

    @Override
    public void hitSomebody(Element otherParty) {
        if (otherParty instanceof SnakeFood) {
            logger.info("Player hit food");
            playerPath.add(otherParty.getElementsAllPoints().get(0));
            piecesOfFoodEaten++;
            GameContext.getInstance().speedUpGamePace(10);
        } else if (otherParty instanceof SnakePlayer) {
            System.out.println("Player " + getName() + " lost");
            System.exit(0);
        }
    }

    @Override
    public void gotHit(Element otherParty) {

    }
}
