package pv260.hw02.tron.InputHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.Direction;
import pv260.hw02.engine.Element;
import pv260.hw02.tron.TronPlayer;
import pv260.hw02.engine.InputHandlers.AbstractHandler;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class TronMouseHandler extends AbstractHandler {

    Logger logger = LoggerFactory.getLogger(TronKeyboardHandler.class);

    @Override
    public void handleEvent(InputEvent ie, Element player) {
        if (!(ie instanceof MouseEvent)) {
            return;
        }

        if (!(player instanceof TronPlayer)) {
            return;
        }

        TronPlayer tronPlayer = (TronPlayer) player;

        MouseEvent e = (MouseEvent) ie;
        logger.info("Player " + tronPlayer + "pressed key on mouse with id: " + e.getButton());

        switch (e.getButton()) { //1-left 3-right
            case 1:
                rotate90Left(tronPlayer);
                break;
            case 3:
                rotate90Right(tronPlayer);
                break;
        }
    }

    private void rotate90Left(TronPlayer player){
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

    private void rotate90Right(TronPlayer player){
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
