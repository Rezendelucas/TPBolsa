package com.mygdx.game;

/**
 * Created by LucasRezende on 01/02/2018.
 */

public class Laço {
    private int indexRetorno;
    private int numRepetiçoes;

    public Laço(int indexRetorno) {
        this.indexRetorno = indexRetorno;
    }

    public int getIndexRetorno() {
        return indexRetorno;
    }

    public void setIndexRetorno(int indexRetorno) {
        this.indexRetorno = indexRetorno;
    }

    public int getNumRepetiçoes() {
        return numRepetiçoes;
    }

    public void setNumRepetiçoes(int numRepetiçoes) {
        this.numRepetiçoes = numRepetiçoes;
    }
}
