package pv260.hw02.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.entity.MovablePlayer;
import pv260.hw02.engine.entity.Point;
import pv260.hw02.presentation.GamePresentation;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Core extends InputListenerCore{

    protected Logger logger = LoggerFactory.getLogger(Core.class);

    protected GamePresentation gamePresentation;

    private boolean running;

    protected List<Element> elements = new ArrayList<>();

    public void stop() {
        running = false;
    }

    public void run() {
        try {
            init();
            addElements(elements);
            createStartPosition();
            gameLoop();
        } finally {
            gamePresentation.restoreScreen();
        }
    }

    public void init() {
        running = true;
        gamePresentation = new GamePresentation();
        gamePresentation.getWindow().addKeyListener(this);
        gamePresentation.getWindow().addMouseListener(this);
        gamePresentation.getWindow().addMouseMotionListener(this);
        GameContext.getInstance()
                .height(gamePresentation.getHeight()/gamePresentation.getSquareSize())
                .width(gamePresentation.getWidth()/gamePresentation.getSquareSize())
                .background(getBackgroundColor())
                .gamePace(this.getGamePace())
                .initBoard();
    }

    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            gameLogic();
            gamePresentation.refresh(GameContext.getInstance());
        }
    }

    public abstract Color getBackgroundColor();

    public void gameLogic() {
        for (Element e : elements) {

            if (!(e instanceof MovablePlayer)) {
                return;
            }

            MovablePlayer movablePlayer = (MovablePlayer) e;

            Point newPosition = movablePlayer.computeNextStep();

            checkValidity(movablePlayer, newPosition);

            movablePlayer.executeStep(newPosition);
        }
    }

    private void createStartPosition() {
        elements.forEach(element -> {
            element.getElementsAllPoints().forEach(point -> {
                GameContext.getInstance().changeColor(point, element.getColor());
            });
        });
    }

    protected void checkValidity(Element element, Point newPosition) {}
    public abstract void addElements(List<Element> elements);
    public abstract boolean isConflict(Point currentPosition, List<Point> alreadyExists);

    public int getGamePace(){
        return 40; //default value for all games
    }

    @Override
    public void onKey(InputEvent e) {
        elements.forEach(player -> player.handleEvent(e));
    }

}
