package pv260.hw02.tron.entity;

import pv260.hw02.engine.GameContext;
import pv260.hw02.engine.enums.Direction;
import pv260.hw02.engine.InputHandlers.AbstractHandler;
import pv260.hw02.presentation.Point;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.entity.MovablePlayer;

import java.awt.Color;

/**
 * @author mhajas
 */
public class TronPlayer extends MovablePlayer {

    public TronPlayer(String name, Point startPosition, Direction startDirection, Color color, AbstractHandler handler) {
        super(name, startPosition, startDirection, color, handler);
    }

    @Override
    public void executeStep(Point newPosition) {
        playerPath.add(newPosition);
        GameContext.getInstance().changeColor(newPosition, this.color);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", currentDirection=" + currentDirection +
                ", color=" + color +
                '}';
    }

    @Override
    public void hitSomebody(Element otherParty) {
        System.out.println("Player: " + getName() + " lost the game");
        System.exit(0);
    }

    @Override
    public void gotHit(Element otherParty) {
        // nothing
    }
}
