package com.mygdx.game.ModelsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.mygdx.game.UtilsPack.Constants;

/**
 * Created by LucasRezende on 03/08/2017.
 */
public class AssetFonts {

    public final BitmapFont defaultSmall;
    public final BitmapFont defaultNormal;
    public final BitmapFont defaultBig;

    public AssetFonts() {
        // create three fonts using Libgdx's 15px bitmap font
        defaultSmall = new BitmapFont(Gdx.files.internal(Constants.FONT_SOURCE), true);
        defaultNormal = new BitmapFont(Gdx.files.internal(Constants.FONT_SOURCE), true);
        defaultBig = new BitmapFont(Gdx.files.internal(Constants.FONT_SOURCE), true);
        // set font sizes
        //       defaultSmall.setScale(0.75f);
        //      defaultNormal.setScale(1.0f);
        //       defaultBig.setScale(2.0f);
        // enable linear texture filtering for smooth fonts
        defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //defaultSmall.setScale(Scale.scale(0.75,0.75f).);
        defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }
}