package pv260.hw02.engine.InputHandlers;


import pv260.hw02.engine.entity.Element;

import java.awt.event.InputEvent;

public abstract class AbstractHandler {
    public abstract void handleEvent(InputEvent e, Element player);
}
