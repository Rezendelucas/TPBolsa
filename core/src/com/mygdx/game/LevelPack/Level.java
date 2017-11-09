package com.mygdx.game.LevelPack;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

public class Level extends ScreenAdapter {

    private static final String TAG = Level.class.getName();

    private final Stage stage;
    private int[][] currente_Level;
    private float sizeX;
    private float sizeY;
    private int totalComandos = 0;
    private int comandosRealizados = 0;
    private AbstractGameObject obj;
    private Player player;
    private Torch objetivo;
    private TextureAtlas atlas;

    public Level(){
        stage = new Stage();
        sizeX = 1f;
        sizeY = 1f;
        currente_Level = LevelHelper.getInstance().getCurrent_Level();
        initMap();
    }

    public void initMap() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                switch (currente_Level[x][y]) {
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
        List<Object> list;
            try {
                list = GuiScreen.pullComands();
                totalComandos = list.getItems().size;
                if(isStart()) {
                //for (int i = 0; i < list.getItems().size; i++) {
                    int wait = 50000;
                    while (wait > 0) {
                        if (wait == 50000) {
                            player.comandos(player.Parse.get(list.getItems().get(comandosRealizados)));
                            comandosRealizados++;
                            //list.getItems().removeIndex(1);
                            wait--;
                            //render(deltaTime,batch);
                        }else{
                            System.out.print("Em espera! \n");
                            wait--;
                        }
                    }
                    if(comandosRealizados == totalComandos){
                        setStart();
                        comandosRealizados = 0;
                        System.out.print("Final de comando \n");
                    }
                }
            }catch (Exception e){
                System.out.print("Erro ao capturar fila de comandos \n");
            }
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
}
