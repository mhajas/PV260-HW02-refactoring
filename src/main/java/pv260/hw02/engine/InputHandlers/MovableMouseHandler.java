package pv260.hw02.engine.InputHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.entity.MovablePlayer;
import pv260.hw02.engine.enums.Direction;
import pv260.hw02.engine.entity.Element;
import pv260.hw02.tron.entity.TronPlayer;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * @author mhajas
 */
public class MovableMouseHandler extends AbstractHandler{
    Logger logger = LoggerFactory.getLogger(MovableMouseHandler.class);

    @Override
    public void handleEvent(InputEvent ie, Element player) {
        if (!(ie instanceof MouseEvent)) {
            return;
        }

        if (!(player instanceof MovablePlayer)) {
            return;
        }

        MovablePlayer movablePlayer = (MovablePlayer) player;

        MouseEvent e = (MouseEvent) ie;
        logger.info("Player " + movablePlayer + "pressed key on mouse with id: " + e.getButton());

        switch (e.getButton()) { //1-left 3-right
            case 1:
                rotate90Left(movablePlayer);
                break;
            case 3:
                rotate90Right(movablePlayer);
                break;
        }
    }

    private void rotate90Left(MovablePlayer player){
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

    private void rotate90Right(MovablePlayer player){
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
