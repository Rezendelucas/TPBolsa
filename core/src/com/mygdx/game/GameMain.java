package com.mygdx.game;

/**
 * Created by LucasRezende on 03/08/2017.
 */

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

import com.mygdx.game.ModelsPack.Assets;
import com.mygdx.game.ScreensPack.MenuScreen;

public class GameMain extends Game {
    private static final String Tag = GameMain.class.getName();

    @Override
    public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Assets.getInstance().init(new AssetManager());
        setScreen(new MenuScreen(this));
    }

}
