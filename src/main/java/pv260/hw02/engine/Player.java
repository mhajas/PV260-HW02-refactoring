package pv260.hw02.engine;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;

/**
 * @author mhajas
 */
public interface Player {

    void handleEvent(InputEvent e);
    void addPoint(pv260.hw02.engine.Point p);
    String getName();
    Color getColor();
    List<Point> getPlayersConflictingPoints();
}
