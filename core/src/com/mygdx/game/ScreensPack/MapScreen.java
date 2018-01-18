package com.mygdx.game.ScreensPack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.LevelPack.GroundHelper;
import com.mygdx.game.LevelPack.Level;
import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.LevelPack.WallHelper;
import com.mygdx.game.UtilsPack.Constants;
import com.mygdx.game.WorldController;
import com.mygdx.game.WorldRenderer;

/**
 * Created by LucasRezende on 09/01/2018.
 */

public class MapScreen extends AbstractGameScreen {

    private static final String TAG = GameScreen.class.getName();

    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private static final Skin skin =  new Skin(Gdx.files.internal(Constants.UISKIN));
    private TextButton startButton;
    private TextButton previousButton;
    private TextButton nextButton;
    private Stage mapStage = new Stage();
    private Boolean paused = false;
    private Boolean debug = true;


    public MapScreen(Game game){
        super(game);
    }


    @Override
    public void show() {
        System.out.print("Mapa de progresso \n");
        worldController = new WorldController(game, skin);
        worldRenderer = new WorldRenderer(worldController);

        startButton = new TextButton("Start",skin);
        nextButton = new TextButton("Next", skin);
        previousButton = new TextButton("Previous", skin);

        previousButton.setSize(100,40);
        previousButton.setPosition(40,20);

        startButton.setSize(100,40);
        startButton.setPosition(600,20);

        nextButton.setSize(100,40);
        nextButton.setPosition(1150,20);

        mapStage.addActor(startButton);
        mapStage.addActor(nextButton);
        mapStage.addActor(previousButton);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.print("zerando dados para o mapa \n");
                WallHelper.getInstance().dropWall();
                GroundHelper.getInstance().dropGround();
                game.setScreen(new GameScreen(game));
            }
        });

        previousButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LevelHelper.getInstance().previousLevel();
            }
        });

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LevelHelper.getInstance().nextLevel();
            }
        });

        mapStage.setDebugAll(debug);
        Gdx.input.setInputProcessor(mapStage);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(1.0f ,0.5f,0.0f,0.5f);//0x64 / 255.0f, 0x95 / 255.0f,0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!paused){
            worldRenderer.renderMap(deltaTime);
            mapStage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width,height);
    }


    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        game.dispose();

    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume () {
        super.resume();
        paused = false;
    }

}
