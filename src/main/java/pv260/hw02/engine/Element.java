package pv260.hw02.engine;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.util.List;

/**
 * @author mhajas
 */
public interface Element {

    String getName();
    Color getColor();
    void handleEvent(InputEvent e);
    List<Point> getElementsAllPoints();
    void resolveConflict(Element conflictingParty);
}
