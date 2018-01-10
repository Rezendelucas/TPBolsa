package com.mygdx.game.ScreensPack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.ModelsPack.Assets;
import com.mygdx.game.UtilsPack.Constants;

import java.awt.Font;

public class GuiScreen extends ScreenAdapter {
    private static final String TAG = GuiScreen.class.getName();

    public static final Skin skin =  new Skin(Gdx.files.internal(Constants.UISKIN));
    private Stage stage = new Stage();
    private final List<String> grimoire;
    private static final List<String> spell = new List<String>(skin);;
    private final List<String> quests;
    private final Table table;
    private final Label lblGrimoire;
    private final Label lblSpell;
    //private final Label lblQuests;
    private final TextButton btnStart;
    private final TextButton btnPaused;
    private final TextButton btnReset;
    private final TextButton btnMapScreen;
    private static boolean start = false;
    private boolean debug = true;
    private Game game;

    public GuiScreen(){
        stage.setDebugAll(debug);
        Gdx.input.setInputProcessor(stage);

        grimoire = new List<String>(skin);
        grimoire.setItems("Avancar - 10 mana","Virar a Direita - 10 mana","Virar a Esquerda - 10 mana","Atear Fogo - 50 mana");
        //spell = new List<Object>(skin);
        spell.setItems("Avancar - 10 mana","Avancar - 10 mana","Avancar - 10 mana","Avancar - 10 mana","Atear Fogo - 50 mana");
        quests = new List<String>(skin);
        //quests.setItems("nenhuma quest registrada!!!");


        table = new Table(skin);
        //table.setFillParent(true);
        table.setPosition(10,10);
        table.setSize(1260,500);
        stage.addActor(table);
        //table.defaults();

        lblGrimoire = new Label("Grimorio", skin);
        lblSpell = new Label("Spell",skin);
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
        btnMapScreen.setPosition(400,660);
        btnMapScreen.setSize(150,50);
        stage.addActor(btnMapScreen);

        btnStart = new TextButton("START", skin);
        btnStart.setPosition(560,660);
        btnStart.setSize(150,50);
        stage.addActor(btnStart);

        btnPaused = new TextButton("PAUSE", skin);
        btnPaused.setPosition(560,600);
        btnPaused.setSize(150,50);
        stage.addActor(btnPaused);

        btnReset = new TextButton("RESET", skin);
        btnReset.setPosition(560,540);
        btnReset.setSize(150,50);
        stage.addActor(btnReset);

        btnMapScreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LevelHelper.getInstance().goToMap();
            }
        });

        btnStart.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                start = true;
                //btnStart.setDisabled(true);  pensar em uma forma de manter desativado enquanto estiver em espera
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
    public static List<String> pullComands() {
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



