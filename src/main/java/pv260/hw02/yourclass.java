package pv260.hw02;

import pv260.hw02.InputHandlers.KeyboardHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class yourclass extends Core implements KeyListener, MouseListener,
        MouseMotionListener {

    private List<Player> players = new ArrayList<>();
    public static final int MOVE_AMOUNT = 5;

    public void init() {
        super.init();

        Window w = sm.getFullScreenWindow();
        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);

        players.add(new Player(new Point(40,40), Direction.RIGHT, Color.GREEN,
                new KeyboardHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT)));
        players.add(new Player(new Point(600,440), Direction.LEFT, Color.RED,
                new KeyboardHandler(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A)));
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    public Point nextStep(Player player) {
        Point currentPosition = player.getCurrentPosition();
        if (currentPosition == null) {
            return null;
        }

        Point newPosition = new Point(currentPosition);
        switch (player.getCurrentDirection()) {
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

    public boolean isConflict(Point currentPosition, List<Point> alreadyExists) {
        return alreadyExists.stream().filter(point -> point.equals(currentPosition)).toArray().length != 0;
    }

    public void draw(Graphics2D g) {

        for (Player p : players) {
            Point newPosition = nextStep(p);

            players.forEach(p1 -> {
                if (isConflict(newPosition, p1.getPlayerPath())) {
                    System.exit(0);
                }
            });

            p.addPoint(newPosition);

        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
        
        for (Player p : players) {
            g.setColor(p.getColor());
            p.getPlayerPath().forEach(point -> g.fillRect(point.getX(), point.getY(), 10, 10));
        }
    }

    public void keyPressed(KeyEvent e) {
        players.forEach(player -> player.handleEvent(e));
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent arg0) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
