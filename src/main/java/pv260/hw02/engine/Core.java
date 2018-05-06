package pv260.hw02.engine;

import pv260.hw02.tron.TronCore;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Core implements KeyListener, MouseListener,
        MouseMotionListener {

    private static final DisplayMode modes[] =
            {
                    //new DisplayMode(1920,1080,32,0),
                    new DisplayMode(1680, 1050, 32, 0),
                    //new DisplayMode(1280,1024,32,0),
                    new DisplayMode(800, 600, 32, 0),
                    new DisplayMode(800, 600, 24, 0),
                    new DisplayMode(800, 600, 16, 0),
                    new DisplayMode(640, 480, 32, 0),
                    new DisplayMode(640, 480, 24, 0),
                    new DisplayMode(640, 480, 16, 0),
            };
    private boolean running;
    protected ScreenManager sm;

    private List<Player> players = new ArrayList<>();

    public void stop() {
        running = false;
    }

    public void run() {
        try {
            init();
            addPlayers(players);
            gameLoop();
        } finally {
            sm.restoreScreen();
        }
    }

    public static void main(String[] args) {
        new TronCore().run();
    }

    public void init() {
        sm = new ScreenManager();
        DisplayMode dm = sm.findFirstCompatibaleMode(modes);
        sm.setFullScreen(dm);
        Window w = sm.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new java.awt.Point(0, 0), "null"));
        running = true;

        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
    }

    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            Graphics2D g = sm.getGraphics();

            // Make logic of game
            gameLogic();

            // Draw result
            drawBackground(g);
            drawAllPlayers(g);

            g.dispose();
            sm.update();

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
        }
    }

    protected void drawAllPlayers(Graphics2D g) {
        players.forEach(player -> draw(g, player));
    }

    protected abstract void draw(Graphics2D g, Player player);


    public abstract Color getBackgroundColor();

    public void drawBackground(Graphics2D g) {
        g.setColor(getBackgroundColor());
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
    }


    public void gameLogic() {
        for (Player p : players) {
            Point newPosition = nextStep(p);

            players.forEach(p1 -> {
                if (isConflict(newPosition, p1.getPlayersConflictingPoints())) {
                    System.out.println("Player " + p1.getName() + " lost the game :(.");
                    System.exit(0);
                }
            });

            p.addPoint(newPosition);
        }
    }

    public abstract void addPlayers(List<Player> players);
    public abstract boolean isConflict(Point currentPosition, List<Point> alreadyExists);
    public abstract Point nextStep(Player player);

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
        players.forEach(player -> player.handleEvent(e));
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
