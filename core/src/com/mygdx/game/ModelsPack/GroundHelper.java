package com.mygdx.game.ModelsPack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucasRezende on 27/10/2017.
 */

public class GroundHelper {
    private static GroundHelper instance = null;
    private List<Ground> grounds = new ArrayList<Ground>();


    public static GroundHelper getInstance() {
        if (instance == null) {
            instance = new GroundHelper();
        }
        return instance;
    }

    public void addGround(Ground ground){
        grounds.add(ground);
    }

    public Ground getGround(int i){
        return grounds.get(i);
    }

    public List<Ground> getGrounds(){
        return  grounds;
    }
}
