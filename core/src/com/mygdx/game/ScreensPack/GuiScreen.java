package com.mygdx.game.ScreensPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import com.mygdx.game.UtilsPack.Constants;

public class GuiScreen extends ScreenAdapter {
    private static final String TAG = GuiScreen.class.getName();

    private static final Skin skin =  new Skin(Gdx.files.internal(Constants.UISKIN));
    private Stage stage = new Stage();
    private final List<Object> grimoire;
    private static final List<Object> spell = new List<Object>(skin);;
    private final List<String> quests;
    private final Table table;
    private final Label lblGrimoire;
    private final Label lblSpell;
    private final Label lblQuests;
    private final TextButton btnStart;
    private final TextButton btnPaused;
    private final TextButton btnReset;
    private static boolean start = false;
    private  boolean debug = false;

    public GuiScreen(){
        stage.setDebugAll(debug);
        Gdx.input.setInputProcessor(stage);

        grimoire = new List<Object>(skin);
        grimoire.setItems("Avancar","Virar a Direita","Virar a Esquerda");
        //spell = new List<Object>(skin);
        spell.setItems();
        quests = new List<String>(skin);
        quests.setItems("nenhuma quest registrada!!!");

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        table.defaults();
        lblGrimoire = new Label("Grimorio", skin);
        lblSpell = new Label("Spell",skin);
        lblQuests = new Label("Quests",skin);

        table.add(lblQuests).fill().row();
        table.add(quests).expand().fill().row();

        table.add(lblGrimoire).width(150).left().fill();
        table.add(lblSpell).width(150).right().fill().row();

        table.add(grimoire).width(150).left().expand().fill();
        table.add(spell).width(150).right().expand().fill().row();


        btnStart = new TextButton("START", skin);
        btnStart.setSize(150,50);
        btnStart.setPosition(1000,620);
        stage.addActor(btnStart);

        btnPaused = new TextButton("PAUSE", skin);
        btnPaused.setSize(150,50);
        btnPaused.setPosition(1000,530);
        stage.addActor(btnPaused);

        btnReset = new TextButton("RESET", skin);
        btnReset.setSize(150,50);
        btnReset.setPosition(840,620);
        stage.addActor(btnReset);

        btnStart.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                start = true;
                System.out.print("Play \n");
            }
        });

        btnPaused.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                start = false;
                System.out.print("Pause \n");
            }
        });

        btnReset.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                start = false;
                spell.clearItems();
                System.out.print("comandos Resetados \n");
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
                payload.setDragActor(new Label((String)item, skin));
                payload.setInvalidDragActor(new Label(item + " (\"voce nao tem mana suficiente!\")", skin));
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
                spell.getItems().add((String) payload.getObject());
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
                payload1.setDragActor(new Label((String)item, skin));
                return payload1;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                if(target == null)
                    spell.getItems().add((String) payload.getObject()); // caso o objeto seja solto em area invalida retorna para lista de inventario
            }
        });
        dnd2.addTarget(new DragAndDrop.Target(grimoire) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return !"Begin".equals(payload.getObject());
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                //spell.getItems().removeIndex(spell.getSelectedIndex());
            }
        });


    }
    public static List<Object> pullComands() {
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



