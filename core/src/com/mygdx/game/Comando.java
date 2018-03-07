package com.mygdx.game;


public class Comando {
    private String comando;
    private int custo;
    private int repetiçao;


    public Comando(String comando,int custo) {
        this.comando = comando;
        this.custo = custo;

    }

    public Comando(String comando, int custo, int repetiçao) {
        this.comando = comando;
        this.custo = custo;
        this.repetiçao = repetiçao;
    }

    public int getRepetiçao() {
        return repetiçao;
    }

    public void setRepetiçao(int repetiçao) {
        this.repetiçao = repetiçao;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        if(this.comando.equals("Repetir")) {
            return this.comando + " (" + this.repetiçao + ") " + this.custo + " Mana ";
        }else{
            return this.comando + " " + this.custo + " Mana ";
        }
    }
}
