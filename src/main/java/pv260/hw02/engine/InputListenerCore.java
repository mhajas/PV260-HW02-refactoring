package pv260.hw02.engine;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author mhajas
 */
public abstract class InputListenerCore implements KeyListener, MouseListener,
        MouseMotionListener {
    public abstract void onKey(InputEvent e);

    public void keyPressed(KeyEvent e) {
        onKey(e);
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent arg0) {

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        onKey(e);
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

}
