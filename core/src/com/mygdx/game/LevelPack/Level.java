package com.mygdx.game.LevelPack;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by LucasRezende on 10/01/2018.
 */

public class Level {

    private static final String TAG = Level.class.getName();

    private int numeroLevel;
    private int[][] mapa;
    private Vector2 cordenadasMapaDeProgresso;

    public Level(int[][] current_level, int level) {
        this.mapa = current_level;
        this.numeroLevel = level;
    }

    public int getNumeroLevel() {
        return numeroLevel;
    }

    public void setNumeroLevel(int numeroLevel) {
        this.numeroLevel = numeroLevel;
    }

    public int[][] getMapa() {
        return mapa;
    }

    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }

    public Vector2 getCordenadasMapaDeProgresso() {
        return cordenadasMapaDeProgresso;
    }

    public void setCordenadasMapaDeProgresso(Vector2 cordenadasMapaDeProgresso) {
        this.cordenadasMapaDeProgresso = cordenadasMapaDeProgresso;
    }

}
