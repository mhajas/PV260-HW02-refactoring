package pv260.hw02.presentation;

import pv260.hw02.engine.GameContext;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePresentation {

    private static final int SQUARE_SIZE = 10; //10x10

    private static final DisplayMode modes[] =
            {
                    new DisplayMode(1920,1080,32,0),
                    new DisplayMode(1680, 1050, 32, 0),
                    //new DisplayMode(1280,1024,32,0),
                    new DisplayMode(800, 600, 32, 0),
                    new DisplayMode(800, 600, 24, 0),
                    new DisplayMode(800, 600, 16, 0),
                    new DisplayMode(640, 480, 32, 0),
                    new DisplayMode(640, 480, 24, 0),
                    new DisplayMode(640, 480, 16, 0),
            };

    protected ScreenManager sm;

    public GamePresentation(){
        sm = new ScreenManager();
        DisplayMode dm = sm.findFirstCompatibaleMode(modes);
        sm.setFullScreen(dm);
        Window w = sm.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new java.awt.Point(0, 0), "null"));
    }

    public void refresh(GameContext board){
        Graphics2D g = sm.getGraphics();
        drawAllElements(g, board);
        g.dispose();
        sm.update();
        try {
            Thread.sleep(board.getGamePace());
        } catch (Exception ex) {
        }
    }

    private void drawAllElements(Graphics2D g, GameContext board) {
        board.getGameBoard().forEach((point, color) ->{
            g.setColor(color);
            g.fillRect(point.getX()* SQUARE_SIZE, point.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        });
    }

    public Window getWindow(){
        return sm.getFullScreenWindow();
    }

    public int getWidth(){
        return sm.getWidth();
    }

    public int getHeight(){
        return sm.getHeight();
    }

    public int getSquareSize(){
        return SQUARE_SIZE;
    }

    public void restoreScreen() {
        sm.restoreScreen();
    }
}
