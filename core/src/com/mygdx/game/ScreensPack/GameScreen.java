package com.mygdx.game.ScreensPack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.UtilsPack.Constants;
import com.mygdx.game.WorldController;
import com.mygdx.game.WorldRenderer;

/**
 * Created by LucasRezende on 03/08/2017.
 */
public class GameScreen extends AbstractGameScreen {

    private static final String TAG = GameScreen.class.getName();

    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private static final Skin skin =  new Skin(Gdx.files.internal(Constants.UISKIN));
    private boolean paused;

    public GameScreen(Game game){
        super(game);
    }

    @Override
    public void show() {
        worldController = new WorldController(game, skin);
        worldRenderer = new WorldRenderer(worldController);
    }

    @Override
    public void render(float deltaTime) {
        if (!paused) {
            worldController.update(deltaTime);
        }
        Gdx.gl.glClearColor(0.5F,0.5F,0.5F,0);//0x64 / 255.0f, 0x95 / 255.0f,0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }


    @Override
    public void hide() {
        worldRenderer.dispose();
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
