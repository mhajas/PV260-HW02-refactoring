package pv260.hw02.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pv260.hw02.engine.entity.Point;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameContext {

    Logger logger = LoggerFactory.getLogger(GameContext.class);


    private static GameContext instance = new GameContext();

    private Map<pv260.hw02.engine.entity.Point,Color> gameBoard = new HashMap<>();
    private int width;
    private int height;
    private Color background;
    private int gamePace;

    private GameContext() {
    }

    public static GameContext getInstance() {
        return instance;
    }

    public GameContext initBoard(){
        for(int y=0; y<height; y++){
            initRow(width, y, background);
        }
        return this;
    }

    public void changeColor(pv260.hw02.engine.entity.Point point, Color color){
        gameBoard.put(point, color);
    }

    public Map<pv260.hw02.engine.entity.Point, Color> getGameBoard() {
        return gameBoard;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGamePace() {
        return gamePace;
    }

    public void speedUpGamePace(int numberOf) {
        this.gamePace = this.gamePace - numberOf;
        if(this.gamePace<=0){
            this.gamePace = 10;
        }
        logger.info("The game was speeded up: " + gamePace);
    }

    public void speedDownGamePace(int numberOf) {
        this.gamePace = this.gamePace + numberOf;
        logger.info("The game was speeded down: " + gamePace);
    }

    public void makePointDefault(pv260.hw02.engine.entity.Point point){
        gameBoard.put(point, background);
    }

    public GameContext width(int width) {
        this.width = width;
        return this;
    }

    public GameContext height(int height) {
        this.height = height;
        return this;
    }

    public GameContext background(Color background) {
        this.background = background;
        return this;
    }

    public GameContext gamePace(int gamePace) {
        this.gamePace = gamePace;
        return this;
    }

    private void initRow(int width, int column, Color background){
        for(int x=0; x<width; x++){
            changeColor(new Point(x, column), background);
        }
    }
}
