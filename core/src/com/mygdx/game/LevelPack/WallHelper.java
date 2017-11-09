package com.mygdx.game.LevelPack;

import com.mygdx.game.ModelsPack.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucasRezende on 27/10/2017.
 */

public class WallHelper {
    private static WallHelper instance = null;
    private List<Wall> walls = new ArrayList<Wall>();


    public static WallHelper getInstance() {
        if (instance == null) {
            instance = new WallHelper();
        }
        return instance;
    }

    public void addWall(Wall wall){
        walls.add(wall);
    }

    public boolean searchWall(float x, float y){
        for(Wall wall: walls){
            if(wall.getPosition().x == x && wall.getPosition().y == y){
                return true;
            }
        }
        return false;
    }

    public Wall getWall(int i){
        return walls.get(i);
    }

    public List<Wall> getWalls(){
        return  walls;
    }
}
