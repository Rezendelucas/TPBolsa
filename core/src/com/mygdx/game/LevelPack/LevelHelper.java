package com.mygdx.game.LevelPack;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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
    private static int NUMERODEFASES = 4;

    private Player player;
    private Torch tocha;
    private LevelBuild faseAtual;
    private Array<Level> fases;
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

    public void carregarFases(){
        fases = new Array<Level>();
        for(int i = 0; i < NUMERODEFASES; i++){
            Level newLevel = new Level(getCurrent_Level(i),i);
            float line = i/4;
            if(line <= 1){
                Vector2 vec = new Vector2(230 * (i + 1), 450);
                newLevel.setCordenadasMapaDeProgresso(vec);
            }else if(line <= 2){
                Vector2 vec = new Vector2(230 * (i + 1), 350);
                newLevel.setCordenadasMapaDeProgresso(vec);
            }else if(line <= 3){
                Vector2 vec = new Vector2(230 * (i + 1), 250);
                newLevel.setCordenadasMapaDeProgresso(vec);
            }
            fases.add(newLevel);
        }
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
        for(Level lvl: fases) {
            if(lvl.getNumeroLevel()==getLevelAtual()){
                if (lvl.getMapa()[x + 5][y + 7] == livre ||  lvl.getMapa()[x+5][y+7] == spawn )
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public boolean getObjectInCoordinates(int x, int y, int object){
        for(Level lvl: fases) {
            if(lvl.getNumeroLevel()==getLevelAtual()){
                if (lvl.getMapa()[x + 5][y + 7] == object )
                     return true;
                else
                     return false;
            }
        }
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

    public void goToMap(Boolean status) { goToMap = status;}

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

    public LevelBuild getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(LevelBuild faseAtual) {
        this.faseAtual = faseAtual;
    }

    public Level getFases(int level) {
        for(Level lvl: fases) {
            if (lvl.getNumeroLevel() == level) {
                return lvl;
            }
        }
        return null;
    }

    public Array<Level> getFases(){
        return fases;
    }


}
