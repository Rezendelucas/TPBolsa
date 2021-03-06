package com.mygdx.game.ModelsPack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Laço;
import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.UtilsPack.Constants;
import com.mygdx.game.WorldController;

import java.util.ArrayList;
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

    private boolean fecharLaço = false;
    private int lastComando; // salva o ultimo index do ultimo comando execultado;
    private int repetiçoes = 0;
    private ArrayList<Laço> pilhaDeLacos = new ArrayList<Laço>();

    private int mana = 1000;   //custo de uso de açoes
    private int direçao = 0;    // direçao em que o player esta olhando
    private boolean tochaDisponivel = false;   // a tocha esta disponivel pra ser usada
    private float movePlayerX = 1;    // numero de casas por movimento de avançar
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

    public void comandos(int comando) {//modificar posteriormente para capturar o custo de mana direto do comando ao inves de inserçao manual
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
                            if(getMana() < 20){break;}
                            abrirLaço();
                            setMana(20);
                            break;
                        case 7:
                            if(getMana() < 20){break;}
                            fecharLaço();
                            setMana(20);
                            break;
                        case 8:
                            repetiçoes();
                            break;
                        default:
                            //nothing
                            break;
                    }
    }

    private void repetiçoes() {
        System.out.print("Repetiçoes atualizadas!!!! \n");
        pilhaDeLacos.get(pilhaDeLacos.size()-1).setNumRepetiçoes(repetiçoes);//temporariamente 1 depois recebe uma var q será setado mais tarde
    }

    private void abrirLaço() {
        System.out.print("salvar comando: " + lastComando +"\n");
        empilhaNovoIndice(lastComando);
    }

    private void empilhaNovoIndice(int indice) {
        Laço lc = new Laço(indice + 1);
        System.out.print("empilhado o indice de retorno: " + (indice + 1) +"\n");
        pilhaDeLacos.add(lc);
    }

    private void fecharLaço(){
        fecharLaço = true;
        if(pilhaDeLacos.get(pilhaDeLacos.size()-1).getNumRepetiçoes() == 1){
            pilhaDeLacos.remove(pilhaDeLacos.size()-1);
            fecharLaço = false;
            System.out.print("Fim do laço!!! \n");
        }else{
            int temp = pilhaDeLacos.get(pilhaDeLacos.size()-1).getNumRepetiçoes();
            temp--;
            pilhaDeLacos.get(pilhaDeLacos.size()-1).setNumRepetiçoes(temp);
            System.out.print("repetiçoes restantes: "  +  pilhaDeLacos.get(pilhaDeLacos.size()-1).getNumRepetiçoes() + "\n");
            System.out.print("retornando para o indice : " + pilhaDeLacos.get(pilhaDeLacos.size()-1).getIndexRetorno() + "\n");
        }
    }

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
            put("Avancar",1);
            put("Virar a Direita",2);
            put("Virar a Esquerda",3);
            put("Golpe simples",4);
            put("Atear Fogo",5);
            put("Abrir Laco",6);
            put("Fechar Laco",7);
            put("Repetir",8);


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

    public int getLastComando() {
        return lastComando;
    }

    public void setLastComando(int lastComando) {
        this.lastComando = lastComando;
    }

    public ArrayList<Laço> getPilhaDeLacos() {
        return pilhaDeLacos;
    }

    public void setPilhaDeLacos(ArrayList pilhaDeLacos) {
        this.pilhaDeLacos = pilhaDeLacos;
    }

    public boolean isFecharLaço() {
        return fecharLaço;
    }

    public void setFecharLaço(boolean fecharLaço) {
        this.fecharLaço = fecharLaço;
    }

    public int getRepetiçoes() {
        return repetiçoes;
    }

    public void setRepetiçoes(int repetiçoes) {
        this.repetiçoes = repetiçoes;
    }
}
