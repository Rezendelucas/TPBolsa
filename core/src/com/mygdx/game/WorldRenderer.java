package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;

import com.mygdx.game.LevelPack.Level;
import com.mygdx.game.LevelPack.LevelHelper;
import com.mygdx.game.ModelsPack.Assets;
import com.mygdx.game.ScreensPack.GuiScreen;
import com.mygdx.game.UtilsPack.Constants;

/**
 * Created by LucasRezende on 03/08/2017.
 */

public class WorldRenderer implements Disposable {

    private OrthographicCamera camera;
    private OrthographicCamera cameraGUI;
    private GuiScreen gui;
    private SpriteBatch batch;
    private WorldController worldController;
    private Box2DDebugRenderer b2debugRenderer;


    public WorldRenderer(WorldController worldController){
        this.worldController = worldController;
        init();
    }

    public void  init(){
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0,0,0);
        camera.update();
        cameraGUI = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        cameraGUI.position.set(0,0,0);
        cameraGUI.setToOrtho(true);
        cameraGUI.update();
        gui = new GuiScreen();
        b2debugRenderer = new Box2DDebugRenderer();
    }

    public void render(float delta){
        renderWorld(delta);
        renderGui(batch);
        renderControlGui(delta);
    }

    private void renderControlGui(float delta) {

        gui.render(delta);
    }

    private void renderWorld(float delta){
        //worldController.camera.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        worldController.getCurrenteLevel().render(batch);
        batch.end();
        //b2debugRenderer.render(worldController.b2world, camera.combined);

    }

    public void resize(int width , int height){
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height)*width;
        camera.update();
        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
        cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT/height)*width;
        cameraGUI.position.set(cameraGUI.viewportWidth/2, cameraGUI.viewportHeight/2,0);
        cameraGUI.update();
    }

    private void renderGui (SpriteBatch batch) {
        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();
        //renderGuiScore(batch);
        if(worldController.isGameover()){
            renderGameOverMensage(batch);
        }
        renderWallPapersCommands(batch);
        renderGuiMana(batch);
        //renderSkils(batch);
        renderGuiFpsCounter(batch);
        batch.end();
    }

    private void renderWallPapersCommands(SpriteBatch batch) {
        batch.draw(Assets.getInstance().paper,-15.0f,00.0f,270f,600f);
        batch.draw(Assets.getInstance().paper,590.0f,00.0f,280f,600f);
    }

    private void renderSkils(SpriteBatch batch) {
        Assets.getInstance().fontes.defaultBig.draw(batch, " Powers ", 10, 75);
        if(true){//LevelHelper.getInstance().tochaDisponivel()) {
            batch.draw(Assets.getInstance().icons.iconTocha, -20, 60, 50, 50, 100, 100, 0.35f, -0.35f, 0);
        }else{
            batch.draw(Assets.getInstance().icons.iconTochaoff, -20, 60, 50, 50, 100, 100, 0.35f, -0.35f, 0);
        }
        batch.draw(Assets.getInstance().icons.iconEsculdooff, 20, 60, 50, 50, 100, 100, 0.35f, -0.35f, 0);
        batch.draw(Assets.getInstance().icons.iconSaltooff, 60, 60, 50, 50, 100, 100, 0.35f, -0.35f, 0);
    }

    private void renderGameOverMensage(SpriteBatch batch) {
        Assets.getInstance().fontes.defaultBig.draw(batch," GAME OVER  ",120,80);
    }

    private void renderGuiScore(SpriteBatch batch){
        float x = -15;
        float y = -15;
        //batch.draw();
        Assets.getInstance().fontes.defaultBig.draw(batch,"" + worldController.getScore(),x+75,y+37);
    }

    private void renderGuiMana(SpriteBatch batch){
        if(LevelHelper.getInstance().getPlayer().getMana() > 0) {
            batch.draw(Assets.getInstance().icons.iconeStar, -20, 0, 50, 50, 100, 100, 0.35f, -0.35f, 0);
        }else{
            batch.draw(Assets.getInstance().icons.iconeStarOff, -20, -20, 50, 50, 100, 100, 0.35f, -0.35f, 0);
        }
        Assets.getInstance().fontes.defaultBig.draw(batch, "" + LevelHelper.getInstance().getPlayer().getMana(), 13, 70);
    }

    private void renderGuiFpsCounter (SpriteBatch batch) {
        float x = 5;
        float y = 5;
        int fps = Gdx.graphics.getFramesPerSecond();
        BitmapFont fpsFont = Assets.getInstance().fontes.defaultNormal;
        if (fps >= 45) {
            fpsFont.setColor(0, 1, 0, 1);//verde
        } else if (fps >= 30) {
            fpsFont.setColor(1, 1, 0, 1);//amarelo
        } else {
            fpsFont.setColor(1, 0, 0, 1);//vermelho
        }
        fpsFont.draw(batch, "FPS: " + fps, x, y);
        fpsFont.setColor(1, 1, 1, 1); //branco
    }

    public void renderMap(float deltaTime) {
        Level temp = LevelHelper.getInstance().getFases(LevelHelper.getInstance().getLevelAtual());
        float x = temp.getCordenadasMapaDeProgresso().x;
        float y = temp.getCordenadasMapaDeProgresso().y;

        batch.begin();
        for(Level lvl:LevelHelper.getInstance().getFases()) {
            batch.draw(Assets.getInstance().icons.iconeDungeonLevel, lvl.getCordenadasMapaDeProgresso().x, lvl.getCordenadasMapaDeProgresso().y,110,100);
            if (lvl.getNumeroLevel() == LevelHelper.getInstance().getLevelAtual()) {
                batch.draw(Assets.getInstance().jogador.idle_donw, x-30, y, 60, 110);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }



}
