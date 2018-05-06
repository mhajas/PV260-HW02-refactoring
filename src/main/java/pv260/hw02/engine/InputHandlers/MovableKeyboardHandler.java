package pv260.hw02.engine.InputHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.Direction;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.engine.entity.MovablePlayer;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author mhajas
 */
public class MovableKeyboardHandler extends AbstractHandler {

    protected Logger logger = LoggerFactory.getLogger(MovableKeyboardHandler.class);

    private int up;
    private int down;
    private int right;
    private int left;

    public MovableKeyboardHandler(int up, int down, int right, int left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    @Override
    public void handleEvent(InputEvent ie, Element element) {
        if(!(ie instanceof KeyEvent)){
            return;
        }

        if(!(element instanceof MovablePlayer)) {
            return;
        }

        MovablePlayer movablePlayer = (MovablePlayer) element;

        KeyEvent e = (KeyEvent) ie;
        logger.info("Player " + movablePlayer + "pressed key on keyboard with id: " + e.getKeyCode());

        if (e.getKeyCode() == up) {
            if (movablePlayer.getCurrentDirection() != Direction.DOWN) {
                movablePlayer.setCurrentDirection(Direction.UP);
            }
        } else if (e.getKeyCode() == down) {
            if (movablePlayer.getCurrentDirection() != Direction.UP) {
                movablePlayer.setCurrentDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == right) {
            if (movablePlayer.getCurrentDirection() != Direction.LEFT) {
                movablePlayer.setCurrentDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == left) {
            if (movablePlayer.getCurrentDirection() != Direction.RIGHT) {
                movablePlayer.setCurrentDirection(Direction.LEFT);
            }
        }
    }
}
