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
import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.LevelPack.WallHelper;
import com.mygdx.game.ScreensPack.MapScreen;
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
    private float cooldown = 3f;
    private boolean isCooldown = false;
    private boolean gameover = false;

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
        currenteLevel = new Level();
    }

    public void timeStop(float delta){
        if(cooldown > 0) {
            isCooldown = true;
            cooldown -= delta;
            if(cooldown <= 0){
                isCooldown = false;
                cooldown = 5;
            }
        }
    }

    ////////////////////////////////////// Update //////////////////////////////////////////////

    public void update(float delta){
        if(gameover){
            timeStop(delta);
            if(!isCooldown) {
                game.setScreen(new MenuScreen(game));
            }
        }
        else if(currenteLevel.isCompleteObjective()){//verifica caso vitoria avança proxima fase
            timeStop(delta);
            if(!isCooldown) {
                LevelHelper.getInstance().nextLevel();
                WallHelper.getInstance().dropWall();
                GroundHelper.getInstance().dropGround();
                game.setScreen(new MapScreen(game));
            }
        }
        else if(LevelHelper.getInstance().getPlayer().getMana() <= 0){//verifica mana e outras condiçoes de game over
            setGameover(true);
            //reseta mundo checar oq precisa ser resetado para recomeçar
        }else if(LevelHelper.getInstance().isGoToMap()) {
            game.setScreen(new MapScreen(game));
        }else{
            currenteLevel.update(delta);
            camera.update();
        }
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

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
}

