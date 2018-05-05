package pv260.hw02.InputHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.Direction;
import pv260.hw02.Player;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MouseHandler extends AbstractHandler {

    Logger logger = LoggerFactory.getLogger(KeyboardHandler.class);

    @Override
    public void handleEvent(InputEvent ie, Player player) {
        if (!(ie instanceof MouseEvent)) {
            return;
        }
        MouseEvent e = (MouseEvent) ie;
        logger.info("Player " + player + "pressed key on mouse with id: " + e.getButton());

        switch (e.getButton()) { //1-left 3-right
            case 1:
                rotate90Left(player);
                break;
            case 3:
                rotate90Right(player);
                break;
        }
    }

    private void rotate90Left(Player player){
        switch (player.getCurrentDirection()){
            case UP:
                player.setCurrentDirection(Direction.LEFT);
                break;
            case DOWN:
                player.setCurrentDirection(Direction.RIGHT);
                break;
            case LEFT:
                player.setCurrentDirection(Direction.DOWN);
                break;
            case RIGHT:
                player.setCurrentDirection(Direction.UP);
                break;
        }
    }

    private void rotate90Right(Player player){
        switch (player.getCurrentDirection()){
            case UP:
                player.setCurrentDirection(Direction.RIGHT);
                break;
            case DOWN:
                player.setCurrentDirection(Direction.LEFT);
                break;
            case LEFT:
                player.setCurrentDirection(Direction.UP);
                break;
            case RIGHT:
                player.setCurrentDirection(Direction.DOWN);
                break;
        }
    }
}
