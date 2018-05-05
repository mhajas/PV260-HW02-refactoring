package pv260.hw02;

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

        players.add(new Player(new Point(40,40), Direction.RIGHT, Color.GREEN));
        players.add(new Player(new Point(600,440), Direction.LEFT, Color.RED));
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
        Player player1 = players.get(0);
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (player1.getCurrentDirection() != Direction.DOWN) {
                player1.setCurrentDirection(Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (player1.getCurrentDirection() != Direction.UP) {
                player1.setCurrentDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (player1.getCurrentDirection() != Direction.LEFT) {
                player1.setCurrentDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (player1.getCurrentDirection() != Direction.RIGHT) {
                player1.setCurrentDirection(Direction.LEFT);
            }
        }
        
        Player player2 = players.get(1);
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (player2.getCurrentDirection() != Direction.DOWN) {
                player2.setCurrentDirection(Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (player2.getCurrentDirection() != Direction.UP) {
                player2.setCurrentDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (player2.getCurrentDirection() != Direction.LEFT) {
                player2.setCurrentDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (player2.getCurrentDirection() != Direction.RIGHT) {
                player2.setCurrentDirection(Direction.LEFT);
            }
        }
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
