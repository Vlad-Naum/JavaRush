package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {

        if(level > 60){
            level %= 60;
        }
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Darkpro\\IdeaProjects\\levels.txt"))) {//"C:\\Users\\Darkpro\\IdeaProjects\\levels.txt"
            int sizeX = 0; int sizeY = 0;
            Set<Box> boxes = new HashSet<>();
            Set<Home> homes = new HashSet<>();
            Set<Wall> walls = new HashSet<>();
            Player player = null;
            while(br.ready()){
                String str = br.readLine();
                if(str.equals("Maze: " + level)){
                    for (int i = 0; i < 6; i++) {
                        str = br.readLine();
                        switch(i){
                            case 1:
                                sizeX = Integer.parseInt(str.split(" ")[2]);
                                break;
                            case 2:
                                sizeY = Integer.parseInt(str.split(" ")[2]);
                                break;
                        }
                    }
                    int x0 = Model.FIELD_CELL_SIZE/2;
                    int y0 = Model.FIELD_CELL_SIZE/2;
                    int x, y;
                    Box box; Home home; Wall wall;
                    for (int i = 0; i < sizeY; i++) {
                        char[] ch = br.readLine().toCharArray();
                        switch (ch[0]){
                            case '.':
                                home = new Home(x0, y0);
                                homes.add(home);
                                break;
                            case '@':
                                player = new Player(x0, y0);
                                break;
                            case 'X':
                                wall = new Wall(x0, y0);
                                walls.add(wall);
                                break;
                            case '*':
                                box = new Box(x0, y0);
                                boxes.add(box);
                                break;
                            case'&':
                                box = new Box(x0, y0);
                                home = new Home(x0, y0);
                                boxes.add(box);
                                homes.add(home);
                                break;
                        }
                        for (int j = 1; j < sizeX; j++) {
                            x = x0 + Model.FIELD_CELL_SIZE;
                            y = y0;
                            switch (ch[j]){
                                case '.':
                                    home = new Home(x, y);
                                    homes.add(home);
                                    break;
                                case '@':
                                    player = new Player(x, y);
                                    break;
                                case 'X':
                                    wall = new Wall(x, y);
                                    walls.add(wall);
                                    break;
                                case '*':
                                    box = new Box(x, y);
                                    boxes.add(box);
                                    break;
                                case'&':
                                    box = new Box(x, y);
                                    home = new Home(x, y);
                                    boxes.add(box);
                                    homes.add(home);
                                    break;
                            }
                            x0 = x;
                        }
                        x0 = Model.FIELD_CELL_SIZE/2;
                        y0 += Model.FIELD_CELL_SIZE;
                    }
                    GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
                    return gameObjects;
                }
            }
        }
        catch(IOException e){

        }
        return null;
    }
}
