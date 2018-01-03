package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.LevelPack.GroundHelper;
import com.mygdx.game.LevelPack.Level;
import com.mygdx.game.LevelPack.WallHelper;
import com.mygdx.game.ScreensPack.MenuScreen;

import sun.rmi.runtime.Log;


/**
 * Created by LucasRezende on 03/08/2017.
 */

public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    private OrthographicCamera camera;
    private Level currenteLevel;
    private int score;
    private Game game;
    private Skin skin;
    private int level = 0;

    public WorldController(Game game, Skin skin){
        this.game = game;
        this.skin = skin;
        init();
    }

    private void init(){
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        initLevel();
    }

    private void initLevel(){
        score = 0;
        currenteLevel = new Level(level);
    }

    ////////////////////////////////////// Update //////////////////////////////////////////////

    public void update(float delta){
        if(currenteLevel.isCompleteObjective()){
            level++;
            WallHelper.getInstance().dropWall();
            GroundHelper.getInstance().dropGround();
            currenteLevel = new Level(level);
        }
        currenteLevel.update(delta);
        camera.update();
    }

    //////////////////////////////////////  Classes de debug  /////////////////////////////////////

    private void backToMenu () {
        // retornar para o menu screen
        game.setScreen(new MenuScreen(game));
    }

    public boolean keyUp(int keycode){//reseta o world
        if(keycode == Input.Keys.R){
            init();
            Gdx.app.debug(TAG,"Game world resetado");
        }else if (keycode == Input.Keys.ESCAPE) {// back to menu
            backToMenu();
        }
        return false;
    }

    public Level getCurrenteLevel() {
        return currenteLevel;
    }

    public int getScore() {
        return score;
    }
}

