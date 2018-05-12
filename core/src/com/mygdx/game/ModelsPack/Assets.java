package com.mygdx.game.ModelsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.UtilsPack.Constants;

/**
 * Created by LucasRezende on 03/08/2017.
 */

public class Assets implements Disposable, AssetErrorListener {

    public static final String Tag = Assets.class.getName();
    private static Assets instance = null;


    private AssetManager assetManager;
    public AssetFonts fontes;
    public AssetPlayer jogador;
    public AssetMap ground;
    public AssetMap wall;
    public AssetObject torch;
    public TextureAtlas atlas;
    public AssetIcon icons;
    public Texture paper;



    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    private Assets() {};

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();
        Gdx.app.debug(Tag, "# assets carregados: " + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames()) {
            Gdx.app.debug(Tag, "asset: " + a);
        }
        atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        for (Texture t : atlas.getTextures()) {
            //t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        ///Começa a carregas as texturas
        fontes = new AssetFonts();
        jogador = new AssetPlayer(atlas);
        ground = new  AssetMap(atlas);
        wall = new AssetMap(atlas);
        torch = new AssetObject(atlas);
        icons = new AssetIcon(atlas);
        paper = new Texture("assets_atlas/mapa.png");
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        fontes.defaultBig.dispose();
        fontes.defaultNormal.dispose();
        fontes.defaultSmall.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(Tag, "Asset nao foi carregada ", (Exception) throwable);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public class AssetPlayer {
        public final TextureRegion idle_up;
        public final TextureRegion idle_donw;
        public final TextureRegion idle_left;
        public final TextureRegion idle_right;

       // public final AtlasRegion walk;
       // public final AtlasRegion attack;
       // public final AtlasRegion die;

        public AssetPlayer(TextureAtlas atlas){
            idle_up =  new TextureRegion(atlas.findRegion("p_up"),0,0,32,57);
            idle_donw = new TextureRegion(atlas.findRegion("p_donw"),0,0,32,57);
            idle_left = new TextureRegion(atlas.findRegion("p_left"),4,0,28,57);
            idle_right = new TextureRegion(atlas.findRegion("p_right"),4,0,28,57);



          //  walk = atlas.findRegion("walk");
          //  attack = atlas.findRegion("attack");
          //  die = atlas.findRegion("die");
        }
    }

    public class AssetMap {
        public final TextureRegion ground_rock;
        public final TextureRegion tile_ground_rock;
        public final TextureRegion up_wall_rock;
        public final TextureRegion side_wall_rock;
        public final TextureRegion cornerLB_wall_rock;
        public final TextureRegion cornerLT_wall_rock;
        public final TextureRegion cornerRB_wall_rock;
        public final TextureRegion cornerRT_wall_rock;



        public AssetMap(TextureAtlas atlas) {
            ground_rock = new TextureRegion(atlas.findRegion("c_ground"),3,1,34,32);
            tile_ground_rock = new TextureRegion(atlas.findRegion("c_ground"),30,35,30,30);
            up_wall_rock = new TextureRegion(atlas.findRegion("cement"),20,0,16,20);
            side_wall_rock = new TextureRegion(atlas.findRegion("cement"),0,40,34,10);
            cornerLB_wall_rock = new TextureRegion(atlas.findRegion("cement"),0,44,34,20);
            cornerLT_wall_rock = new TextureRegion(atlas.findRegion("cement"),0,0,33,20);
            cornerRB_wall_rock = new TextureRegion(atlas.findRegion("cement"),38,44,32,20);
            cornerRT_wall_rock = new TextureRegion(atlas.findRegion("cement"),38,0,32,20);

        }
    }

    public class AssetObject {
        public final TextureRegion torch_off;
        public final TextureRegion torch_on;
        public AssetObject(TextureAtlas atlas) {
            torch_on  = new TextureRegion(atlas.findRegion("Tocha"),73,5,20,80);
            torch_off = new TextureRegion(atlas.findRegion("Tocha"),25,5,20,80);
        }
    }

    public class AssetIcon {
        public final TextureRegion iconMana;
        public final TextureRegion iconCura;
        public final TextureRegion iconTocha;
        public final TextureRegion iconEsculdo;
        public final TextureRegion iconSalto;
        public final TextureRegion iconManaoff;
        public final TextureRegion iconCuraoff;
        public final TextureRegion iconTochaoff;
        public final TextureRegion iconEsculdooff;
        public final TextureRegion iconSaltooff;
        public final TextureRegion iconeDungeonLevel;
        public final TextureRegion iconeStar;
        public final TextureRegion iconeStarOff;

        public AssetIcon(TextureAtlas atlas){
            iconMana = new TextureRegion(atlas.findRegion("spells"),191,0,49,45);
            iconCura = new TextureRegion(atlas.findRegion("spells"),0,0,49,45);
            iconTocha = new TextureRegion(atlas.findRegion("spells"),49,0,47,45);
            iconEsculdo = new TextureRegion(atlas.findRegion("spells"),98,0,47,45);
            iconSalto = new TextureRegion(atlas.findRegion("spells"),146,0,47,45);
            iconManaoff = new TextureRegion(atlas.findRegion("spellsOff"),191,0,49,45);
            iconCuraoff = new TextureRegion(atlas.findRegion("spellsOff"),0,0,49,45);
            iconTochaoff = new TextureRegion(atlas.findRegion("spellsOff"),49,0,47,45);
            iconEsculdooff = new TextureRegion(atlas.findRegion("spellsOff"),98,0,47,45);
            iconSaltooff = new TextureRegion(atlas.findRegion("spellsOff"),146,0,47,45);
            iconeDungeonLevel = new TextureRegion(atlas.findRegion("dungeon"),0,0,85,71);
            iconeStar = new TextureRegion(atlas.findRegion("star"),0,0,80,70);
            iconeStarOff = new TextureRegion(atlas.findRegion("star"),95,0,95,70);
        }
    }

    public class AssetFonts {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public AssetFonts () {
            // create three fonts using Libgdx's 15px bitmap font
            defaultSmall = new BitmapFont(Gdx.files.internal(Constants.FONT_SOURCE), true);
            defaultNormal = new BitmapFont(Gdx.files.internal(Constants.FONT_SOURCE), true);
            defaultBig = new BitmapFont(Gdx.files.internal(Constants.FONT_SOURCE), true);
            // enable linear texture filtering for smooth fonts
            defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            //defaultSmall.setScale(Scale.scale(0.75,0.75f).);
            defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        }

    }

}

