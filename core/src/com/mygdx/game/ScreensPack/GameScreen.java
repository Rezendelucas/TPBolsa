package com.mygdx.game.ScreensPack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.mygdx.game.WorldController;
import com.mygdx.game.WorldRenderer;

/**
 * Created by LucasRezende on 03/08/2017.
 */
public class GameScreen extends AbstractGameScreen {

    private static final String TAG = GameScreen.class.getName();

    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private boolean paused;

    public GameScreen(Game game){
        super(game);
    }

    @Override
    public void show() {
        worldController = new WorldController(game);
        worldRenderer = new WorldRenderer(worldController);
       // Gdx.input.setCatchBackKey(true);  ///????
    }

    @Override
    public void render(float deltaTime) {
        if (!paused) {
            worldController.update(deltaTime);
        }
        Gdx.gl.glClearColor(0,0,0,0);//0x64 / 255.0f, 0x95 / 255.0f,0xed / 255.0f, 0xff / 255.0f);
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
        //Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void dispose() {

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
