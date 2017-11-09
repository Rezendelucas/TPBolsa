package com.mygdx.game.LevelPack;

import com.mygdx.game.ModelsPack.Player;
import com.mygdx.game.ModelsPack.Torch;

/**
 * Created by LucasRezende on 07/11/2017.
 */

public class LevelHelper {
    private static LevelHelper instance = null;
    private Player player;
    private Torch tocha;
    private int[][] Current_Level = new int[][]{
        {5, 1, 1, 1, 1, 1, 1, 1, 1, 6},
        {2, 9, 0, 0, 0, 0, 0, 0, 0, 3},
        {2, 0, 0, 0, 0, 0, 3, 0, 0, 3},
        {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
        {2, 0, 0, 3, 0, 0, 0, 0, 0, 3},
        {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
        {2, 0, 0, 0, 0, 3, 0, 0, 0, 3},
        {2, 0, 3, 0, 0, 0, 0, 3, 0, 3},
        {2, 0, 0, 0, 0, 0, 0, 0, 10, 3},
        {7, 4, 4, 4, 4, 4, 4, 4, 4, 8},
    };

    public static LevelHelper getInstance() {
        if (instance == null) {
            instance = new LevelHelper();
        }
        return instance;
    }

    public int[][] getCurrent_Level() {
        return Current_Level;
    }

    public boolean getObjectInCoordinates(int x, int y, int objeto){
        if(Current_Level[x+5][y+7] == objeto)
            return true;
        else
            return false;
    }

    public void setCurrent_Level(int[][] current_Level) {
        Current_Level = current_Level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Torch getTocha() {
        return tocha;
    }

    public void setTocha(Torch tocha) {
        this.tocha = tocha;
    }
}
