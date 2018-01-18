package com.mygdx.game.LevelPack;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import com.mygdx.game.ModelsPack.AbstractGameObject;
import com.mygdx.game.ModelsPack.Assets;
import com.mygdx.game.ModelsPack.Ground;
import com.mygdx.game.ModelsPack.Player;
import com.mygdx.game.ModelsPack.Torch;
import com.mygdx.game.ModelsPack.Wall;
import com.mygdx.game.ScreensPack.GuiScreen;


import static com.mygdx.game.ScreensPack.GuiScreen.isStart;
import static com.mygdx.game.ScreensPack.GuiScreen.setStart;


/**
 * Created by LucasRezende on 08/08/2017.
 */

public class LevelBuild extends ScreenAdapter {

    private static final String TAG = LevelBuild.class.getName();
    private static final Float COOLDOWN = 1f;

    private Stage stage;
    private Level level;
    private float sizeX;
    private float sizeY;
    private int totalComandos = 0;
    private int comandoAtual = 0;
    private int comandoDeReturn;
    private AbstractGameObject obj;
    private Player player;
    private Torch objetivo;
    private float espera = COOLDOWN;
    private boolean inEspera = false;


    public LevelBuild(){
       initLevel();
    }

    public void initLevel(){
        level = LevelHelper.getInstance().getFases(LevelHelper.getInstance().getLevelAtual());
        stage = new Stage();
        sizeX = 1f;
        sizeY = 1f;
        initMap();
    }

    public void initMap() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                switch (level.getMapa()[x][y]) {
                    case 0:
                        obj = new Ground(Assets.getInstance().ground.ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        break;
                    case 1:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.side_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 2:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.side_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        obj.rotation = 90;
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 3:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.up_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 4:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.side_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        obj.rotation = 180;
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 5:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.cornerLB_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 6:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.cornerLT_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 7:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.cornerRB_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 8:
                        obj = new Ground(Assets.getInstance().ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Wall(Assets.getInstance().wall.cornerRT_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        WallHelper.getInstance().addWall((Wall) obj);
                        break;
                    case 9:
                        obj = new Ground(Assets.getInstance().ground.ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Player();
                        obj.position.set(x-5,y-7);
                        player = (Player) obj;
                        LevelHelper.getInstance().setPlayer(player);
                        break;
                    case 10:
                        obj = new Ground(Assets.getInstance().ground.ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        GroundHelper.getInstance().addGround((Ground)obj);
                        obj = new Torch(Assets.getInstance().torch.torch_off);
                        obj.position.set(x-5,y-7);
                        objetivo = (Torch) obj;
                        LevelHelper.getInstance().setTocha(objetivo);
                        break;
                    default:
                        //nothing
                        break;
                }

            }
        }

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void update (float deltaTime) {
        final List<String> list;
            try {
                list = GuiScreen.pullComands();
                totalComandos = list.getItems().size;
                if(isStart()) {

                    if(!inEspera){
                            if(player.isinicioDoLaço()){
                                comandoDeReturn = comandoAtual-1;
                                //player.setLaçoAtivo(true);
                                player.desativarinicioDoLaço();
                            }
                            if(!player.isFimDoLaço()){//verifica final do laço caso nao atenda 'comandosrealizados' volta  a ser o valor de 'caomandoDeReturn'
                                comandoAtual = comandoDeReturn;
                                player.desativaFimDoLaço();
                            }
                            player.comandos(player.Parse.get(list.getItems().get(comandoAtual)));
                            comandoAtual++;
                            inEspera = true;
                            LevelHelper.getInstance().setEstadoAtivo(false);
                    }else{
                            espera -= deltaTime;
                            if(espera <= 0){
                                inEspera = false;
                                LevelHelper.getInstance().setEstadoAtivo(true);
                                resetEspera();
                            }
                    }
                    if(comandoAtual == totalComandos){
                        setStart();
                        comandoAtual = 0;
                    }
                }
            }catch (Exception e){
                System.out.print("Erro ao capturar comandos \n");
            }
        }

    private void resetEspera() {
        espera = COOLDOWN;
    }

    public void render(SpriteBatch batch) {
        renderMap(batch);
        if(objetivo != null)
            objetivo.render(batch);
        player.render(batch);
    }

    public void renderMap(SpriteBatch batch) {
        for(Ground m: GroundHelper.getInstance().getGrounds())
            m.render(batch);
        for(Wall w: WallHelper.getInstance().getWalls())
            w.render(batch);
        }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isCompleteObjective() {
        return objetivo.isTorch_On();
    }

    public boolean isInEspera() {
        return inEspera;
    }

    public Torch getObjetivo() {
        return objetivo;
    }
}
