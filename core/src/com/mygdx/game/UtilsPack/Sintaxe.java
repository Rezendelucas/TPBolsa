package com.mygdx.game.UtilsPack;


import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.Comando;


/**
 * Created by LucasRezende on 19/02/2018.
 */

public class Sintaxe{
     private List<Comando> lista;
     private Comando anterior = null;
     private int somaZero = 0;//controle das situaçoes de laços, se nao final nao estiver em zero siginifca algum laço sem fechar;

    public boolean analiseSintaxeComandos(List<Comando> list){
        this.lista = list;
        this.anterior = new Comando("inicio",0);
        for(int i = 0; i < lista.getItems().size; i++){

            if(anterior.getComando().equals("Abrir Laco") && !lista.getItems().get(i).getComando().equals("Repetir")){
                System.out.print("Erro: Comando 'abrir laço' sem comando 'repetir' na sequencia \n");
                return false;
            }
            if(lista.getItems().get(i).getComando().equals("Repetir") && !anterior.getComando().equals("Abrir Laco")){
                System.out.print("Erro: Comando 'repetir' nao se refere ao nenhum laço \n");
                 return false;
            }
            if(lista.getItems().get(i).getComando().equals("Fechar Laco") && somaZero <= 0){
                System.out.print("Erro: tentando fechar um laço que nao foi aberto \n");
                return false;
            }
            if(lista.getItems().get(i).getComando().equals("Abrir Laco")){
                somaZero++;
            }
            if(lista.getItems().get(i).getComando().equals("Fechar Laco")){
                somaZero--;
            }
            this.anterior = lista.getItems().get(i);
        }
        if(somaZero == 0) {
            System.out.print("Tudo OK comandos compilados \n");
            return true;
        }else if(somaZero > 1) {
                System.out.print("Erro: laço aberto \n");
                somaZero = 0;
                return false;
            }else{
                System.out.print("Erro: laço ao fechar laço \n");
                somaZero = 0;
                return false;
            }

    }

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }
}
