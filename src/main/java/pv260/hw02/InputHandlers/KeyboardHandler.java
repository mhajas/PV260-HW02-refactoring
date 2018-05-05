package pv260.hw02.InputHandlers;

import pv260.hw02.Direction;
import pv260.hw02.Player;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class KeyboardHandler extends AbstractHandler {

    private int up;
    private int down;
    private int right;
    private int left;

    public KeyboardHandler(int up, int down, int right, int left) {
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
        KeyEvent e = (KeyEvent) ie;
        if (e.getKeyCode() == up) {
            if (player.getCurrentDirection() != Direction.DOWN) {
                player.setCurrentDirection(Direction.UP);
            }
        } else if (e.getKeyCode() == down) {
            if (player.getCurrentDirection() != Direction.UP) {
                player.setCurrentDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == right) {
            if (player.getCurrentDirection() != Direction.LEFT) {
                player.setCurrentDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == left) {
            if (player.getCurrentDirection() != Direction.RIGHT) {
                player.setCurrentDirection(Direction.LEFT);
            }
        }
    }
}
