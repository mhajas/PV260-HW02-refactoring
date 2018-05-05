package pv260.hw02.InputHandlers;

import pv260.hw02.Player;

import java.awt.event.InputEvent;

public abstract class AbstractHandler {
    public abstract void handleEvent(InputEvent e, Player player);
}
