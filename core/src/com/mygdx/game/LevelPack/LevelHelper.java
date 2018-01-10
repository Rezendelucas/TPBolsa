package com.mygdx.game.LevelPack;

import com.mygdx.game.ModelsPack.Assets;
import com.mygdx.game.ModelsPack.Ground;
import com.mygdx.game.ModelsPack.Player;
import com.mygdx.game.ModelsPack.Torch;
import com.mygdx.game.ModelsPack.Wall;

/**
 * Created by LucasRezende on 07/11/2017.
 */

public class LevelHelper {
    private static LevelHelper instance = null;

    private static int spawn = 9;
    private static int livre = 0;

    private Player player;
    private Torch tocha;
    private int levelAtual = 0;
    private boolean estadoAtivo;
    private boolean goToMap = false;

    private int[][] level_00 = new int[][]{
        {5, 1, 1, 1, 1, 1, 1, 1, 1, 6},
        {2, 9, 0, 0, 0, 0, 0, 0, 0, 3},
        {2, 0, 0, 0, 0, 0, 3, 0, 0, 3},
        {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
        {2, 0, 0, 3, 0, 0, 0, 0, 0, 3},
        {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
        {2, 10, 0, 0, 0, 3, 0, 0, 0, 3},
        {2, 0, 3, 0, 0, 0, 0, 3, 0, 3},
        {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
        {7, 4, 4, 4, 4, 4, 4, 4, 4, 8},
    };
    private int[][] level_01 = new int[][]{
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 6},
            {2, 9, 0, 0, 0, 0, 0, 0, 0, 3},
            {2, 0, 3, 0, 0, 0, 0, 0, 0, 3},
            {2, 0, 3, 0, 3, 1, 1, 6, 0, 3},
            {2, 0, 0, 3, 3, 0, 0, 3, 0, 3},
            {2, 0, 0, 0, 3, 10, 0, 3, 0, 3},
            {2, 0, 0, 0, 3, 4, 0, 3, 0, 3},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {2, 3, 0, 0, 0, 0, 0, 0, 0, 3},
            {7, 4, 4, 4, 4, 4, 4, 4, 4, 8},
    };
    private int[][] level_02 = new int[][]{
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 6},
            {2, 0, 0, 0, 0, 0, 3, 0, 0, 3},
            {2, 3, 3, 0, 0, 0, 3, 0, 0, 3},
            {2, 0, 3, 0, 0, 0, 0, 0, 0, 3},
            {2, 0, 0, 0, 0, 0, 3,3, 0, 3},
            {2, 0, 0, 0, 0, 9, 3, 0, 0, 3},
            {2, 0, 3, 0, 3, 3, 3, 0, 3, 3},
            {2, 0, 0, 0, 3, 0, 3, 10, 0, 3},
            {2, 0, 0, 0, 0, 0, 3, 0, 0, 3},
            {7, 4, 4, 4, 4, 4, 4, 4, 4, 8},
    };
    private int[][] level_03 = new int[][]{
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 6},
            {2, 0, 0, 0, 3, 0, 0, 0, 0, 3},
            {2, 0, 0, 3, 3, 0, 0, 0, 0, 3},
            {2, 0, 3, 0, 0, 0, 3, 0, 0, 3},
            {2, 0, 0, 3, 0, 0, 3, 0, 0, 3},
            {2, 0, 10, 3, 3, 0, 3, 9, 0, 3},
            {2, 0, 0, 3, 0, 0, 3, 0, 0, 3},
            {2, 0, 0, 0, 0, 0, 3, 0, 0, 3},
            {2, 0, 3, 3, 3, 0, 0, 0, 0, 3},
            {7, 4, 4, 4, 4, 4, 4, 4, 4, 8},
    };

    public static LevelHelper getInstance() {
        if (instance == null) {
            instance = new LevelHelper();
        }
        return instance;
    }

    public int[][] getCurrent_Level(int level) {
        switch (level) {
            case 0:
                return level_00;
            case 1:
                return level_01;
            case 2:
                return level_02;
            case 3:
                return level_03;
            default:
                //nothing
                break;
        }
        return null;
    }

    public boolean getObjectInCoordinates(int x, int y){
        if(level_00[x+5][y+7] == livre ||  level_00[x+5][y+7] == spawn )
            return true;
        else
            return false;
    }

    public boolean getObjectInCoordinates(int x, int y, int object){
        if(level_00[x+5][y+7] == object )
            return true;
        else
            return false;
    }

    public void setCurrent_Level(int[][] current_Level) {
        level_00 = current_Level;
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

    public void setEstadoAtivo(boolean estado) {
        this.estadoAtivo = estado;
    }

    public boolean isEstadoAtivo() {
        return estadoAtivo;
    }

    public void goToMap() {
        goToMap = true;
    }

    public boolean isGoToMap() {
        return goToMap;
    }

    public int getLevelAtual() {
        return levelAtual;
    }

    public void setLevelAtual(int levelAtual) {
        this.levelAtual = levelAtual;
    }

    public void nextLevel(){
        this.levelAtual++;
    }

    public void previousLevel(){
        this.levelAtual--;
    }
}
