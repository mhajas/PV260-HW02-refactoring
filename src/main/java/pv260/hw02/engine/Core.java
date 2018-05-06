package pv260.hw02.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.InputHandlers.MovableMouseHandler;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.entity.MovablePlayer;
import pv260.hw02.snake.SnakeCore;
import pv260.hw02.snake.entity.SnakePlayer;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Core extends InputListenerCore{

    protected Logger logger = LoggerFactory.getLogger(Core.class);

    private static final DisplayMode modes[] =
            {
                    new DisplayMode(1920,1080,32,0),
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

    protected List<Element> elements = new ArrayList<>();

    public void stop() {
        running = false;
    }

    public void run() {
        try {
            init();
            addElements(elements);
            gameLoop();
        } finally {
            sm.restoreScreen();
        }
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
            drawAllElements(g);

            g.dispose();
            sm.update();

            try {
                Thread.sleep(getGamePace());
            } catch (Exception ex) {
            }
        }
    }

    protected void drawAllElements(Graphics2D g) {
        elements.forEach(player -> draw(g, player));
    }

    protected abstract void draw(Graphics2D g, Element player);


    public abstract Color getBackgroundColor();

    public void drawBackground(Graphics2D g) {
        g.setColor(getBackgroundColor());
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
    }


    public void gameLogic() {
        for (Element e : elements) {

            if (!(e instanceof MovablePlayer)) {
                return;
            }

            MovablePlayer movablePlayer = (MovablePlayer) e;

            Point newPosition = movablePlayer.computeNextStep(getMoveAmount(), sm);

            checkValidity(movablePlayer, newPosition);

            movablePlayer.executeStep(newPosition);
        }
    }

    protected void checkValidity(Element element, Point newPosition) {

    }

    public abstract void addElements(List<Element> elements);
    public abstract boolean isConflict(Point currentPosition, List<Point> alreadyExists);

    public long getGamePace() {
        return 40;
    }

    @Override
    public void onKey(InputEvent e) {
        elements.forEach(player -> player.handleEvent(e));
    }

    public int getMoveAmount() {
        return 10;
    }
}
