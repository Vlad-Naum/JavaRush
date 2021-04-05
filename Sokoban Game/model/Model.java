package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import javax.swing.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Model implements EventListener {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader;
    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public Model() {
        try {
            levelLoader = new LevelLoader(Paths.get(getClass().getResource("../res/levels.txt").toURI()));
        } catch (URISyntaxException e) {
        }
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
        currentLevel = level;
    }

    @Override
    public void move(Direction direction) {
        if(checkWallCollision(gameObjects.getPlayer(), direction)){
            return;
        }
        if(checkBoxCollisionAndMoveIfAvailable(direction)){
            return;
        }
        gameObjects.getPlayer().move(getdx(direction), getdy(direction));
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall : gameObjects.getWalls()) {
            if(gameObject.isCollision(wall, direction)){
                return true;
            }
        }
        return false;
    }

    public void checkCompletion(){
        int c = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if(home.getX() == box.getX() && home.getY() == box.getY()){
                    c++;
                    break;
                }
            }
        }
        if(c == gameObjects.getHomes().size()){
            eventListener.levelCompleted(currentLevel);
        }
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction){
        for (Box box1 : gameObjects.getBoxes()) {
            if (getGameObjects().getPlayer().isCollision(box1, direction)) {
                if(checkWallCollision(box1, direction)){
                    return true;
                }
                for (Box box2 : gameObjects.getBoxes()) {
                    if (box1.isCollision(box2, direction)) {
                        return true;
                    }
                }
                box1.move(getdx(direction), getdy(direction));
            }
        }
        return false;
    }
    public int getdx(Direction direction){
        if(direction.equals(Direction.LEFT)){
            return -FIELD_CELL_SIZE;
        }
        else if(direction.equals(Direction.RIGHT)){
            return FIELD_CELL_SIZE;
        }
        else{
            return 0;
        }
    }
    public int getdy(Direction direction){
        if(direction.equals(Direction.UP)){
            return -FIELD_CELL_SIZE;
        }
        else if(direction.equals(Direction.DOWN)){
            return FIELD_CELL_SIZE;
        }
        else{
            return 0;
        }
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restartLevel(currentLevel);
    }

    @Override
    public void levelCompleted(int level) {

    }

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }
}
