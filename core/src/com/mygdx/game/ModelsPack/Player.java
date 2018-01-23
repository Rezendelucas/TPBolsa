package com.mygdx.game.ModelsPack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.WorldController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LucasRezende on 12/09/2017.
 */

public class Player extends AbstractGameObject {

    private static final int DIREITA = 0;
    private static final int BAIXO = 1;
    private static final int ESQUERDA = 2;
    private static final int CIMA = 3;


    
    public static final String TAG = Player.class.getName();
    private TextureRegion regTexture;
    private boolean inicioDoLaço = false ;
    private boolean fimDoLaço = false;
    private boolean laçoAberto = false;
    private boolean laçoisfechado = true; // usado para interface saber quando o laço foi fechado
    private String currentComando = "Nenhum comando";
    private int mana = 1000;
    private int direçao = 0;
    private boolean tochaDisponivel = false;
    private float movePlayerX = 1;
    private float movePlayerY = 1;


    public Player() {
        init();
    }

    public void init () {
        dimension.set(1, 2);
        origin.set(dimension.x/2,dimension.y/2);
        rotation = 0;
        bounds.set(0, 0, dimension.x, dimension.y); //seta caixa de colisao
        regTexture = Assets.getInstance().jogador.idle_right;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        // Draw image
        reg = regTexture;
        //drawBatch.begin();
        batch.draw(reg.getTexture(),
                getPosition().x , getPosition().y,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                0/*rotation*/ ,
                reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(),
                false, false);
        //drawBatch.end();
    }

    public void comandos(int comando) {
                    switch (comando) {
                        case 1:
                            verificaDirecao();
                            if(getMana() < 10){break;}
                            movimento_Frente();
                            //verificaFrente();
                            setMana(10);
                            break;
                        case 2:
                            verificaDirecao();
                            if(getMana() < 10){break;}
                            girar_Direita();
                            //verificaFrente();
                            setMana(10);
                            break;
                        case 3:
                            verificaDirecao();
                            if(getMana() < 10){break;}
                            girar_Esquerda();
                            //verificaFrente();
                            setMana(10);
                            break;
                        case 4:
                            verificaDirecao();
                            //movimento_Ataque();
                            //render(drawBatch);
                            break;
                        case 5:
                            verificaDirecao();
                            if(getMana() < 50){break;}
                            acender_Fogo();
                            //verificaFrente();
                            setMana(50);
                            break;
                        case 6:
                            //abrir ciclo
                            if(getMana() < 20){break;}
                            adicionarLaço();
                            setMana(20);
                            break;
                        default:
                            //nothing
                            break;
                    }
    }

    /*private void fecharLaço() {
        System.out.print("Laco fechado \n");
        //verifica requisito, se cumpre fecha o laço
        if(true) {//temporariamente true
            fimDoLaço = LAÇO_CLOSED;
        }else{
            fimDoLaço = LAÇO_OPEN;
           // laçoAtivo = LAÇO_CLOSED;
        }
    }
*/
    private void adicionarLaço() {
        if (!inicioDoLaço && !laçoAberto) {
            System.out.print("Laco aberto \n");
            inicioDoLaço = true;
            laçoAberto = true;
        }else if(!fimDoLaço && laçoAberto){
            System.out.print("Verifica Laco \n");
            // "AKI" realiza a verificaçao do clausula de parada do laço
            if(verificaçaoLaço()){
                System.out.print("Laco fechado \n");
                fimDoLaço = true;
                laçoAberto = false;
            }else{
                System.out.print("Laco repetiçao \n");
                fimDoLaço = true;
                //laço continua aberto
            }
        }else{
            System.out.print("erro no Laco \n");
        }
    }

    private boolean verificaçaoLaço(){
        return false;
    } //temporario

    private void verificaFrente() {
    }

    private void verificaDirecao() {
        if(rotation == 0)
            direçao = DIREITA;
        else if(rotation == 90 || rotation == -270)
            direçao = CIMA;
        else if(rotation == -90 || rotation == 270)
            direçao = BAIXO;
        else if(rotation == 180 || rotation == -180)
            direçao= ESQUERDA;
    }


    public Map<String , Integer> Parse = new HashMap<String , Integer>(){
        {
            put("Avancar - 10 mana",1);
            put("Virar a Direita - 10 mana",2);
            put("Virar a Esquerda - 10 mana",3);
            put("Golpe simples - 10 mana",4);
            put("Atear Fogo - 50 mana",5);
            put("Laco - 20 mana",6);

        }
    };

    private void movimento_Frente() {
        if(rotation == 0 && LevelHelper.getInstance().getObjectInCoordinates((int)position.x + 1,(int)position.y)) {//direita
            position.set(getPosition().x + movePlayerX, getPosition().y +0);
        }else if(rotation == 90 || rotation == -270) {
            if(LevelHelper.getInstance().getObjectInCoordinates((int)position.x,(int)position.y + 1))//cima
                position.set(getPosition().x + 0, getPosition().y + movePlayerY);
        }else if(rotation == 180 || rotation == -180) {
            if(LevelHelper.getInstance().getObjectInCoordinates((int)position.x - 1,(int)position.y))//esquerda
                position.set(getPosition().x -  movePlayerY, getPosition().y + 0);
        }else if(rotation == -90 || rotation == 270) {
            if(LevelHelper.getInstance().getObjectInCoordinates((int)position.x,(int)position.y -1))//baixo
                position.set(getPosition().x + 0, getPosition().y - movePlayerX);
        }
        System.out.print("Movimento a frente! \n");

    }

    private void girar_Direita() {
        //position.set(getPosition().x + movePlayerX,getPosition().y + 0);
        setRotation(rotation-90);
        if(rotation == -360){
            setRotation(0);
        }
        jogadorSetTexture();
        System.out.print("giro a direita! \n");
    }

    private void girar_Esquerda() {
        //position.set(getPosition().x - movePlayerX,getPosition().y + 0);
        setRotation(getRotation()+90);
        if(rotation == 360){
            setRotation(0);
        }
        jogadorSetTexture();
        System.out.print("giro a esquerda! \n");
    }

    private void jogadorSetTexture() {
        if(rotation == 0)
             regTexture = Assets.getInstance().jogador.idle_right;
        else if(rotation == 90 || rotation == -270)
                regTexture = Assets.getInstance().jogador.idle_up;
        else if(rotation == -90 || rotation == 270)
                regTexture = Assets.getInstance().jogador.idle_donw;
        else if(rotation == 180 || rotation == -180)
            regTexture = Assets.getInstance().jogador.idle_left;
    }

    private void movimento_Ataque() {
        System.out.print("Ataque efetuado \n!");
    }//Nao implementado;

    private void acender_Fogo() {
        if(rotation == 0 && LevelHelper.getInstance().getObjectInCoordinates((int)position.x + 1,(int)position.y, 10)) {//procura uma pira de fogo a direita(cod 10)
            LevelHelper.getInstance().getTocha().switch_State();
            System.out.print("pira acessa a direita \n");
        }else if(rotation == 90 || rotation == -270) {
            if(LevelHelper.getInstance().getObjectInCoordinates((int)position.x, (int)position.y + 1, 10)) {//cima
                LevelHelper.getInstance().getTocha().switch_State();
                System.out.print("pira acessa a frente \n");
            }
        }else if(rotation == 180 || rotation == -180) {
            if(LevelHelper.getInstance().getObjectInCoordinates((int)position.x - 1, (int)position.y, 10)) {//esquerda
                LevelHelper.getInstance().getTocha().switch_State();
                System.out.print("pira acessa a esquerda \n");
            }
        }else if(rotation == -90 || rotation == 270) {
            if(LevelHelper.getInstance().getObjectInCoordinates((int)position.x, (int)position.y - 1, 10)) {//baixo
                LevelHelper.getInstance().getTocha().switch_State();
                System.out.print("pira acessa abaixo \n");
            }
        }else {
            System.out.print("nenhuma Pira encontrada \n");
            System.out.print("pira nao acessa \n");
        }
    }


    public int getMana() {
        return mana;
    }

    public void setMana(int valor){
        mana = mana - valor;
    }

    public boolean isinicioDoLaço() {
        return inicioDoLaço;
    }

    public void desativarinicioDoLaço() {
        this.inicioDoLaço = false;
    }

    public boolean isFimDoLaço() {
        return fimDoLaço;
    }

    public void desativaFimDoLaço() {
        this.fimDoLaço = false;
    }

    public boolean isLaçoAtivo() {
        return laçoAberto;
    }

    public void setLaçoAtivo(boolean laçoAtivo) {
        this.laçoAberto = laçoAtivo;
    }

    public boolean isLaçoisfechado() {
        return laçoisfechado;
    }

    public void setLaçoisfechado() {
        if(laçoisfechado){this.laçoisfechado = false;}
        else{this.laçoisfechado = true;}
    }

    public String getCurrentComando() {
        return currentComando;
    }

    public void setCurrentComando(String currentComando) {
        this.currentComando = currentComando;
    }
}
