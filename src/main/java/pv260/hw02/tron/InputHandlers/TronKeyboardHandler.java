package pv260.hw02.tron.InputHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.Direction;
import pv260.hw02.engine.Player;
import pv260.hw02.engine.InputHandlers.AbstractHandler;
import pv260.hw02.tron.TronPlayer;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TronKeyboardHandler extends AbstractHandler {

    Logger logger = LoggerFactory.getLogger(TronKeyboardHandler.class);

    private int up;
    private int down;
    private int right;
    private int left;

    public TronKeyboardHandler(int up, int down, int right, int left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    @Override
    public void handleEvent(InputEvent ie, Player player) {
        if(!(ie instanceof KeyEvent)){
            return;
        }

        if(!(player instanceof TronPlayer)) {
            return;
        }

        TronPlayer tronPlayer = (TronPlayer) player;

        KeyEvent e = (KeyEvent) ie;
        logger.info("Player " + tronPlayer + "pressed key on keyboard with id: " + e.getKeyCode());

        if (e.getKeyCode() == up) {
            if (tronPlayer.getCurrentDirection() != Direction.DOWN) {
                tronPlayer.setCurrentDirection(Direction.UP);
            }
        } else if (e.getKeyCode() == down) {
            if (tronPlayer.getCurrentDirection() != Direction.UP) {
                tronPlayer.setCurrentDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == right) {
            if (tronPlayer.getCurrentDirection() != Direction.LEFT) {
                tronPlayer.setCurrentDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == left) {
            if (tronPlayer.getCurrentDirection() != Direction.RIGHT) {
                tronPlayer.setCurrentDirection(Direction.LEFT);
            }
        }
    }
}
