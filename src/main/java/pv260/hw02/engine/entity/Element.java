package pv260.hw02.engine.entity;

import pv260.hw02.presentation.Point;

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
    void hitSomebody(Element otherParty);
    void gotHit(Element otherParty);
}
