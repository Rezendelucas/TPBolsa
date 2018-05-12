package com.mygdx.game.ScreensPack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import com.mygdx.game.Comando;
import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.UtilsPack.Sintaxe;
import com.mygdx.game.UtilsPack.Constants;



public class GuiScreen extends ScreenAdapter {
    private static final String TAG = GuiScreen.class.getName();

    public static final Skin skin =  new Skin(Gdx.files.internal(Constants.UISKIN));
    private Sintaxe analiseSintaxe = new Sintaxe();
    private Stage stage = new Stage();
    private final List<Comando> grimoire;
    private static final List<Comando> spell  = new List<Comando>(skin);
    private static final List<Comando> spell2 = new List<Comando>(skin);
    private static final List<Comando> spell3 = new List<Comando>(skin);
    private final List<String> quests;
    private final Table table;
    private final Label lblGrimoire;
    private final Label lblSpell;
    //private final Label lblQuests;
    private final TextButton btnStart;
    private final TextButton btnPaused;
    private final TextButton btnResetComandos;
    private final TextButton btnResetFase;
    private final TextButton btnMapScreen;
    private final TextButton btnAbaA;
    private final TextButton btnAbaB;
    private final TextButton btnAbaC;
    private int repetiçao = 0;
    private int tempcusto = 0;
    private String tempcomando = "";
    private static boolean start = false;
    private boolean debug = false;
    private Game game;

    public GuiScreen(){
        stage.setDebugAll(debug);
        Gdx.input.setInputProcessor(stage);

        grimoire = new List<Comando>(skin);
        grimoire.setItems(new Comando("Avancar",10),new Comando("Virar a Direita",10),new Comando("Virar a Esquerda", 10),new Comando("Atear Fogo", 50),new Comando("Abrir Laco",20 ),new Comando("Fechar Laco",20 ),new Comando("Repetir",0));
        //grimoire.setItems(new Comando("Avancar",10),new Comando("Virar a Direita",10),new Comando("Virar a Esquerda", 10),new Comando("Atear Fogo", 50),new Comando("Abrir Laco",20 ),new Comando("Fechar Laco",20 ),new Comando("Repetir",0));
        //spell = new List<Object>(skin);
        spell.setItems(new Comando("Avancar", 10),new Comando("Avancar", 10),new Comando("Avancar",10),new Comando("Avancar", 10),new Comando("Atear Fogo", 50));


        quests = new List<String>(skin);
        //quests.setItems("nenhuma quest registrada!!!");


        table = new Table(skin);
        //table.setFillParent(true);
        table.setPosition(10,10);
        table.setSize(1260,500);
        stage.addActor(table);
        //table.defaults();

        lblGrimoire = new Label("Comandos", skin);
        lblSpell = new Label("Acoes",skin);
        //lblQuests = new Label("Quests",skin);
;

        lblGrimoire.setFontScale(2,2);
        lblGrimoire.setAlignment(20,2);
        lblSpell.setFontScale(2,2);
        lblSpell.setAlignment(20,2);

        //table.add(lblQuests).width(300).left().fill().row();
        //table.add(quests).width(300).height(100).top().left().expand().fill().row();

        table.add(lblGrimoire).width(350);
        table.add().width(560).fill();
        table.add(lblSpell).width(350).row();


        table.add(grimoire).width(350).expand().top().fill();
        table.add().width(560).fill();
        table.add(spell).width(350).top().expand().fill().row();


        btnMapScreen = new TextButton("MAP", skin);
        btnMapScreen.setPosition(400,600);
        btnMapScreen.setSize(150,50);
        stage.addActor(btnMapScreen);

        btnStart = new TextButton("START", skin);
        btnStart.setPosition(560,660);
        btnStart.setSize(150,50);
        stage.addActor(btnStart);

        btnPaused = new TextButton("PAUSE", skin);
        btnPaused.setPosition(720,600);
        btnPaused.setSize(150,50);
        stage.addActor(btnPaused);

        btnResetComandos = new TextButton("RESET", skin);
        btnResetComandos.setPosition(720,660);
        btnResetComandos.setSize(150,50);
        stage.addActor(btnResetComandos);

        btnResetFase = new TextButton("RESTART", skin);
        btnResetFase.setPosition(400,660);
        btnResetFase.setSize(150,50);
        stage.addActor(btnResetFase);

        btnAbaA = new TextButton("A", skin);
        btnAbaA.setPosition(920,520);
        btnAbaA.setSize(50,20);
        stage.addActor(btnAbaA);

        btnAbaB = new TextButton("B", skin);
        btnAbaB.setPosition(980,520);
        btnAbaB.setSize(50,20);
        stage.addActor(btnAbaB);

        btnAbaC = new TextButton("C", skin);
        btnAbaC.setPosition(1040,520);
        btnAbaC.setSize(50,20);
        stage.addActor(btnAbaC);


        btnAbaA.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spell.setVisible(true);
                spell2.setVisible(false);
                spell3.setVisible(false);
            }
        });


        btnAbaB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spell.setVisible(false);
                spell2.setVisible(true);
                spell3.setVisible(false);
            }
        });

        btnAbaA.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spell.setVisible(false);
                spell2.setVisible(false);
                spell3.setVisible(true);
            }
        });



        btnMapScreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LevelHelper.getInstance().goToMap();
            }
        });

        btnStart.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                if(analiseSintaxe.analiseSintaxeComandos(spell)) {
                    start = true;
                    //btnStart.setDisabled(true);  pensar em uma forma de manter desativado enquanto estiver em espera
                    System.out.print("Play \n");
                }else{
                    System.out.print("Erro ao compilar comandos!!! \n");
                }
            }
        });

        btnPaused.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                start = false;
                System.out.print("Pause \n");
            }
        });

        btnResetComandos.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                start = false;
                spell.clearItems();
                System.out.print("comandos Resetados \n");
            }
        });

        btnResetFase.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LevelHelper.getInstance().getFaseAtual().initLevel();
                System.out.print("fase Resetadas \n");
            }
        });




        /////////mecanismo de arrastar e soltar/////////


        //-------------------Grimoire para spell-----------------------//

        DragAndDrop dnd = new DragAndDrop(); //cria o ator de arrastar
        dnd.addSource(new DragAndDrop.Source(grimoire) {  //a origem de onde e possivel arrastar
            final DragAndDrop.Payload payload = new DragAndDrop.Payload();  // cria o objeto carga para conter o item arrastado
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                Object item = grimoire.getSelected(); // pega o item escolhido do inventario
                payload.setObject(item);  //   add no objeto de carga
                //grimoire.getItems().removeIndex(Grimoire.getSelectedIndex());  // remove o objeto da lista de inventario, isto e desnecessario para nosso projeto
                payload.setDragActor(new Label(item.toString(), skin));
                payload.setInvalidDragActor(new Label(item + " (\"voce nao tem requisito\")", skin));
                payload.setValidDragActor(new Label(item + " (\"Adcionar spell\")", skin));

                return payload;
            }



            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                // if(target == null)
                //Grimoire.getItems().add((String) payload.getObject()); // caso o objeto seja solto em area invalida retorna para lista de inventario
            }
        });
        //criaçao de um alvo para o ator de arrastar
        dnd.addTarget(new DragAndDrop.Target(spell) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
              return !"Touch of Death".equals(payload.getObject());
            }



            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Comando comando = (Comando) payload.getObject();
                if(((Comando) payload.getObject()).getComando().equals("Repetir")){
                    openWindowForRepeat(comando.getComando(),comando.getCusto());
                }else{
                    spell.getItems().add(new Comando(comando.getComando(),comando.getCusto()));
                }

            }
        });

        //-------------------spell para grimoire------------------------//

        DragAndDrop dnd2 = new DragAndDrop();
        dnd2.addSource(new DragAndDrop.Source(spell) {  //a origem de onde e possivel arrastar
            final DragAndDrop.Payload payload1 = new DragAndDrop.Payload();  // cria o objeto carga para conter o item arrastado
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                Object item = spell.getSelected(); // pega o item escolhido do inventario
                payload1.setObject(item);  //   add no objeto de carga
                spell.getItems().removeIndex(spell.getSelectedIndex());  // remove o objeto da lista de inventario, isto e desnecessario para nosso projeto
                Comando comando = (Comando) payload1.getObject();
                payload1.setDragActor(new Label(comando.getComando(), skin));
                return payload1;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                if(target == null)
                    spell.getItems().add((Comando) payload.getObject()); // caso o objeto seja solto em area invalida retorna para lista de inventario
            }
        });
        dnd2.addTarget(new DragAndDrop.Target(grimoire) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return !"Begin".equals(payload.getObject());
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                if(payload.getObject().equals("Laco - 20 mana")) {
                }
                //spell.getItems().removeIndex(spell.getSelectedIndex());
            }
        });

    }

    private void openWindowForRepeat(String comando,int custo) {
        final Dialog entrada = new Dialog("Digite um valor",skin);
        final Table tbl = new Table(skin);
        final TextField txt = new TextField("",skin);
        final TextButton btnOk = new TextButton("Ok",skin);

        tempcomando = comando;
        tempcusto = custo;

        btnOk.align(1);
        btnOk.setSize(50,50);

        tbl.add(txt).top().expand().fill().row();
        tbl.add(btnOk).top().expand().fill().row();

        entrada.add(tbl);

        stage.addActor(entrada);

        btnOk.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                repetiçao = Integer.parseInt(txt.getText());
                System.out.print("repetiçao setada para:  " + repetiçao + "\n");
                entrada.hide();
                spell.getItems().add(new Comando(tempcomando,tempcusto,repetiçao));
            }
        });

    }

    public static List<Comando> pullComands() {
        return spell;
    }

    public static boolean isStart(){return start;}

    public static void setStart(){start = false;}

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}



