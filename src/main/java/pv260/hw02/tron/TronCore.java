package pv260.hw02.tron;

import pv260.hw02.engine.Core;
import pv260.hw02.engine.Direction;
import pv260.hw02.engine.Player;
import pv260.hw02.engine.Point;
import pv260.hw02.tron.InputHandlers.TronMouseHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class TronCore extends Core {

    public static final int MOVE_AMOUNT = 5;

    @Override
    public void addPlayers(List<Player> players) {
        players.add(new TronPlayer("PLAYER3", new Point(800,800), Direction.LEFT, Color.RED, new TronMouseHandler()));
    }

    @Override
    public boolean isConflict(Point currentPosition, List<Point> alreadyExists) {
        return alreadyExists.stream().filter(point -> point.equals(currentPosition)).toArray().length != 0;
    }

    @Override
    public Point nextStep(Player player) {
        if (!(player instanceof TronPlayer)) {
            return null;
        }

        TronPlayer tronPlayer = (TronPlayer) player;

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
    public void draw(Graphics2D g, Player player) {
            g.setColor(player.getColor());
            player.getPlayersConflictingPoints().forEach(point -> g.fillRect(point.getX(), point.getY(), 10, 10));
    }
}
